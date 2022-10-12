package BFST22Group10.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class OSMElement implements Serializable, Comparable<OSMElement>, Pointable {
    public static final long serialVersionUID = 11;
    private final float[][] lat;
    private final float[][] lon;
    private final boolean isPolygon;
    private Tag mainTag;
    private float minLat;
    private float maxLat;
    private float minLon;
    private float maxLon;
    private final float mainLat;
    private final float mainLon;
    
    public OSMElement(float[][] lat, float[][] lon, boolean isPolygon) {
        this.lat = lat;
        this.lon = lon;
        this.isPolygon = isPolygon;
        findMinAndMax();
        mainLat = minLat + (maxLat-minLat)/2;
        mainLon = minLon + (maxLon-minLon)/2;
    }

    private void findMinAndMax() {
        minLat = Integer.MAX_VALUE;
        maxLat = Integer.MIN_VALUE;
        minLon = Integer.MAX_VALUE;
        maxLon = Integer.MIN_VALUE;
        for (int i = 0; i < lat.length; i++) {
            for (int j = 0; j < lat[i].length; j++) {
                if (lat[i][j] < minLat) minLat = lat[i][j];
                if (lon[i][j] < minLon) minLon = lon[i][j];
                if (lat[i][j] > maxLat) maxLat = lat[i][j];
                if (lon[i][j] > maxLon) maxLon = lon[i][j];
            }
        }
    }
    
    public float[][] getLat() {
        return lat;
    }
    
    public float[][] getLon() {
        return lon;
    }

    public boolean isPolygon() {
        return isPolygon;
    }

    public float getMainLat() {
        return mainLat;
    }

    public float getMainLon() {
        return mainLon;
    }

    public Tag getMainTag() {
        return mainTag;
    }

    public void setMainTag(Tag mainTag) {
        this.mainTag = mainTag;
    }

    public float getMinLat() {
        return minLat;
    }

    public float getMaxLat() {
        return maxLat;
    }

    public float getMinLon() {
        return minLon;
    }

    public float getMaxLon() {
        return maxLon;
    }

    public double getBoxArea() {
        return (this.maxLat - this.minLat) * (this.maxLon - this.minLon);
    }

    public boolean isRoad() {
        return mainTag.isRoad();
    }

    @Override
    public int compareTo(OSMElement other) {
        return (int) (this.getBoxArea() - other.getBoxArea());
    }
}
