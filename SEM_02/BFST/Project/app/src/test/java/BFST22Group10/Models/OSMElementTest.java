package BFST22Group10.Models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class OSMElementTest {
    private static float[][] latCoords;
    private static float[][] lonCoords;
    private static OSMElement highwayCoords;
    private static OSMElement polygonCoords;
    private static float mainLat;
    private static float mainLon;

    @BeforeAll
    static void setUp() {
        latCoords = new float[2][];
        latCoords[0] = new float[]{0, 1, 2};
        latCoords[1] = new float[]{2, 0};

        mainLat = (float) ((2-0) / 2.0 + 0);

        lonCoords = new float[2][];
        lonCoords[0] = new float[]{3, 4, 5};
        lonCoords[1]  = new float[]{5, 3};

        mainLon = (float) ((5-3) / 2.0 + 3);

        highwayCoords = new OSMElement(latCoords, lonCoords, false);
        polygonCoords = new OSMElement(latCoords, lonCoords, true);

        highwayCoords.setMainTag(Tag.HIGHWAY_LIVING_STREET);
        polygonCoords.setMainTag(Tag.PLACE);
    }

    @Test
    void getters() {
        assertEquals(latCoords, highwayCoords.getLat(), "Wrong latitude coords received on OSMElement.getLat() that is a highway");
        assertEquals(latCoords, polygonCoords.getLat(), "Wrong latitude coords received on OSMElement.getLat() that is a polygon");
        assertEquals(lonCoords, highwayCoords.getLon(), "Wrong longitude coords received on OSMElement.getLon() that is a highway");
        assertEquals(latCoords, polygonCoords.getLat(), "Wrong longitude coords received on OSMElement.getLat() that is a polygon");
        assertEquals(highwayCoords.getMainLat(), mainLat, "Main lat should be calculated from the coordinates");
        assertEquals(polygonCoords.getMainLon(), mainLon, "Main lon should be calculated from the coordinates");
        assertEquals(highwayCoords.getMainTag(), Tag.HIGHWAY_LIVING_STREET, "Main tag should not be changed on an OSM Element");
        assertEquals(polygonCoords.getMainTag(), Tag.PLACE, "Main tag should not be changed on an OSM element");
    }

    @Test
    void booleans() {
        assertTrue(polygonCoords.isPolygon(), "A polygon should return 'true' on OSMElement.isPolygon()");
        assertFalse(highwayCoords.isPolygon(), "Highway should not be a polygon");
        assertFalse(polygonCoords.isRoad(), "A polygon should not be a road");
        assertTrue(highwayCoords.isRoad(), "A highway should be a road");
    }
}
