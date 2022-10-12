package BFST22Group10.Models;

import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    @Test
    void distance() {
        Vertex v1 = new Vertex(2, 3);
        Vertex v2 = new Vertex(5, 3);
        v1.setDistToSource(1);
        assertEquals(1, v1.getDistToSource(), "Vertex should use the recently set destination to source");
        v1.setDistToDestination(v2, ModeOfTransport.WALK);
        float expectedDistance = 334.58478f;
        assertEquals(expectedDistance, v1.getSourceAndDestinationDistance());
        v1.setDistToDestination(v2, ModeOfTransport.CAR);
        expectedDistance = 5.16981f;
        assertEquals(expectedDistance, v1.getSourceAndDestinationDistance());
        v1.setDistToDestination(v2, ModeOfTransport.BICYCLE);
        expectedDistance = 17.67924f;
        assertEquals(expectedDistance, v1.getSourceAndDestinationDistance());
        v1.setDistToDestination(3);
        assertEquals(1 + 3, v1.getSourceAndDestinationDistance(), "Vertex should use the recently set distance to destination as a floating point value");
        v2.setDistToDestination(v1, ModeOfTransport.BICYCLE);
        assertTrue(v1.compareTo(v2) < 0, "When distance to source is 0 for one vertex and 1 for another, but distance to destination is equal, then the distance to source 0 vertex should be less than the distance to source 1 vertex");
    }

    @Test
    void edges() {
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        Vertex v1 = new Vertex(1, 2);
        Vertex v2 = new Vertex(3, 4);
        Vertex v3 = new Vertex(5, 6);
        allVertices.put((long)1,v1);
        allVertices.put((long)2,v2);
        allVertices.put((long)3,v3);
        Model.setAllVertices(allVertices);
        v1.addEdge((short) 25, 1, true, true, false, "Testersensvej");
        v1.addEdge((short) 10, 2, true, false, true, "Testersensvej");
        v2.addEdge((short) 15, 3, false, false, true, "Testersensvej");
        assertEquals(2, v1.getNeighbors().size(), "Adding an edge to another vertex should return the vertex when getting all neighbors");
        assertEquals(1, v2.getNeighbors().size(), "Adding an edge to another vertex should return the vertex when getting all neighbors");
        assertNull(v3.getNeighbors(), "A Vertex with no edges should return null");
    }

    @Test
    void getters() {
        Vertex v = new Vertex(1, 2);
        assertEquals(1, v.getMainLat());
        assertEquals(2, v.getMainLon());
        assertTrue(v.isVertex(), "A vertex is always a vertex");
    }
}