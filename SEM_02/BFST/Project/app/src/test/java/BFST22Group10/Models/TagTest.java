package BFST22Group10.Models;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    void getTag() {
        Tag expectedTag = Tag.HIGHWAY_PATH;
        assertEquals(expectedTag, Tag.getTag("HIGHWAY_PATH"));
        assertEquals(Tag.UNDEFINED, Tag.getTag("THIS_TAG_DOES_NOT_EXIST"));
    }

    @Test
    void getRequiredZoomLevel() {
        assertEquals(41200, Tag.BUILDING.getRequiredZoomLevel());
        assertEquals(0, Tag.HIGHWAY_MOTORWAY.getRequiredZoomLevel());
    }

    @Test
    void getColor() {
        Color expectedColor = Color.rgb(221, 148, 161, 1);
        assertEquals(expectedColor, Tag.HIGHWAY_MOTORWAY.getColor());
    }

    @Test
    void getLineWidth() {
        double expectedLineWidth = 0.00001;
        assertEquals(expectedLineWidth, Tag.BUILDING.getLineWidth());
    }

    @Test
    void getStandardMaxSpeed() {
        short expectedMaxSpeed = 80;
        Tag tag = Tag.HIGHWAY_PRIMARY;
        assertEquals(expectedMaxSpeed, tag.getStandardMaxSpeed());

        expectedMaxSpeed = 130;
        tag = Tag.HIGHWAY_MOTORWAY;
        assertEquals(expectedMaxSpeed, tag.getStandardMaxSpeed());

        expectedMaxSpeed = 0;
        tag = Tag.BARRIER_FENCE;
        assertEquals(expectedMaxSpeed, tag.getStandardMaxSpeed());

        expectedMaxSpeed = 50;
        tag = Tag.HIGHWAY_RESIDENTIAL;
        assertEquals(expectedMaxSpeed, tag.getStandardMaxSpeed());
    }

    @Test
    void isRoad() {
        Tag expectedTrue = Tag.HIGHWAY_MOTORWAY;
        Tag expectedFalse = Tag.AEROWAY_APRON;
        assertTrue(expectedTrue.isRoad());
        assertFalse(expectedFalse.isRoad());
    }

    @Test
    void isCarAllowed() {
        Tag expectedTrue = Tag.HIGHWAY_PRIMARY;
        Tag expectedFalse = Tag.HIGHWAY_CYCLEWAY;
        assertTrue(expectedTrue.isCarAllowed());
        assertFalse(expectedFalse.isCarAllowed());

        expectedFalse = Tag.AMENITY_PARKING;
        assertFalse(expectedFalse.isCarAllowed());
    }

    @Test
    void isBicycleAllowed() {
        assertTrue(Tag.HIGHWAY_PRIMARY.isBicycleAllowed());
        assertTrue(Tag.HIGHWAY_PATH.isBicycleAllowed());
        assertTrue(Tag.HIGHWAY_CYCLEWAY.isBicycleAllowed());

        assertFalse(Tag.AMENITY_PARKING.isBicycleAllowed());
    }

    @Test
    void isPedestrianAllowed() {
        Tag expectedTrue = Tag.HIGHWAY_PRIMARY;
        Tag expectedFalse = Tag.HIGHWAY_MOTORWAY;
        assertTrue(expectedTrue.isPedestrianAllowed());
        assertFalse(expectedFalse.isPedestrianAllowed());

        expectedTrue = Tag.HIGHWAY_PATH;
        assertTrue(expectedTrue.isPedestrianAllowed());

        expectedFalse = Tag.AMENITY_PARKING;
        assertFalse(expectedFalse.isPedestrianAllowed());
    }

    @Test
    void isPolygon() {
        Tag expectedTrue = Tag.PLACE;
        Tag expectedFalse = Tag.HIGHWAY_MOTORWAY;
        assertTrue(expectedTrue.isPolygon());
        assertFalse(expectedFalse.isPolygon());
    }
}