package BFST22Group10.Models;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Locale;

public enum Tag implements Serializable {
    // PLACE
    PLACE                           (Color.rgb(242, 243, 227, 1), 0, 0.00001, true),

    // BARRIER
    BARRIER_HEDGE                   (Color.rgb(179, 209, 161, 1), 41200, 0.000005, false),
    BARRIER_FENCE                   (Color.rgb(178, 177, 177, 1), 41200, 0.000005, false),

    // LANDUSE
    LANDUSE_RESIDENTIAL             (Color.rgb(225, 225, 225, 1), 2500, 0.00001, true),
    LANDUSE_COMMERCIAL              (Color.rgb(238, 218, 217, 1), 8000, 0.00001, true),
    LANDUSE_RETAIL                  (Color.rgb(248, 214, 209, 1), 8000, 0.00001, true),
    LANDUSE_INDUSTRIAL              (Color.rgb(233, 219, 232, 1), 2500, 0.00001, true),
    LANDUSE_RAILWAY                 (Color.rgb(233, 219, 232, 1), 8000, 0.00001, true),
    LANDUSE_GARAGES                 (Color.rgb(223, 221, 207, 1), 41200, 0.00001, true),
    LANDUSE_CEMETERY                (Color.rgb(176, 202, 177, 1), 8000, 0.00001, true),
    LANDUSE_BROWNFIELD              (Color.rgb(199, 199, 182, 1), 8000, 0.00001, true),
    LANDUSE_CONSTRUCTION            (Color.rgb(199, 199, 182, 1), 2500, 0.00001, true),
    LANDUSE_LANDFILL                (Color.rgb(182, 182, 148, 1), 10000, 0.00001, true),
    LANDUSE_BASIN                   (Color.rgb(178, 210, 221, 1), 5500, 0.00001, true),
    LANDUSE_RESERVOIR               (Color.rgb(178, 210, 221, 1), 5500, 0.00001, true),
    LANDUSE_SALT_POND               (Color.rgb(178, 210, 221, 1), 2500, 0.00001, true),
    LANDUSE_GREENHOUSE_HORTICULTURE (Color.rgb(238, 240, 216, 1), 5500, 0.00001, true),
    LANDUSE_ALLOTMENTS              (Color.rgb(208, 225, 196, 1), 5500, 0.00001, true),
    LANDUSE_ORCHARD                 (Color.rgb(183, 223, 167, 1), 5500, 0.00001, true),
    LANDUSE_PLANT_NURSERY           (Color.rgb(183, 223, 167, 1), 5500, 0.00001, true),
    LANDUSE_FOREST                  (Color.rgb(179, 209, 161, 1), 500, 0.00001, true),
    LANDUSE_VINEYARD                (Color.rgb(183, 223, 167, 1), 5500, 0.00001, true),
    LANDUSE_QUARRY                  (Color.rgb(195, 194, 194, 1), 5500, 0.00001, true),
    LANDUSE_RELIGIOUS               (Color.rgb(195, 194, 194, 1), 5500, 0.00001, true),
    LANDUSE_MILITARY                (Color.rgb(255, 0, 0, 0.1), 5500, 0.00001, true),
    LANDUSE_GRASS                   (Color.rgb(211, 234, 182, 1), 5500, 0.00001, true),
    LANDUSE_MEADOW                  (Color.rgb(211, 234, 182, 1), 5500, 0.00001, true),
    LANDUSE_VILLAGE_GREEN           (Color.rgb(211, 234, 182, 1), 5500, 0.00001, true),
    LANDUSE_TREE_ROW                (Color.rgb(176, 205, 165, 1), 5500, 0.00001, true),
    LANDUSE_FARMYARD                (Color.rgb(234, 214, 183, 1), 41200, 0.00001, true),
    LANDUSE_GREENFIELD              (Color.rgb(238, 240, 216, 1), 2500, 0.00001, true),

    // LEISURE
    LEISURE_SWIMMING_POOL           (Color.rgb(178, 210, 221, 1), 41200, 0.00001, true),
    LEISURE_GARDEN                  (Color.rgb(211, 234, 182, 1), 8000, 0.00001, true),
    LEISURE_PARK                    (Color.rgb(213, 246, 205, 1), 5500, 0.00001, true),
    LEISURE_PLAYGROUND              (Color.rgb(229, 251, 228, 1), 10000, 0.00001, true),
    LEISURE_FITNESS_STATION         (Color.rgb(229, 251, 228, 1), 25000, 0.00001, true),
    LEISURE_DOG_PARK                (Color.rgb(229, 251, 228, 1), 8000, 0.00001, true),
    LEISURE_SPORTS_CENTRE           (Color.rgb(229, 251, 228, 1), 8000, 0.00001, true),
    LEISURE_STADIUM                 (Color.rgb(229, 251, 228, 1), 5500, 0.00001, true),
    LEISURE_PITCH                   (Color.rgb(181, 223, 204, 1), 5500, 0.00001, true),
    LEISURE_TRACK                   (Color.rgb(181, 223, 204, 1), 8000, 0.00001, true),
    LEISURE_GOLF_COURSE             (Color.rgb(227, 245, 197, 1), 5500, 0.00001, true),

