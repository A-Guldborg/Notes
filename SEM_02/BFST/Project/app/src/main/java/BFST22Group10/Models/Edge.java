package BFST22Group10.Models;

import java.io.Serializable;

public class Edge implements Serializable {
    public static final long serialVersionUID = 30;
    private float distance;
    private short speedLimit;
    private boolean isCarAllowed;
    private boolean isBicycleAllowed;
    private boolean isPedestrianAllowed;
    private String streetName;
    private long toId;
    
    public Edge(short speedLimit, long to, float distance, boolean isCarAllowed, boolean isBicycleAllowed, boolean isPedestrianAllowed, String streetName) {
        this.toId = to;
        this.distance = distance;
        this.speedLimit = speedLimit;
        this.isCarAllowed = isCarAllowed;
        this.isBicycleAllowed = isBicycleAllowed;
        this.isPedestrianAllowed = isPedestrianAllowed;
        this.streetName = streetName.intern();
    }

    public String getStreetName() {
        return streetName;
    }

    public float getDistance(ModeOfTransport modeOfTransport){
        switch (modeOfTransport){
            case CAR:
                return distance / speedLimit;
            case BICYCLE:
                return distance / 20f;
        }
        return distance;
    }

    public float getDistance() {
        return distance;
    }

    public float getSpeedLimit() {
        return speedLimit;
    }
    
    public long getTo() {
        return toId;
    }
    
    public boolean isTransportModeAllowed(ModeOfTransport modeOfTransport){
        switch (modeOfTransport){
            case CAR:
                return isCarAllowed;
            case BICYCLE:
                return isBicycleAllowed;
            case WALK:
                return isPedestrianAllowed;
        }
        return false;
    }
}
