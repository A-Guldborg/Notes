package BFST22Group10.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    
    @Test
    void findPathsTest(){
        Vertex vertex1 = new Vertex(10.0f, 10.0f);
        Vertex vertex2 = new Vertex(20.0f, 10.0f);
        Vertex vertex3 = new Vertex(10.0f, 20.0f);
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        allVertices.put((long)1, vertex1);
        allVertices.put((long)2, vertex2);
        allVertices.put((long)3, vertex3);
        Model.setAllVertices(allVertices);
        vertex1.addEdge((short) 1, 2, true, true, true, "Vejnavn");
        vertex1.addEdge((short) 1, 3, true, true, true, "Vejnavn");
        PathFinder pathFinder = new PathFinder(vertex1, vertex2, ModeOfTransport.CAR, 3, false);
        assertNotNull(pathFinder.getFullPath());
    }
    
    @Test
    void findShortestPathTest(){
        Vertex vertex1 = new Vertex(10.0f, 10.0f);
        Vertex vertex2 = new Vertex(20.0f, 10.0f);
        Vertex vertex3 = new Vertex(10.0f, 20.0f);
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        allVertices.put((long)1, vertex1);
        allVertices.put((long)2, vertex2);
        allVertices.put((long)3, vertex3);
        Model.setAllVertices(allVertices);
        vertex1.addEdge((short) 1, 2, true, true, true, "Vejnavn");
        vertex1.addEdge((short) 1, 3, true, true, true, "Vejnavn");
        PathFinder pathFinder = new PathFinder(vertex1, vertex2, ModeOfTransport.CAR, 3, false);
        ArrayList<Vertex> path = pathFinder.getFullPath();
        System.out.println("length: " + path.size());
        for (Vertex vertex : path) {
            System.out.println("lat: " + vertex.getMainLat() + " lon: " + vertex.getMainLon());
        }
        assertNotNull(path);
    }
    
    @Test
    void findShortestPathTest2(){
        Vertex vertex1 = new Vertex(0f, 0f);
        Vertex vertex2 = new Vertex(0f, 1f);
        Vertex vertex3 = new Vertex(1f, 1f);
        Vertex vertex4 = new Vertex(1f, 2f);
        Vertex vertex5 = new Vertex(2f, 2f);
        Vertex vertex6 = new Vertex(2f, 3f);
        Vertex vertex7 = new Vertex(3f, 3f);
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        allVertices.put((long)1, vertex1);
        allVertices.put((long)2, vertex2);
        allVertices.put((long)3, vertex3);
        allVertices.put((long)4, vertex4);
        allVertices.put((long)5, vertex5);
        allVertices.put((long)6, vertex6);
        allVertices.put((long)7, vertex7);
        Model.setAllVertices(allVertices);
        vertex1.addEdge((short) 1, 2, true, true, true, "Vejnavn");
        vertex2.addEdge((short) 1, 3, true, true, true, "Vejnavn");
        vertex3.addEdge((short) 1, 4, true, true, true, "Vejnavn");
        vertex4.addEdge((short) 1, 5, true, true, true, "Vejnavn");
        vertex5.addEdge((short) 1, 6, true, true, true, "Vejnavn");
        vertex6.addEdge((short) 1, 7, true, true, true, "Vejnavn");
        vertex1.addEdge((short) 1, 7, true, true, true, "Vejnavn");
        PathFinder pathFinder = new PathFinder(vertex1, vertex7, ModeOfTransport.CAR, 7, false);
        ArrayList<Vertex> path = pathFinder.getFullPath();
        System.out.println("length: " + path.size());
        for (Vertex vertex : path) {
            System.out.println("lat: " + vertex.getMainLat() + " lon: " + vertex.getMainLon());
        }
        assertNotNull(path);
    }

    @Test
    void findShortestPathTest3(){
        Vertex vertex1 = new Vertex(0f, 0f);
        Vertex vertex2 = new Vertex(0f, 1f);
        Vertex vertex3 = new Vertex(1f, 1f);
        Vertex vertex4 = new Vertex(1f, 2f);
        Vertex vertex5 = new Vertex(2f, 2f);
        Vertex vertex6 = new Vertex(2f, 3f);
        Vertex vertex7 = new Vertex(3f, 3f);
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        allVertices.put((long)1, vertex1);
        allVertices.put((long)2, vertex2);
        allVertices.put((long)3, vertex3);
        allVertices.put((long)4, vertex4);
        allVertices.put((long)5, vertex5);
        allVertices.put((long)6, vertex6);
        allVertices.put((long)7, vertex7);
        Model.setAllVertices(allVertices);
        vertex1.addEdge((short) 1, 2, false, true, true, "Vejnavn1");
        vertex2.addEdge((short) 1, 3, true, true, true, "Vejnavn2");
        vertex3.addEdge((short) 1, 4, true, true, true, "Vejnavn3");
        vertex4.addEdge((short) 1, 5, true, true, true, "Vejnavn4");
        vertex5.addEdge((short) 1, 6, true, true, true, "Vejnavn5");
        vertex6.addEdge((short) 1, 7, true, true, true, "Vejnavn5");
        vertex7.addEdge((short) 1, 6, true, true, true, "Vejnavn6");
        PathFinder pathFinder = new PathFinder(vertex1, vertex7, ModeOfTransport.CAR, 7, false);
        ArrayList<Vertex> path =  new ArrayList<>(pathFinder.getFullPath());
        // always adds two vertices twice
        assertEquals(9, path.size());
        assertNotNull(pathFinder.getRouteDescription());
        assertNotNull(path);
        assertNotNull(pathFinder.getVisitedEdgesLats());
        assertNotNull(pathFinder.getVisitedEdgesLons());
    }
    
    @Test
    void testLoopInShortestPath(){
        Vertex vertex1 = new Vertex(0f, 0f);
        Vertex vertex2 = new Vertex(0f, 1f);
        Vertex vertex3 = new Vertex(1f, 1f);
        HashMap<Long, Vertex> allVertices = new HashMap<>();
        allVertices.put((long)1, vertex1);
        allVertices.put((long)2, vertex2);
        allVertices.put((long)3, vertex3);
        Model.setAllVertices(allVertices);
        vertex1.addEdge((short) 1, 2, true, true, true, "Vejnavn");
        vertex2.addEdge((short) 1, 3, true, true, true, "Vejnavn");
        vertex2.addEdge((short) 1, 1, true, true, true, "Vejnavn");
        PathFinder pathFinder = new PathFinder(vertex1, vertex3, ModeOfTransport.CAR, 3, false);
        ArrayList<Vertex> path = pathFinder.getFullPath();
        assertNotNull(path);
    }
    
}