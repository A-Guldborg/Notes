package BFST22Group10.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest {
    Model model;

    @BeforeEach
    void setUp(){
        String prefix = "src/main/resources";
        String file = "/Fejø.osm";
        InputStream stream = this.getClass().getResourceAsStream(file);
        model = new Model(stream, prefix+file);
    }

    @Test
    void getMinLon(){
        float expected = 11.3489000f;
        assertEquals(model.getMinLon(), expected);
    }

    @Test
    void getMinLat(){
        float expected = 54.9205000f;
        assertEquals(model.getMinLat(), expected);
    }

    @Test
    void getMaxLon(){
        float expected = 11.4746000f;
        assertEquals(model.getMaxLon(), expected);
    }

    @Test
    void getMaxLat(){
        float expected = 54.9690000f;
        assertEquals(model.getMaxLat(), expected);
    }

    @Test
    void getLatLonProportions() {
        float expected = 0.56f;
        assertEquals(expected, model.getLatLonProportions(), 0.015);
    }

    @Test
    void IsIsland(){
        assertTrue(model.isIsland());
    }

    @Test
    void getIsland(){
        List<OSMElement> actualIslands = model.getIslands();
        assertEquals(actualIslands.size(), 2);
        assertTrue(actualIslands.get(0).isPolygon());
        assertTrue(actualIslands.get(1).isPolygon());
    }
}
