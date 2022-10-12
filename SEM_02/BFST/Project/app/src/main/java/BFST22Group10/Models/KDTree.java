package BFST22Group10.Models;

import java.util.ArrayList;
import java.util.List;

public class KDTree implements SpatialTree {
    public static final long serialVersionUID = 10;
    private ArrayList<Pointable> root = new ArrayList<>();
    private KDTree leftChild;
    private KDTree rightChild;
    private ArrayList<Pointable> range;
    private final int cutOffValue = 1000;

    public Pointable getRoot() { return root.get(0); }
    public ArrayList<Pointable> getRoots() { return root; }
    public KDTree getLeftChild() { return leftChild; }
    public KDTree getRightChild() { return rightChild; }

    public List<Pointable> searchQuery(float minLat, float maxLat, float minLon, float maxLon, int maxDepth, boolean useDebug) {
        range = new ArrayList<>();
        searchQueryHelper(this, minLat, maxLat, minLon, maxLon, true, minLat, maxLat, minLon, maxLon, useDebug);
        return range;
    }

    private void searchQueryHelper(KDTree tree, float minLat, float maxLat, float minLon, float maxLon, boolean xAxis, float debugMinLat, float debugMaxLat, float debugMinLon, float debugMaxLon, boolean useDebug) {
        if (tree == null) return;
        ArrayList<Pointable> roots = tree.getRoots();

        if (roots.size() == 1) {
            searchQueryHelperForNonLeaf(tree, roots, minLat, maxLat, minLon, maxLon, xAxis, debugMinLat, debugMaxLat, debugMinLon, debugMaxLon, useDebug);
        }
        else {
            searchQueryHelperForLeaf(roots, maxLat, maxLon, minLon, minLat);
        }
    }

    private void searchQueryHelperForNonLeaf(KDTree tree, ArrayList<Pointable> roots, float minLat, float maxLat, float minLon, float maxLon, boolean xAxis, float debugMinLat, float debugMaxLat, float debugMinLon, float debugMaxLon, boolean useDebug) {
        Pointable root = roots.get(0);
        float axisCoord = xAxis ? root.getMainLon() : root.getMainLat();
        float minValue = xAxis ? minLon : minLat;
        float maxValue = xAxis ? maxLon : maxLat;

        if (useDebug) addDebugLine(xAxis, root, debugMinLon, debugMaxLon, debugMinLat, debugMaxLat, maxLat, minLat, maxLon, minLon);

        if(axisCoord > minValue) {
            if (!xAxis)
                searchQueryHelper(tree.getLeftChild(), minLat, maxLat, minLon, maxLon, true, debugMinLat, axisCoord, debugMinLon, debugMaxLon, useDebug);
            else
                searchQueryHelper(tree.getLeftChild(), minLat, maxLat, minLon, maxLon, false, debugMinLat, debugMaxLat, debugMinLon, axisCoord, useDebug);
        }
        if(axisCoord < maxValue) {
            if (!xAxis)
                searchQueryHelper(tree.getRightChild(), minLat, maxLat, minLon, maxLon, true, axisCoord, debugMaxLat, debugMinLon, debugMaxLon, useDebug);
            else
                searchQueryHelper(tree.getRightChild(), minLat, maxLat, minLon, maxLon, false, debugMinLat, debugMaxLat, axisCoord, debugMaxLon, useDebug);
        }
        if(axisCoord >= minValue && axisCoord <= maxValue){
            OSMElement element = (OSMElement) root;
            if (element.getMinLat() <= maxLat && element.getMinLon() <= maxLon && element.getMaxLat() >= minLat && element.getMaxLon() >= minLon)
                range.add(element);
        }
    }

    private void searchQueryHelperForLeaf(ArrayList<Pointable> roots, float maxLat, float maxLon, float minLon, float minLat) {
        for (Pointable point : roots) {
            OSMElement element = (OSMElement) point;
            if (element.getMinLat() <= maxLat && element.getMinLon() <= maxLon && element.getMaxLat() >= minLat && element.getMaxLon() >= minLon)
                range.add((OSMElement) point);
        }
    }

