package BFST22Group10.Models;

import java.io.Serializable;
import java.util.List;

public interface SpatialTree extends Serializable {
    void addElements(List<Pointable> elements, boolean axis);
    List<Pointable> searchQuery(float minLat, float maxLat, float minLon, float maxLon, int zoomLevel, boolean addDebug);
}
