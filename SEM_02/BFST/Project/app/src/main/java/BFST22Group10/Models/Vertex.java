package BFST22Group10.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertex extends Node implements Comparable<Vertex>, Pointable, Serializable {
    public static final long serialVersionUID = 33;
    private ArrayList<Edge> neighbors;
    private float distToSource;
    private float distToDestination;
    private int priorityQueueIndex;
    
    public Vertex(float lat, float lon) {
        super(lat, lon);
    }
    
    public void addEdge(short speedLimit, long toId, boolean isCarAllowed, boolean isBicycleAllowed, boolean isPedestrianAllowed, String streetName){
        if (neighbors == null) neighbors = new ArrayList<>();
        Vertex destination = Model.getVertexFromId(toId);
        float distanceBetweenVertices = Model.calculateDistance(this.getMainLon(), this.getMainLat(), destination.getMainLon(), destination.getMainLat());
        Edge edge = new Edge(speedLimit, toId, distanceBetweenVertices, isCarAllowed, isBicycleAllowed, isPedestrianAllowed, streetName);
        neighbors.add(edge);
    }

    public float getDistToSource() {
        return distToSource;
    }
    
    public void setDistToSource(float distToSource) {
        this.distToSource = distToSource;
    }

    public void setDistToDestination(float distToDestination){
        this.distToDestination = distToDestination;
    }
    
    public void setDistToDestination(Vertex destination, ModeOfTransport modeOfTransport) {
        this.distToDestination = Model.calculateDistance(this.getMainLon(), this.getMainLat(), destination.getMainLon(), destination.getMainLat());
        
        switch (modeOfTransport){
            case CAR:
                this.distToDestination = this.distToDestination / 80f;
                break;
            case BICYCLE:
                this.distToDestination = this.distToDestination / 20f;
                break;
        }
    }

    public float getSourceAndDestinationDistance(){
        return distToSource + distToDestination;
    }
    
    public ArrayList<Edge> getNeighbors() {
        return neighbors;
    }
    
    @Override
    public boolean isVertex() {
        return true;
    }
    
    @Override
    public int compareTo(Vertex other) {
        return Float.compare(this.getSourceAndDestinationDistance(), other.getSourceAndDestinationDistance());
    }
    
    public int getIndex() {
        return priorityQueueIndex;
    }
    
    public void setIndex(int index) {
        this.priorityQueueIndex = index;
    }
}
