package BFST22Group10.Models;

import java.io.Serializable;

public class Node implements Serializable {
    public static final long serialVersionUID = 12;
    private final float lat;
    private final float lon;
    
    public Node(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
    public float getMainLat() {
        return lat;
    }
    
    public float getMainLon() {
        return lon;
    }

    public boolean isVertex() {
        return false;
    }
}
