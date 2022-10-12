package BFST22Group10.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    Node node = new Node( 54.9558568f, 11.3669383f);

    @Test
    void getLat() {
        assertEquals(54.9558568f, node.getMainLat());
    }

    @Test
    void getLon() {
        assertEquals(11.3669383f, node.getMainLon());
    }
}