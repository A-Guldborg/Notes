package BFST22Group10.Models;

import java.util.*;

public class PathFinder {
    private final ArrayList<Vertex> legalPathFromSource;
    private final ArrayList<Vertex> mainPath;
    private ArrayList<Vertex> legalPathToDestination;
    private final ArrayList<float[]> visitedEdgesLats = new ArrayList<>();
    private final ArrayList<float[]> visitedEdgesLons = new ArrayList<>();
    private ArrayList<String> routeDescription;
    private Vertex endPoint;

    public PathFinder(Vertex source, Vertex destination, ModeOfTransport modeOfTransport, int amountOfVertices, boolean hasTrailer){
        legalPathFromSource = createPath(source, destination, modeOfTransport, amountOfVertices, false);
        legalPathToDestination = createPath(destination, source, modeOfTransport, amountOfVertices, false);

        Vertex carRouteSource = getEnd(legalPathFromSource);
        Vertex carRouteDestinatination = getEnd(legalPathToDestination);
        if (carRouteSource == null || carRouteDestinatination == null) {
            mainPath = new ArrayList<>();
            mainPath.add(source);
            mainPath.add(destination);
        } else {
            mainPath = createPath(carRouteSource, carRouteDestinatination, modeOfTransport, amountOfVertices, true);
        }
        legalPathToDestination = revertList(legalPathToDestination);
        generateRouteDescription(mainPath, modeOfTransport, hasTrailer);
    }

    private Vertex getEnd(ArrayList<Vertex> path) {
        if (path.size() == 0) return null;
        return path.get(path.size()-1);
    }

    private ArrayList<Vertex> createPath(Vertex source, Vertex destination, ModeOfTransport modeOfTransport, int amountOfVertices, boolean lookingForDestination) {
        HashMap<Vertex, Vertex> shortestPathTree = shortestPathTree(source, destination, modeOfTransport, amountOfVertices, lookingForDestination);
        if (lookingForDestination) {
            return pathToDestination(shortestPathTree, destination);
        }
        return pathToDestination(shortestPathTree, endPoint);
    }


    public ArrayList<float[]> getVisitedEdgesLats() {
        return visitedEdgesLats;
    }

    public ArrayList<float[]> getVisitedEdgesLons() {
        return visitedEdgesLons;
    }

    public ArrayList<String> getRouteDescription() {
        return routeDescription;
    }
    
    private HashMap<Vertex, Vertex> shortestPathTree(Vertex source, Vertex destination, ModeOfTransport modeOfTransport, int amountOfVertices, boolean lookingForDestination){
        // Initialize data structures
        HashSet<Vertex> removeTheseVertices = new HashSet<>();
        HashSet<Vertex> visitedVertices = new HashSet<>();
        HashMap<Vertex, Vertex> shortestPathTree = new HashMap<>();
        IndexMinPQ<Vertex> indexMinPQ = new IndexMinPQ<>(amountOfVertices);

        // Populate first entry of PQ
        int indexCounter = 1;
        source.setIndex(indexCounter++);
        removeTheseVertices.add(source);
        removeTheseVertices.add(destination);
        if(lookingForDestination) {
            source.setDistToDestination(destination, modeOfTransport);
        }
        indexMinPQ.insert(source.getIndex(), source);

        pathSearching:
        while(!indexMinPQ.isEmpty()){
            Vertex vertexFrom = indexMinPQ.minKey();
            indexMinPQ.delMin();
            visitedVertices.add(vertexFrom);
            removeTheseVertices.add(vertexFrom);
            List<Edge> neighbors = vertexFrom.getNeighbors();
            if (neighbors == null) continue;
            for (Edge edge : vertexFrom.getNeighbors()) {
                if(endPointFound(vertexFrom, edge, lookingForDestination, destination, modeOfTransport)){
                    this.endPoint = vertexFrom;
                    break pathSearching;
                }

                Vertex vertexTo = Model.getVertexFromId(edge.getTo());
                removeTheseVertices.add(vertexTo);
                if(lookingForDestination){
                    vertexTo.setDistToDestination(destination, modeOfTransport);
                    if(!edge.isTransportModeAllowed(modeOfTransport)) continue;
                }
                visitedEdgesLats.add(new float[]{vertexFrom.getMainLat(), vertexTo.getMainLat()});
                visitedEdgesLons.add(new float[]{vertexFrom.getMainLon(), vertexTo.getMainLon()});

                if(!visitedVertices.contains(vertexTo) || vertexTo.getDistToSource() > vertexFrom.getDistToSource() + edge.getDistance(modeOfTransport)) {
                    float newDist = lookingForDestination ? vertexFrom.getDistToSource() + edge.getDistance(modeOfTransport) :
                            vertexFrom.getDistToSource() + edge.getDistance(ModeOfTransport.WALK);
                    vertexTo.setDistToSource(newDist);
                    shortestPathTree.put(vertexTo, vertexFrom);

                    if (indexMinPQ.contains(vertexTo.getIndex())){
                        indexMinPQ.changeKey(vertexTo.getIndex(), vertexTo);
                        continue;
                    } else if (vertexTo.getIndex() <= 0) {
                        vertexTo.setIndex(indexCounter++);
                        indexMinPQ.insert(vertexTo.getIndex(), vertexTo);
                    }
                }
            }
        }

        // Cleanup
        removeTheseVertices.forEach(this::resetVertex);
        shortestPathTree.forEach(this::resetVertex);
        visitedVertices.forEach(this::resetVertex);
        resetVertex(destination);
        return shortestPathTree;
    }
    
