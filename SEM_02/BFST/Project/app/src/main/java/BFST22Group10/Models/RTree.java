package BFST22Group10.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class RTree implements SpatialTree, Serializable {
    public static final long serialVersionUID = 32;
    private final static int pqCapacity = 256;
    private final static int treeCapacity = 16;
    static int counter = 0;

    private final PriorityQueue<OSMElement> elements;
    private RTree[] subtrees;
    private float minLat, maxLat, minLon, maxLon;

    static private List<Pointable> returnList;

    public static HashMap<Integer, RTree> getTreesByZoomLevel() {
        return treesByZoomLevel;
    }

    public static void setTreesByZoomLevel(HashMap<Integer, RTree> treesByZoomLevel) {
        RTree.treesByZoomLevel = treesByZoomLevel;
    }

    private static HashMap<Integer, RTree> treesByZoomLevel;
    private static List<Integer> zoomLevels;

    public RTree() {
        elements = new PriorityQueue<>();
    }

    private void addElement(OSMElement element) {
        if (elements.size() == 0) {
            minLat = element.getMinLat();
            maxLat = element.getMaxLat();
            minLon = element.getMinLon();
            maxLon = element.getMaxLon();
        } else {
            minLat = Math.min(minLat, element.getMinLat());
            maxLat = Math.max(maxLat, element.getMaxLat());
            minLon = Math.min(minLon, element.getMinLon());
            maxLon = Math.max(maxLon, element.getMaxLon());
        }

        elements.offer(element);

        if (elements.size() > pqCapacity) {
            OSMElement smallestElement = elements.poll();
            addElementToSubtree(smallestElement);
        }
    }

    private void addElementToSubtree(OSMElement element) {
        if (subtrees == null) createSubtrees();

        RTree smallestNewRTree = subtrees[0];
        float smallestBoundingBox = subtrees[0].getBoundingBoxChange(element);

        checkIfBoundingBoxContainElement(element, smallestNewRTree, smallestBoundingBox);
    }

    private void checkIfBoundingBoxContainElement(OSMElement element, RTree smallestNewRTree, float smallestBoundingBox) {
        for (RTree subtree : subtrees) {
            if (subtree.getBoundingBox() == 0.0 || subtree.getBoundingBoxChange(element) == 0.0) {
                smallestNewRTree = subtree;
                break;
            } else if (subtree.getBoundingBoxChange(element) < smallestBoundingBox) {
                smallestBoundingBox = subtree.getBoundingBoxChange(element);
                smallestNewRTree = subtree;
            }
        }
        smallestNewRTree.addElement(element);
    }

    private float getBoundingBoxChange(OSMElement newElement) {
        float minLat = Math.min(this.minLat, newElement.getMinLat());
        float maxLat = Math.max(this.maxLat, newElement.getMaxLat());
        float minLon = Math.min(this.minLon, newElement.getMinLon());
        float maxLon = Math.max(this.maxLon, newElement.getMaxLon());
        return (maxLat - minLat) * (maxLon - minLon) - getBoundingBox();
    }

    private float getBoundingBox() {
        return (this.maxLat - this.minLat) * (this.maxLon - this.minLon);
    }

    private void createSubtrees() {
        subtrees = new RTree[treeCapacity];
        for (int i = 0; i < treeCapacity; i++) {
            subtrees[i] = new RTree();
        }
    }

    @Override
    public void addElements(List<Pointable> elements, boolean axis) {
        createMainTrees();
        for (Pointable element : elements) {
            if(((OSMElement) element).getMainTag() == Tag.UNDEFINED) continue;
            treesByZoomLevel.get(((OSMElement) element).getMainTag().getRequiredZoomLevel()).addElement((OSMElement) element);
        }
    }

    @Override
    public List<Pointable> searchQuery(float queryMinLat, float queryMaxLat, float queryMinLon, float queryMaxLon, int zoomLevel, boolean useDebug) {
        returnList = new ArrayList<>();
        for (Integer zoom : zoomLevels) {
            if (zoom > zoomLevel) continue;
            treesByZoomLevel.get(zoom).search(queryMinLat, queryMaxLat, queryMinLon, queryMaxLon, useDebug);
        }
        return returnList;
    }

    public static void createMainTrees() {
        treesByZoomLevel = new HashMap<>();
        zoomLevels = new ArrayList<>();
        for (Tag t : Tag.values()) {
            if (null == treesByZoomLevel.putIfAbsent(t.getRequiredZoomLevel(), new RTree())) {
                zoomLevels.add(t.getRequiredZoomLevel());
            }
        }
    }

    private void search(float queryMinLat, float queryMaxLat, float queryMinLon, float queryMaxLon, boolean useDebug) {
        if (boxesOverlap(queryMinLat, queryMaxLat, queryMinLon, queryMaxLon, this.minLat, this.maxLat, this.minLon, this.maxLon)) {
            if (useDebug) addDebug();

            getElementsIfContained(queryMinLat, queryMaxLat, queryMinLon, queryMaxLon);

            if (subtrees == null) return;
            for (RTree subtree : subtrees) {
                subtree.search(queryMinLat, queryMaxLat, queryMinLon, queryMaxLon, useDebug);
            }
        }
    }

    private void addDebug() {
        float[][] lats = new float[][]{{minLat, maxLat, maxLat, minLat, minLat}};
        float[][] lons = new float[][]{{minLon, minLon, maxLon, maxLon, minLon}};
        OSMElement debugBox = new OSMElement(lats, lons, false);
        debugBox.setMainTag(Tag.DEBUG);
        returnList.add(debugBox);
    }

    private void getElementsIfContained(float queryMinLat, float queryMaxLat, float queryMinLon, float queryMaxLon) {
        for (OSMElement element : elements) {
            if (boxesOverlap(queryMinLat, queryMaxLat, queryMinLon, queryMaxLon, element.getMinLat(), element.getMaxLat(), element.getMinLon(), element.getMaxLon())) {
                returnList.add(element);
                counter++;
            }
        }
    }

    private static boolean coordinatesOverlap(float firstMin, float firstMax, float secondMin, float secondMax) {
        return (firstMin > secondMin && firstMin < secondMax) || (firstMax > secondMin && firstMax < secondMax) || (secondMin > firstMin && secondMin < firstMax);
    }

    private static boolean boxesOverlap(float firstMinLat, float firstMaxLat, float firstMinLon, float firstMaxLon, float secondMinLat, float secondMaxLat, float secondMinLon, float secondMaxLon) {
        boolean latOverlaps = coordinatesOverlap(firstMinLat, firstMaxLat, secondMinLat, secondMaxLat);

        boolean lonOverlaps = coordinatesOverlap(firstMinLon, firstMaxLon, secondMinLon, secondMaxLon);

        return (latOverlaps && lonOverlaps);
    }
}