    // NATURAL
    NATURAL_COASTLINE               (Color.rgb(242, 243, 227, 1), 2500, 0.00001, true),
    NATURAL_REEF                    (Color.rgb(170, 210, 221, 1), 8000, 0.00001, true),
    NATURAL_WOOD                    (Color.rgb(179, 209, 161, 1), 4000, 0.00001, true),
    NATURAL_GRASSLAND               (Color.rgb(211, 234, 182, 1), 4000, 0.00001, true),
    NATURAL_SCRUB                   (Color.rgb(203, 215, 175, 1), 5500, 0.00001, true),
    NATURAL_HEATH                   (Color.rgb(215, 217, 165, 1), 5500, 0.00001, true),
    NATURAL_BEACH                   (Color.rgb(253, 242, 192, 1), 10000, 0.00001, true),
    NATURAL_SAND                    (Color.rgb(234, 224, 194, 1), 10000, 0.00001, true),
    NATURAL_BARE_ROCK               (Color.rgb(230, 224, 217, 1), 5500, 0.00001, true),
    NATURAL_SCREE                   (Color.rgb(234, 228, 220, 1), 5500, 0.00001, true),
    NATURAL_TREE_ROW                (Color.rgb(179, 209, 161, 1), 5500, 0.00001, true),
    NATURAL_MUD                     (Color.rgb(228, 220, 211, 1), 5500, 0.00001, true),
    NATURAL_BAY                     (Color.LIGHTBLUE, 5500, 0.00001, true),

    // WATERWAY
    WATERWAY_DITCH                  (Color.rgb(178, 210, 221, 1), 10000, 0.00001, false),
    WATERWAY_CANAL                  (Color.rgb(178, 210, 221, 1), 10000, 0.00001, false),

    // NATURAL
    NATURAL_WATER                   (Color.LIGHTBLUE, 500, 0.00001, true),

    // MAN_MADE
    MAN_MADE_SILO                   (Color.rgb(241, 221, 190, 1), 41200, 0.00001, true),
    MAN_MADE_BREAKWATER             (Color.rgb(170, 170, 170, 1), 41200, 0.00001, true),
    MAN_MADE_PIER                   (Color.rgb(242, 239, 234, 1), 41200, 0.00001, true),

    // TOURISM
    TOURISM_CAMP_SITE               (Color.rgb(225, 245, 191, 1), 10000, 0.00001, true),

    // RUNWAYS
    AEROWAY_APRON                   (Color.rgb(218, 218, 224, 1), 4000, 0.00001, true),
    AEROWAY_TAXIWAY                 (Color.rgb(187, 187, 204, 1), 4000, 0.00005, false),
    AEROWAY_RUNWAY                  (Color.rgb(187, 187, 204, 1), 4000, 0.0001, false),

    // AMENITY
    AMENITY_PARKING                 (Color.rgb(238, 238, 238, 1), 10000, 0.00001, true),
    AMENITY_GRAVE_YARD              (Color.rgb(215, 208, 202, 1), 10000, 0.00001, true),
    AMENITY_RECYCLING               (Color.rgb(242, 239, 234, 1), 10000, 0.00001, true),
    AMENITY_PLACE_OF_WORSHIP        (Color.rgb(194, 183, 172, 1), 10000, 0.00001, true),

    // RAILS
    RAILWAY_RAIL                    (Color.rgb(85, 85, 85, 1), 10000, 0.000023, false),

    // BUILDING (All types of buildings are equal)
    BUILDING                        (Color.rgb(215, 208, 202, 1), 41200, 0.00001, true),