    private boolean endPointFound(Vertex source, Edge edge, boolean lookingForDestination, Vertex destination, ModeOfTransport modeOfTransport){
        if(lookingForDestination){
            return source.equals(destination);
        } else {
            return edge.isTransportModeAllowed(modeOfTransport);
        }
    }

    private ArrayList<Vertex> pathToDestination(HashMap<Vertex, Vertex> shortestPathTree, Vertex destination){
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex current = destination;
        while(current != null){
            path.add(current);
            current = shortestPathTree.get(current);
        }
        return revertList(path);
    }

    private ArrayList<Vertex> revertList(ArrayList<Vertex> original) {
        ArrayList<Vertex> returnList = new ArrayList<>();
        for (int i = original.size()-1; i >= 0; i--) {
            returnList.add(original.get(i));
        }
        return returnList;
    }
    
    private void resetVertex(Vertex ...value) {
        for (Vertex v : value){
            v.setDistToSource(0f);
            v.setDistToDestination(0f);
            v.setIndex(0);
        }
    }

    private void generateRouteDescription(ArrayList<Vertex> shortestPath, ModeOfTransport modeOfTransport, boolean hasTrailer) {
        if(shortestPath.size() < 3) {
            routeDescription = new ArrayList<>();
            routeDescription.add("Grow wings and fly, as no such route exists.");
            return;
        }
        Iterator<Vertex> iterator = shortestPath.iterator();
        ArrayList<RoadSegment> segments = new ArrayList<>();
        Vertex previous = null;
        Vertex current = iterator.next();
        Vertex nextInPath;
        RoadSegment segment = null;
        float totalDuration = 0f;
    
        while(iterator.hasNext()){
            nextInPath = iterator.next();
            for (Edge edge : current.getNeighbors()) {
                if(Model.getVertexFromId(edge.getTo()).equals(nextInPath)){
                    if(segment == null || !segment.getStreetName().equals(edge.getStreetName())){
                        segment = new RoadSegment(edge.getStreetName(), modeOfTransport);
                        segments.add(segment);
                        if (previous != null)
                            segment.setTurn(previous, current, nextInPath);
                        totalDuration += 0.001f;
                    }
                    segment.addDistance(edge.getDistance());
                    totalDuration += calculateDurationOfEdge(modeOfTransport, edge, hasTrailer);
                    break;
                }
            }
            previous = current;
            current = nextInPath;
        }

        routeDescription = new ArrayList<>();
        for (RoadSegment segmentInPath : segments) {
            routeDescription.add(segmentInPath.toString());
        }
        routeDescription.add("The trip will take " + timeString(totalDuration));
    }

    private float calculateDurationOfEdge(ModeOfTransport modeOfTransport, Edge edge, boolean hasTrailer) {
        float distance = edge.getDistance();
        float speedLimit = edge.getSpeedLimit();
        if(hasTrailer && speedLimit > 80) speedLimit = 80;
        switch (modeOfTransport) {
            case CAR:
                return distance / speedLimit;
            case BICYCLE:
                return distance / 20f;
        }
        return distance / 5f;
    }

    private String timeString(float time) {
        int hours = (int) time;
        int minutes = (int) (60 * (time-hours));
        if(hours < 1) {
            return minutes + " minutes";
        } else {
            return hours + " hours and " + minutes + " minutes";
        }
    }

    public ArrayList<Vertex> getFullPath() {
        ArrayList<Vertex> returnList = new ArrayList<>(legalPathFromSource);
        returnList.addAll(mainPath);
        returnList.addAll(legalPathToDestination);
        return returnList;

    }

    private static class RoadSegment {
        private final String streetName;
        private float distance;
        private String turn = null;
        private final ModeOfTransport modeOfTransport;
        
        public RoadSegment(String streetName, ModeOfTransport modeOfTransport) {
            this.streetName = streetName.intern();
            this.modeOfTransport = modeOfTransport;
        }
    
        public String getStreetName() {
            return streetName;
        }
        
        public void addDistance(float distance){
            this.distance += distance;
        }
        
        public int getDistance(){
            return Math.round(distance * 1000);
        }
        
        public void setTurn(Vertex previous, Vertex current, Vertex nextInPath) {
            float a1 = current.getMainLon() - previous.getMainLon();
            float a2 = current.getMainLat() - previous.getMainLat();
            float b1 = nextInPath.getMainLon() - current.getMainLon();
            float b2 = nextInPath.getMainLat() - current.getMainLat();
            float angle = (float) (180f / Math.PI * (Math.atan2(b1, b2) - Math.atan2(a1, a2)));
            
            if(angle < 30 && angle > -30) {
                turn = "forward";
            } else if ((angle < -30 && angle > -150) || (angle > 210 && angle < 330)){
                turn = "left";
            } else if ((angle > 30 && angle < 150) || (angle < -210 && angle > -330)){
                turn = "right";
            } else {
                turn = "forward";
            }
        }
        
        @Override
        public String toString(){
            String keyWord;
            if(modeOfTransport.equals(ModeOfTransport.WALK)) {
                keyWord = "Walk";
            } else {
                keyWord = "Drive";
            }
            if (turn == null) return keyWord + " along " + this.streetName + " for " + getDistance() + " meters";
            else if (turn.equals("forward")) return keyWord + " forward along " + this.streetName + " for " + getDistance() + " meters";
            return "Turn " + turn + " onto " + this.streetName + " and continue for " + getDistance() + " meters";
        }
    }
}
