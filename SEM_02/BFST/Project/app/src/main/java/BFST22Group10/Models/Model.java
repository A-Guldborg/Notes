package BFST22Group10.Models;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Model {
    private XMLStreamReader reader;

    static private HashMap<Long, Vertex> allVertices = new HashMap<>();
    private ArrayList<Pointable> allAddressVertices = new ArrayList<>();

    private SpatialTree spatialTree;
    private boolean isKDTree = false;

    private final HashMap<Long, OSMElement> relationById = new HashMap<>();
    private final HashMap<Long, OSMElement> wayById = new HashMap<>();
    private HashMap<Long, Node> nodesById = new HashMap<>();

    private float minLat, minLon, maxLat, maxLon;
    private ArrayList<OSMElement> islands = new ArrayList<>();
    private TernarySearchTree addressesTST = new TernarySearchTree();
    private KDTree vertexTree = null;
    private KDTree vertexAddressTree = null;
    private float latLonProportions;

    private Tag currentTag = Tag.UNDEFINED;
    private long currentId;
    private LinkedList<LinkedList<Float>> latOfCurrentElement;
    private LinkedList<LinkedList<Float>> lonOfCurrentElement;

    private ArrayList<Long> nodesOfCurrentWay;
    private short currentMaxSpeed;
    private Boolean currentIsCarAllowed;
    private Boolean currentIsBicycleAllowed;
    private Boolean currentIsPedestrianAllowed;
    private String currentName;
    private boolean isOneWay;

    private Address currentAddress;
    private boolean isIsland;

    public Model(InputStream path, String pathName) {
        openFile(path, pathName);
        calculateLatLonProportions();
    }

    private void calculateLatLonProportions() {
        float centerLat = (minLat + maxLat) / 2;
        float centerLon = (minLon + maxLon) / 2;
        float latDistance = calculateDistance(centerLon, minLat, centerLon, maxLat);
        float lonDistance = calculateDistance(minLon, centerLat, maxLon, centerLat);
        float averageLatDistance = latDistance / (maxLat - minLat);
        float averageLonDistance = lonDistance / (maxLon - minLon);
        latLonProportions = averageLonDistance / averageLatDistance;
    }

    public static float calculateDistance(float fromLon, float fromLat, float toLon, float toLat) {
        double lonFrom = Math.toRadians(fromLon);
        double lonTo = Math.toRadians(toLon);
        double latFrom = Math.toRadians(fromLat);
        double latTo = Math.toRadians(toLat);
        double deltaLon = lonTo-lonFrom;
        double deltaLat = latTo-latFrom;
        double sidelengthInSphericalTriangle = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(latFrom) * Math.cos(latTo) * Math.pow(Math.sin(deltaLon / 2),2);
        double angleOfDistanceInRadians = 2 * Math.asin(Math.sqrt(sidelengthInSphericalTriangle));
        double radiusOfEarth = 6371; //Radius of earth in km
        return (float) (angleOfDistanceInRadians*radiusOfEarth);
    }

    private void createSpatialTree(List<Pointable> elements) {
        if (spatialTree == null) spatialTree = isKDTree ? new KDTree() : new RTree();
        spatialTree.addElements(elements, true);
    }

    public void changeSpatialTree() {
        isKDTree = !isKDTree;
        List<Pointable> elements = spatialTree.searchQuery(minLat, maxLat, minLon, maxLon, 50000, false);
        spatialTree = isKDTree ? new KDTree() : new RTree();
        createSpatialTree(elements);
    }
    
    private void openFile(InputStream path, String pathName) {
        try {
            if (pathName.endsWith(".zip")) {
                var zip = new ZipInputStream(path);
                zip.getNextEntry();
                loadOSM(zip);
            }else if (pathName.endsWith(".osm")) {
                loadOSM(path);
                createVertexTrees();
                addressesTST.reduceTree();
                ArrayList<Pointable> allOSMElements = new ArrayList<>(relationById.values());
                allOSMElements.addAll(wayById.values());
                createSpatialTree(allOSMElements);
                nodesById = null;
            }
            else if (pathName.endsWith(".obj")) {
                try (var input = new ObjectInputStream(path)) {
                    minLat = input.readFloat();
                    minLon = input.readFloat();
                    maxLat = input.readFloat();
                    maxLon = input.readFloat();
                    addressesTST = (TernarySearchTree) input.readObject();
                    vertexAddressTree = (KDTree) input.readObject();
                    vertexTree = (KDTree) input.readObject();
                    allVertices = (HashMap<Long, Vertex>) input.readObject();
                    RTree.createMainTrees();
                    RTree.setTreesByZoomLevel((HashMap<Integer, RTree>) input.readObject());
                    spatialTree = (SpatialTree) input.readObject();
                    isKDTree = input.readBoolean();
                    isIsland = input.readBoolean();
                    islands = (ArrayList<OSMElement>) input.readObject();
                } catch (ClassCastException e) {
                    System.out.println("Exception reading objects. Perhaps serialized file is outdated?");
                }
                System.gc();
            }
        }
        catch (XMLStreamException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(String path) throws IOException {
        try (var out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeFloat(minLat);
            out.writeFloat(minLon);
            out.writeFloat(maxLat);
            out.writeFloat(maxLon);
            out.writeObject(addressesTST);
            out.writeObject(vertexAddressTree);
            out.writeObject(vertexTree);
            out.writeObject(allVertices);
            out.writeObject(RTree.getTreesByZoomLevel());
            out.writeObject(spatialTree);
            out.writeBoolean(isKDTree);
            out.writeBoolean(isIsland);
            out.writeObject(islands);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void loadOSM(InputStream inputStream) throws XMLStreamException {
        reader = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedInputStream(inputStream));

        while(reader.hasNext()){
            switch (reader.next()){
                case XMLStreamConstants.START_ELEMENT:
                    startElement();
                case XMLStreamConstants.END_ELEMENT:
                    endElement();
            }
        }

        isIsland = !islands.isEmpty();
        reader.close();
    }

    private void startElement() {
        switch(reader.getLocalName()) {
            case "bounds":
                loadBounds();
                break;
            case "node":
                createNode();
                break;
            case "way":
                loadWay();
                break;
            case "nd":
                insertNodeInWay();
                break;
            case "relation":
                loadRelation();
                break;
            case "member":
                insertMemberInRelation();
                break;
            case "tag":
                addTag();
                break;
        }
    }

    private void loadBounds() {
        minLat = Float.parseFloat(reader.getAttributeValue(null, "minlat"));
        minLon = Float.parseFloat(reader.getAttributeValue(null, "minlon"));
        maxLat = Float.parseFloat(reader.getAttributeValue(null, "maxlat"));
        maxLon = Float.parseFloat(reader.getAttributeValue(null, "maxlon"));
    }

    private void createNode() {
        currentName = null;
        currentId = Long.parseLong(reader.getAttributeValue(null, "id"));
        float lat = Float.parseFloat(reader.getAttributeValue(null, "lat"));
        float lon = Float.parseFloat(reader.getAttributeValue(null, "lon"));
        nodesById.put(currentId, new Node(lat, lon));
    }

    private void loadWay() {
        currentName = "Unknown Road";
        currentTag = Tag.UNDEFINED;
        isOneWay = false;
        latOfCurrentElement = new LinkedList<>();
        lonOfCurrentElement = new LinkedList<>();
        nodesOfCurrentWay = new ArrayList<>();
        currentMaxSpeed = 0;
        currentIsCarAllowed = null;
        currentIsBicycleAllowed = null;
        currentIsPedestrianAllowed = null;
        latOfCurrentElement.add(new LinkedList<>());
        lonOfCurrentElement.add(new LinkedList<>());
        currentId = Long.parseLong(reader.getAttributeValue(null, "id"));
    }

    private void insertNodeInWay() {
        Long id = Long.parseLong(reader.getAttributeValue(null, "ref"));
        Node node = nodesById.get(id);
        nodesOfCurrentWay.add(id);
        latOfCurrentElement.get(0).add(node.getMainLat());
        lonOfCurrentElement.get(0).add(node.getMainLon());
    }

    private void loadRelation() {
        currentName = null;
        latOfCurrentElement = new LinkedList<>();
        lonOfCurrentElement = new LinkedList<>();
        currentTag = Tag.UNDEFINED;
        currentId = Long.parseLong(reader.getAttributeValue(null, "id"));
    }

    private void insertMemberInRelation() {
        long ref = Long.parseLong(reader.getAttributeValue(null, "ref"));
        String type = reader.getAttributeValue(null, "type");
        OSMElement element;
        if (type.equals("way")) {
            element = wayById.get(ref);
        }
        else {
            element = relationById.get(ref);
        }
        if(element == null) return;
        addMember(arrayToArrayList(element.getLat()), arrayToArrayList(element.getLon()));
    }

    private void addTag() {
        String key = reader.getAttributeValue(null, "k");
        String value = reader.getAttributeValue(null, "v");
        if (key.startsWith("addr:") && nodesById.get(currentId) != null) addToAddress(key, value);
        if (key.equals("wikipedia") && currentName != null) insertPlace();
        try {
            if (key.equals("maxspeed")) currentMaxSpeed = Short.parseShort(value);
        } catch (NumberFormatException ignored) {}
        if (key.equals("bicycle")) currentIsBicycleAllowed = isModeOfTransportAllowed(value);
        if (key.equals("foot")) currentIsPedestrianAllowed = isModeOfTransportAllowed(value);
        if (key.equals("motorcar") || key.equals("motor_vehicle")) currentIsCarAllowed = isModeOfTransportAllowed(value);
        if (currentTag == Tag.UNDEFINED) currentTag = Tag.getTag(key + "_" + value);
        if(key.equals("name")) currentName = reader.getAttributeValue(null, "v");
        if ((key.equals("oneway") && value.equals("yes")) || (key.equals("junction") && value.equals("roundabout"))) isOneWay = true;
    }

    private boolean isModeOfTransportAllowed(String value) {
        return value.equals("yes");
    }

    private void insertPlace() {
        float lat;
        float lon;
        if(nodesById.get(currentId) != null) {
            lat = nodesById.get(currentId).getMainLat();
            lon = nodesById.get(currentId).getMainLon();
        } else if (latOfCurrentElement != null) {
            lat = getMainCoord(arrayListToArray(latOfCurrentElement));
            lon = getMainCoord(arrayListToArray(lonOfCurrentElement));
        } else {
            return;
        }
        createNewAddress(lat, lon);
        currentAddress.setCity(currentName);
        endAddress();
    }

    private float getMainCoord(float[][] coords) {
        float min = 100;
        float max = 0;
        for (float[] arrayList: coords) {
            for (float f: arrayList) {
                if (f < min) min= f;
                if (f > max) max = f;
            }
        }
            return min + (max-min)/2;
    }
    
    private void addToAddress(String key, String value) {
        value = value.intern();
        switch (key){
            case "addr:city":
                createNewAddress(nodesById.get(currentId).getMainLat(), nodesById.get(currentId).getMainLon());
                currentAddress.setCity(value);
                break;
            case "addr:housenumber":
                currentAddress.setHousenumber(value);
                break;
            case "addr:postcode":
                currentAddress.setPostcode(value);
                break;
            case "addr:street":
                currentAddress.setStreet(value);
                endAddress();
                break;
        }
    }

    private void createNewAddress(float lat, float lon) {
        currentAddress = new Address();
        currentAddress.setLat(lat);
        currentAddress.setLon(lon);
    }

    private void endAddress() {
        addressesTST.insert(currentAddress);
        allAddressVertices.add(currentAddress);
    }

    private void addWayToGraph() {
        createAndAddVertex(0);
        for (int i = 1; i < nodesOfCurrentWay.size(); i++) {
            long id = createAndAddVertex(i);
            Vertex current = (Vertex) nodesById.get(id);
            Vertex previous = (Vertex) nodesById.get(nodesOfCurrentWay.get(i-1));
            updateMaxSpeedAndVehiclesAllowed();
            if (!isOneWay)
                current.addEdge(currentMaxSpeed, nodesOfCurrentWay.get(i-1), currentIsCarAllowed, currentIsBicycleAllowed, currentIsPedestrianAllowed, currentName);
            previous.addEdge(currentMaxSpeed, id, currentIsCarAllowed, currentIsBicycleAllowed, currentIsPedestrianAllowed, currentName);
        }
    }

    private void updateMaxSpeedAndVehiclesAllowed() {
        if(currentMaxSpeed == 0) currentMaxSpeed = currentTag.getStandardMaxSpeed();
        if(currentIsCarAllowed == null) currentIsCarAllowed = currentTag.isCarAllowed();
        if(currentIsBicycleAllowed == null) currentIsBicycleAllowed = currentTag.isBicycleAllowed();
        if(currentIsPedestrianAllowed == null) currentIsPedestrianAllowed = currentTag.isPedestrianAllowed();
    }

    private long createAndAddVertex(int i) {
        long id = nodesOfCurrentWay.get(i);
        Node node = nodesById.get(id);
        if(!node.isVertex()) {
            Vertex vertex = new Vertex(node.getMainLat(), node.getMainLon());
            allVertices.put(id, vertex);
            nodesById.put(id, vertex);
        }
        return id;
    }

    private void endElement() {
        switch (reader.getLocalName()){
            case "way":
                endWay();
                break;
            case "relation":
                endRelation();
                break;
        }
    }

    private void endWay() {
        boolean isPolygon = currentTag.isPolygon();
        if (isPolygon) isPolygon = firstPointEqualsLastPoint(lonOfCurrentElement.get(0), latOfCurrentElement.get(0));
        OSMElement element = new OSMElement(arrayListToArray(latOfCurrentElement), arrayListToArray(lonOfCurrentElement), isPolygon);
        wayById.put(currentId, element);
        element.setMainTag(currentTag);
        if (currentTag == Tag.PLACE) islands.add(element);
        if (currentTag.isRoad()) {
            addWayToGraph();
        }
        currentTag = Tag.UNDEFINED;
    }

    private void endRelation() {
        boolean isPolygon = currentTag.isPolygon();
        if (isArrayNotEmpty(latOfCurrentElement, lonOfCurrentElement)) {
            if (isPolygon) isPolygon = isMultiPolygon();
            OSMElement element = new OSMElement(arrayListToArray(latOfCurrentElement), arrayListToArray(lonOfCurrentElement), isPolygon);
            relationById.put(currentId, element);
            element.setMainTag(currentTag);
            if (currentTag == Tag.PLACE) islands.add(element);
            currentTag = Tag.UNDEFINED;
        }
    }

    public float getMinLat() {
        return minLat;
    }
    
    public float getMinLon() {
        return minLon;
    }
    
    public float getMaxLat() {
        return maxLat;
    }
    
    public float getMaxLon() {
        return maxLon;
    }

    public float getLatLonProportions() {
        return latLonProportions;
    }

    public int getVerticesCount(){
        return allVertices.size();
    }
    
    public HashMap<Tag, ArrayList<OSMElement>> spatialTreeSearchQuery(float[] boundaryBox, int zoomLevel, boolean useDebug) {
        HashMap<Tag, ArrayList<OSMElement>> searchResult = new HashMap<>();
        for (Tag tag : Tag.values()) {
            searchResult.put(tag, new ArrayList<>());
        }

        List<Pointable> queryElements = spatialTree.searchQuery(boundaryBox[0], boundaryBox[1], boundaryBox[2], boundaryBox[3], zoomLevel, useDebug);

        if (isKDTree) searchResult.put(Tag.PLACE, islands);

        for (Pointable element : queryElements) {
            searchResult.get(((OSMElement) element).getMainTag()).add((OSMElement) element);
        }
        return searchResult;
    }

    public List<OSMElement> getIslands() {
        return islands;
    }

    public Address addressSearch(String input) throws NullPointerException{
            return addressesTST.getAddress(Address.valueOf(input).toString());
    }
    
    public boolean isIsland(){
        return isIsland;
    }

    public HashSet<Address> getAddressSuggestions(String input) {
        return addressesTST.prefixSearch(input);
    }
    
    private void createVertexTrees(){
        vertexTree = new KDTree();
        vertexTree.addElements(new ArrayList<>(allVertices.values()), true);
        vertexAddressTree = new KDTree();
        vertexAddressTree.addElements(allAddressVertices, true);
    }
    
    public Pointable getNearestNeighborVertex(float x, float y, Boolean isAddress){
        if(!isAddress) return vertexTree.nearestNeighborSearch(x, y, true);
        else return vertexAddressTree.nearestNeighborSearch(x, y, true);
    }

    static public Vertex getVertexFromId(long id) {
        return allVertices.get(id);
    }

    private void addMember(LinkedList<LinkedList<Float>> lat, LinkedList<LinkedList<Float>> lon) {
        for (int i = 0; i < lat.size(); i++) {
            latOfCurrentElement.add(lat.get(i));
            lonOfCurrentElement.add(lon.get(i));
        }
    }

    private boolean isMultiPolygon() {
        LinkedList<LinkedList<Float>> unassignedLat = latOfCurrentElement;
        LinkedList<LinkedList<Float>> unassignedLon = lonOfCurrentElement;
        LinkedList<LinkedList<Float>> polygonLat = new LinkedList<>();
        LinkedList<LinkedList<Float>> polygonLon = new LinkedList<>();
        int current = 0;

        while (isArrayNotEmpty(unassignedLat, unassignedLon)) {
            startNewPolygon(polygonLon, unassignedLon, current);
            startNewPolygon(polygonLat, unassignedLat, current);
            if (!findWay(polygonLon.get(current), polygonLat.get(current), unassignedLon, unassignedLat)) return false;
            current++;
        }
        latOfCurrentElement = polygonLat;
        lonOfCurrentElement = polygonLon;
        return true;
    }

    private boolean findWay(LinkedList<Float> currentWayLon, LinkedList<Float> currentWayLat,
                            LinkedList<LinkedList<Float>> unassignedLon, LinkedList<LinkedList<Float>> unassignedLat) {
        while (!firstPointEqualsLastPoint(currentWayLon, currentWayLat)) {
            boolean success = false;
            for (int i = 0; i < unassignedLat.size(); i++) {
                boolean reversed = false;
                if (didNotFindNextWay(currentWayLon, currentWayLat, unassignedLon, unassignedLat, i)) {
                    continue;
                }
                else if (hasFoundNextWay(unassignedLon, currentWayLon.get(currentWayLon.size()-1), i, true)
                        && hasFoundNextWay(unassignedLat, currentWayLat.get(currentWayLat.size()-1), i, true)) {
                    reversed = true;
                }
                addWayToPolygon(currentWayLat, unassignedLat, i, reversed);
                addWayToPolygon(currentWayLon, unassignedLon, i, reversed);
                success = true;
                break;
            }
            if (!success) {
                return false;
            }
        }
        return true;
    }

    private boolean didNotFindNextWay(LinkedList<Float> currentWayLon, LinkedList<Float> currentWayLat,
                                      LinkedList<LinkedList<Float>> unassignedLon, LinkedList<LinkedList<Float>> unassignedLat, int i) {
        return !((hasFoundNextWay(unassignedLon, currentWayLon.get(currentWayLon.size()-1), i, false)
                && hasFoundNextWay(unassignedLat, currentWayLat.get(currentWayLat.size()-1), i, false))
                || (hasFoundNextWay(unassignedLon, currentWayLon.get(currentWayLon.size()-1), i, true)
                && hasFoundNextWay(unassignedLat, currentWayLat.get(currentWayLat.size()-1), i, true)));
    }

    private boolean isArrayNotEmpty(LinkedList<LinkedList<Float>> lat, LinkedList<LinkedList<Float>> lon) {
        return lat.size() > 0 && lon.size() > 0;
    }

    private boolean hasFoundNextWay(LinkedList<LinkedList<Float>> unassigned,
                                    Float end, int index, boolean reversed) {
        if (reversed) return end.equals(unassigned.get(index).get(unassigned.get(index).size()-1));
        return end.equals(unassigned.get(index).get(0));
    }

    private boolean firstPointEqualsLastPoint(LinkedList<Float> currentWayLon, LinkedList<Float> currentWayLat) {
        return currentWayLat.get(0).equals(currentWayLat.get(currentWayLat.size()-1))
                && currentWayLon.get(0).equals(currentWayLon.get(currentWayLon.size()-1));
    }

    private void addWayToPolygon(LinkedList<Float> currentWay, LinkedList<LinkedList<Float>> unassigned,
                                 int index, boolean reverse) {
        if (reverse) currentWay.addAll(reverse(unassigned.get(index)));
        else currentWay.addAll(unassigned.get(index));
        unassigned.remove(index);
    }

    private void startNewPolygon(LinkedList<LinkedList<Float>> polygon, LinkedList<LinkedList<Float>> unassigned, int current) {
        polygon.add(new LinkedList<>());
        polygon.get(current).addAll(unassigned.get(unassigned.size()-1));
        unassigned.remove(unassigned.size()-1);
    }

    private LinkedList<Float> reverse(LinkedList<Float> toBeReversed) {
        LinkedList<Float> reversedArray = new LinkedList<>();
        for (int i = toBeReversed.size()-1; i >= 0; i--) {
            reversedArray.add(toBeReversed.get(i));
        }
        return reversedArray;
    }

    private float[][] arrayListToArray(LinkedList<LinkedList<Float>> arrayList) {
        float[][] array = new float[arrayList.size()][];

        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = new float[arrayList.get(i).size()];
            for (int j = 0; j < arrayList.get(i).size(); j++) {
                array[i][j] = arrayList.get(i).get(j);
            }
        }
        return array;
    }

    private LinkedList<LinkedList<Float>> arrayToArrayList(float[][] array) {
        LinkedList<LinkedList<Float>> arrayList = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            arrayList.add(new LinkedList<>());
            for (int j = 0; j < array[i].length; j++) {
                arrayList.get(i).add(array[i][j]);
            }
        }
        return arrayList;
    }

    public void addAddress(Address poiAddress) {
        addressesTST.insert(poiAddress);
    }

    public static void setAllVertices(HashMap<Long, Vertex> allVertices) {
        Model.allVertices = allVertices;
    }
}