    private void addDebugLine(boolean xAxis, Pointable root, float debugMinLon, float debugMaxLon, float debugMinLat, float debugMaxLat, float maxLat, float minLat, float maxLon, float minLon) {
        float[][] lats = new float[1][2];
        float[][] lons = new float[1][2];

        if (!xAxis) addDebugLineHelper(lats, lons, root.getMainLat(), root.getMainLat(), debugMinLon, debugMaxLon);
        else addDebugLineHelper(lats, lons, debugMinLat, debugMaxLat, root.getMainLon(), root.getMainLon());

        OSMElement debugLine = new OSMElement(lats, lons, false);
        debugLine.setMainTag(Tag.DEBUG);

        if (debugLine.getMinLat() <= maxLat && debugLine.getMinLon() <= maxLon && debugLine.getMaxLat() >= minLat && debugLine.getMaxLon() >= minLon)
            range.add(debugLine);
    }

    private static void addDebugLineHelper(float[][] lats, float[][] lons, float firstLat, float secondLat, float firstLon, float secondLon) {
        lats[0][0] = firstLat;
        lats[0][1] = secondLat;
        lons[0][0] = firstLon;
        lons[0][1] = secondLon;
    }

    public Pointable nearestNeighborSearch(float lon, float lat, boolean xAxis) {
        if(root.size() == 0) return null;
        if (root.size() == 1) {
            return nearestNeighborSearchForNonLeaf(xAxis, lat, lon);
        }
        else {
            return linearSearchOfClosestPointableInLeaf(lon, lat);
        }
    }

    private Pointable nearestNeighborSearchForNonLeaf(boolean xAxis, float lat, float lon) {
        float axisCoord =       xAxis ? root.get(0).getMainLon() : root.get(0).getMainLat();
        float coordVal =        xAxis ? lon : lat;
        double otherDistance =  xAxis ? Math.abs((root.get(0).getMainLon()) - lon) : Math.abs(root.get(0).getMainLat() - lat);

        KDTree nextTree =  axisCoord > coordVal ? leftChild : rightChild;
        KDTree otherTree = axisCoord > coordVal ? rightChild : leftChild;

        if (nextTree == null) return root.get(0);
        Pointable temp = nextTree.nearestNeighborSearch(lon, lat, !xAxis);
        Pointable closest = compareDistance(temp, root.get(0), lon, lat);

        double bestDistance = getDistance(closest, lon, lat);

        if(otherDistance <= bestDistance){
            temp = otherTree.nearestNeighborSearch(lon, lat, !xAxis);
            closest = compareDistance(temp, closest, lon, lat);
        }

        return closest;
    }

    private Pointable linearSearchOfClosestPointableInLeaf(float lon, float lat) {
        Pointable closest = compareDistance(null, root.get(0), lon, lat);
        for (Pointable p: root) {
            closest = compareDistance(closest, p, lon, lat);
        }
        return closest;
    }

    private Pointable compareDistance(Pointable firstPoint, Pointable secondPoint, float lon, float lat){
        if(firstPoint == null) return secondPoint;
        if(secondPoint == null) return firstPoint;

        double distFirstPoint = getDistance(firstPoint, lon, lat);
        double distSecondPoint = getDistance(secondPoint, lon, lat);

        return distFirstPoint < distSecondPoint ? firstPoint : secondPoint;
    }

    private double getDistance(Pointable point, float lon, float lat){
        double aSquared = Math.pow(Math.abs(point.getMainLon()-lon), 2);
        double bSquared = Math.pow(Math.abs(point.getMainLat()-lat), 2);
        return aSquared+bSquared;
    }

    @Override
    public void addElements(List<Pointable> elements, boolean xAxis) {
        if (elements.size() > cutOffValue) {
            if (xAxis) elements.sort((first, next) -> Float.compare(first.getMainLon(), next.getMainLon()));
            else elements.sort((first, next) -> Float.compare(first.getMainLat(), next.getMainLat()));

            int median = elements.size() / 2;
            root.add(elements.get(median));

            leftChild = new KDTree();
            leftChild.addElements(new ArrayList<>(elements.subList(0, median)), !xAxis);
            rightChild = new KDTree();
            rightChild.addElements(new ArrayList<>(elements.subList(median+1, elements.size())), !xAxis);
        }
        else root.addAll(elements);
    }
}