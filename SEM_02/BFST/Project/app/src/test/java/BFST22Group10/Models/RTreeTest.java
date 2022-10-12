package BFST22Group10.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RTreeTest {
    RTree root;
    OSMElement mainElement;
    int elementsInFirstQuadrant = 200;

    @BeforeEach
    void setUp() {
        root = new RTree();
        ArrayList<Pointable> elements = new ArrayList<>();

        float[][] lats = new float[1][];
        float[][] lons = new float[1][];
        lats[0] = new float[]{3f, 5f, 5f, 3f, 3f};
        lons[0] = new float[]{4f, 4f, 2f, 2f, 4f};


        OSMElement element = new OSMElement(lats, lons, true);
        element.setMainTag(Tag.BUILDING);
        for (int i = 0; i < elementsInFirstQuadrant; i++) {
            elements.add(element);
        }
        mainElement = element;


        lats[0] = new float[]{-3f, -5f, -5f, -3f, -3f};
        lons[0] = new float[]{4f, 4f, 2f, 2f, 4f};

        element = new OSMElement(lats, lons, true);
        element.setMainTag(Tag.HIGHWAY_MOTORWAY);
        for (int i = 0; i < 32; i++) {
            elements.add(element);
        }

        lats[0] = new float[]{3f, 5f, 5f, 3f, 3f};
        lons[0] = new float[]{-4f, -4f, -2f, -2f, -4f};

        element = new OSMElement(lats, lons, true);
        element.setMainTag(Tag.HIGHWAY_MOTORWAY);
        for (int i = 0; i < 32; i++) {
            elements.add(element);
        }

        Random ran = new Random();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 5; j++) {
                lats[0][j] = -ran.nextFloat()*10;
                lons[0][j] = -ran.nextFloat()*10;
            }
            element = new OSMElement(lats, lons, false);
            element.setMainTag(Tag.HIGHWAY_MOTORWAY);
            elements.add(element);
        }

        root.addElements(elements, true);
    }

    @Test
    void searchQuery() {
        List<Pointable> queryResult = root.searchQuery(0.1f, 10f, 0.1f, 10f, Tag.BUILDING.getRequiredZoomLevel(), true);

        assertTrue(queryResult.contains(mainElement), "A query for a quadrant should return the elements in that quadrant");
        assertEquals(2 + elementsInFirstQuadrant, queryResult.size(), "A query for a box should return the elements that are in plus the bounding box of the nodes in the tree");
    }

    @Test
    void searchQueryWithZoomLevel() {
        List<Pointable> queryResult = root.searchQuery(0, 100f, 0, 100f, 41199, true);

        assertEquals(1, queryResult.size(), "A query with zoom level of 1 should not return buildings but only the outer boundingbox");
    }
}