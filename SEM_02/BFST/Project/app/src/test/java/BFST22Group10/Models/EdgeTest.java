package BFST22Group10.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    HashMap<Long, Vertex> allVertices;

    @BeforeEach
    void setUp() {
        allVertices = new HashMap<>();
        Vertex to = new Vertex(56f, 10f);
        allVertices.put((long)1, to);
        Model.setAllVertices(allVertices);
    }
    @Test
    void getDistance() {
        Vertex from = new Vertex(55f, 9f);
        from.addEdge((short) 80, 1, true, true, true, "Testersensvej");
        Edge edge = from.getNeighbors().get(0);
        float expectedDistance = 127.789566f;
        assertEquals(expectedDistance, edge.getDistance(ModeOfTransport.WALK));
        System.out.println(edge.getDistance(ModeOfTransport.WALK));
    }

    @Test
    void getStreetName() {
        Vertex from = new Vertex(55f, 9f);
        from.addEdge((short) 80, 1, true, true, true, "Testersensvej");
        Edge edge = from.getNeighbors().get(0);
        String expected = "Testersensvej";
        assertEquals(expected, edge.getStreetName());
    }

    @Test
    void getTo() {
        Vertex from = new Vertex(55f, 9f);
        from.addEdge((short) 80, 1, true, true, true, "Testersensvej");
        Edge edge = from.getNeighbors().get(0);
        assertEquals(1, edge.getTo());
        assertNotEquals(from, edge.getTo());
    }

    @Test
    void isTransportModeAllowed() {
        Vertex from = new Vertex(55f, 9f);
        from.addEdge((short) 80, 1, true, true, true, "Testersensvej");
        Edge edge = from.getNeighbors().get(0);
        assertTrue(edge.isTransportModeAllowed(ModeOfTransport.CAR));
        assertTrue(edge.isTransportModeAllowed(ModeOfTransport.BICYCLE));
        assertTrue(edge.isTransportModeAllowed(ModeOfTransport.WALK));

        from.addEdge((short) 80, 1, false, false, false, "Testersensvej");
        edge = from.getNeighbors().get(1);
        assertFalse(edge.isTransportModeAllowed(ModeOfTransport.CAR));
        assertFalse(edge.isTransportModeAllowed(ModeOfTransport.BICYCLE));
        assertFalse(edge.isTransportModeAllowed(ModeOfTransport.WALK));
    }
}