    // HIGHWAY
    HIGHWAY_MOTORWAY                (Color.rgb(221, 148, 161, 1), 0, 0.00004, (short) 130, true, false, false),
    HIGHWAY_MOTORWAY_LINK           (Color.rgb(221, 148, 161, 1), 0, 0.00004, (short) 130, true, false, false),
    HIGHWAY_TRUNK                   (Color.rgb(221, 148, 161, 1), 0, 0.000018, (short) 80, true, false, false),
    HIGHWAY_TRUNK_LINK              (Color.rgb(221, 148, 161, 1), 0, 0.000018, (short) 80, true, false, false),
    HIGHWAY_PRIMARY                 (Color.rgb(242, 195, 175, 1), 0, 0.000018, (short) 80, true, true, true),
    HIGHWAY_PRIMARY_LINK            (Color.rgb(242, 195, 175, 1), 0, 0.000018, (short) 80, true, true, true),
    HIGHWAY_SECONDARY               (Color.rgb(248, 223, 184, 1), 500, 0.000017, (short) 80, true, true, true),
    HIGHWAY_SECONDARY_LINK          (Color.rgb(248, 223, 184, 1), 500, 0.000017, (short) 80, true, true, true),
    HIGHWAY_TERTIARY                (Color.rgb(254, 254, 254, 1), 2500, 0.000016, (short) 80, true, true, true),
    HIGHWAY_TERTIARY_LINK           (Color.rgb(254, 254, 254, 1), 2500, 0.000016, (short) 80, true, true, true),
    HIGHWAY_UNCLASSIFIED            (Color.rgb(254, 254, 254, 1), 10000, 0.000023, (short) 80, true, true, true),
    HIGHWAY_RESIDENTIAL             (Color.rgb(254, 254, 254, 1), 10000, 0.000023, (short) 50, true, true, true),
    HIGHWAY_SERVICE                 (Color.rgb(254, 254, 254, 1), 25000, 0.000009, (short) 50, true, true, true),
    HIGHWAY_LIVING_STREET           (Color.rgb(254, 254, 254, 1), 25000, 0.000009, (short) 15, true, true, true),
    HIGHWAY_PEDESTRIAN              (Color.rgb(221, 221, 232, 1), 41200, 0.000009, (short) 15, false, false, true), // It is legal to drive but some places have restrictions which are not represented in OSM file, including restrictions for bikes
    HIGHWAY_FOOTWAY                 (Color.rgb(221, 221, 232, 1), 41200, 0.000009, (short) 0, false, true, true),
    HIGHWAY_PATH                    (Color.rgb(221, 221, 232, 1), 41200, 0.000009, (short) 0, false, true, true),
    HIGHWAY_TRACK                   (Color.rgb(221, 221, 232, 1), 41200, 0.000009, (short) 20, false, true, true),
    HIGHWAY_CYCLEWAY                (Color.rgb(211, 211, 232, 1), 41200, 0.00001, (short) 0, false, true, true),

    // ROUTE
    ROUTE_FERRY                     (Color.rgb(117, 123, 241, 1), 2500, 0.00001, (short) 80, true, true, true),
    ROUTE_HIKING                    (Color.rgb(255, 0, 0, 0), 25000, 0.00001, (short) 0, false, false, true),
    ROUTE_BICYCLE                   (Color.rgb(255, 0, 0, 0), 25000, 0.00001, (short) 0, false, true, true),

    // DEBUG
    DEBUG                           (Color.rgb(255, 0, 0, 1), 0, 0.00001, false),


    // Placeholder tag: If we cannot find the tag we can use this bright pink.
    // If we find a lot of areas with this color, or if a big or important area has this color,
    // we can investigate the tag and add it later.
    UNDEFINED                       (Color.rgb(255, 33, 233, 0), 2500, 0.00001, false);

    public static final long serialVersionUID = 13;
    private Color color;
    private int requiredZoomLevel;
    private double lineWidth;
    private final boolean isPolygon;
    private final boolean isRoad;
    private final short maxSpeed;
    private boolean carAllowed;
    private boolean bicycleAllowed;
    private boolean pedestrianAllowed;

    Tag(Color color, int requiredZoomLevel, double lineWidth, boolean isPolygon) {
        setStandardInformation(color, requiredZoomLevel, lineWidth);
        this.isPolygon = isPolygon;
        this.maxSpeed = 0;
        this.isRoad = false;
    }

    Tag(Color color, int requiredZoomLevel, double lineWidth, short maxSpeed, boolean carAllowed, boolean bicycleAllowed, boolean pedestrianAllowed) {
        setStandardInformation(color, requiredZoomLevel, lineWidth);
        this.isPolygon = false;
        this.maxSpeed = maxSpeed;
        this.isRoad = true;
        this.carAllowed = carAllowed;
        this.bicycleAllowed = bicycleAllowed;
        this.pedestrianAllowed = pedestrianAllowed;
    }

    private void setStandardInformation(Color color, int requiredZoomLevel, double lineWidth) {
        this.color = color;
        this.requiredZoomLevel = requiredZoomLevel;
        this.lineWidth = lineWidth;
    }

    public static Tag getTag(String tagName) {
        tagName = tagName.toUpperCase(Locale.ROOT);
        try {
            return Tag.valueOf(tagName);
        } catch (IllegalArgumentException e) {
            if (tagName.startsWith("BUILDING"))   return BUILDING;
            if (tagName.equals("PLACE_PENINSULA") || tagName.equals("PLACE_ISLAND") || tagName.equals("PLACE_ISLET"))      return PLACE;
            return Tag.UNDEFINED; // doesn't exist, necessary in case an OSM tag is not defined here but does not draw
        }
    }

    public int getRequiredZoomLevel() {
        // Sort list of members by the zoom level, so we can easily paint just the necessary elements in the correct order
        return this.requiredZoomLevel;
    }

    public Color getColor() {
        return this.color;
    }

    public double getLineWidth() {
        return lineWidth; // TODO: Give all tags a lineWidth, i.e. how thick is a road etc.
    }

    public boolean isPolygon() {
        return isPolygon;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public short getStandardMaxSpeed() {
        return maxSpeed;
    }

    public boolean isCarAllowed() {
        return carAllowed;
    }

    public boolean isBicycleAllowed() {
        return bicycleAllowed;
    }

    public boolean isPedestrianAllowed() {
        return pedestrianAllowed;
    }
}