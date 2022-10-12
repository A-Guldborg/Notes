package BFST22Group10.Models;

import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.OS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KDTreeTest {
    KDTree root;
    ArrayList<Pointable> elements;

    @BeforeEach
    void setUp(){
        root = new KDTree();
        elements = new ArrayList<>();
        for (int i = 0; i < 1002; i++) {
            elements.add(new Vertex(-i, i));
        }
        root.addElements(elements, true);
    }

    @Test
    void getRoot(){
        assertEquals( 501, root.getRoot().getMainLon());
        assertEquals( -501, root.getRoot().getMainLat());
    }

    @Test
    void getLeftChild(){
        assertEquals( 501, root.getLeftChild().getRoots().size());
    }

    @Test
    void getRightChild(){
        assertEquals( 500, root.getRightChild().getRoots().size());
    }

    @Test
    void searchQuery() {
        root = new KDTree();
        elements = new ArrayList<>();
        for (int i = 0; i < 1002; i++) {
            elements.add(new OSMElement(new float[][]{{i}}, new float[][]{{i}}, false));
        }
        root.addElements(elements, false);
        List<Pointable> search = root.searchQuery(0,1002, 0, 1002, 1, true);
        // plus the debug line
        assertEquals(elements.size()+1, search.size());
    }

    @Test
    void searchQuery2() {
        root = new KDTree();
        elements = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            elements.add(new OSMElement(new float[][]{{i}}, new float[][]{{i}}, false));
        }
        root.addElements(elements, false);
        List<Pointable> search = root.searchQuery(-10,-5, -10, -5, 1, true);
        assertEquals(0, search.size());
    }

    @Test
    void nearestNeighborSearch() {
        Vertex test = (Vertex)root.nearestNeighborSearch(400.1f, -399.9f, true);
        assertEquals( -400, test.getMainLat());
        assertEquals( 400, test.getMainLon());
    }

    @Test
    void nearestNeighborSearchNull() {
        KDTree nullRoot = new KDTree();
        assertNull(nullRoot.nearestNeighborSearch(400.1f, -399.9f, true));
    }

    @Test
    void nearestNeighborSearch2() {
        root = new KDTree();
        ArrayList<Pointable> elements = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            elements.add(new Vertex(-i, i));
        }
        root.addElements(elements, true);
        Vertex test = (Vertex)root.nearestNeighborSearch(1.1f, -0.5f, false);
        assertEquals(1, test.getMainLon());
        assertEquals(-1, test.getMainLat());
    }

    @Test
    void nearestNeighborSearch3() {
        Vertex test = (Vertex)root.nearestNeighborSearch(502, 1000, true);
        assertEquals(0, test.getMainLon());
        assertEquals(0, test.getMainLat());
    }

    @Test
    void nearestNeighborSearch4() {
        root = new KDTree();
        ArrayList<Pointable> elements = new ArrayList<>();
        elements.add(new Vertex(0, 0));
        root.addElements(elements, false);
        Vertex test = (Vertex)root.nearestNeighborSearch(10, 10, true);
        assertEquals(0, test.getMainLon());
        assertEquals(0, test.getMainLat());
    }

    @Test
    void nearestNeighborSearch5() {
        root = new KDTree();
        ArrayList<Pointable> elements = new ArrayList<>();
        elements.add(new Vertex(50, 2));
        elements.add(new Vertex(0, 1));
        root.addElements(elements, false);
        Vertex test = (Vertex)root.nearestNeighborSearch(2, 50, true);
        assertEquals(2, test.getMainLon());
        assertEquals(50, test.getMainLat());
    }

    @Test
    void addElements(){
        KDTree testRoot = new KDTree();
        ArrayList<Pointable> testElements = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testElements.add(new Vertex(-i, i));
        }
        testRoot.addElements(testElements, false);
        assertEquals(1000,testRoot.getRoots().size());
    }

    @Test
    void addElements2(){
        KDTree testRoot2 = new KDTree();
        ArrayList<Pointable> testElements2 = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            testElements2.add(new Vertex(-i, i));
        }
        testRoot2.addElements(testElements2, false);
        assertEquals(1,testRoot2.getRoots().size());
    }

    @Test
    void addElements3(){
        KDTree testRoot3 = new KDTree();
        ArrayList<Pointable> testElements3 = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            testElements3.add(new Vertex(-i, i));
        }
        testRoot3.addElements(testElements3, true);
        assertEquals(1,testRoot3.getRoots().size());
    }
}