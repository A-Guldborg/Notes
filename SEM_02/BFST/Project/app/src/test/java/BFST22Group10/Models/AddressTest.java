package BFST22Group10.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void valueOfStreetAndHousenumber() {
        Address address = Address.valueOf("Rued Langgaards Vej 7");
        assertEquals("7", address.getHousenumber(), "Rued Langgaards Vej 7 should register 7 as the housenumber");
        assertEquals("Rued Langgaards Vej", address.getStreet(), "Rued Langgaards Vej 7 should register Rued Langgaards Vej as the street name");
        assertNull(address.getCity(), "Empty city in an address should be saved as null");
    }

    @Test
    void valueOfStreetWithApostrophe() {
        String add = "Frederiksberg Allé 11 1621 København";
        Address address = Address.valueOf(add);
        assertEquals(address.toString(), add);
    }

    @Test
    void valueOfStreet() {
        Address address = Address.valueOf("Rued Langgaards Vej");
        assertNull(address.getHousenumber(), "Empty housenumber should return null");
        assertEquals("Rued Langgaards Vej", address.getStreet(), "Rued Langgaards Vej should register the full input as the street name");
    }

    @Test
    void valueOfOneWordStreet() {
        Address address = Address.valueOf("Storkevang");
        assertEquals("Storkevang", address.getStreet(), "A one word streetname and nothing else should register the full input as the street name");
    }

    @Test
    void valueOfFullAddress() {
        Address address = Address.valueOf("Rued Langgaards Vej 7, 2300 København S");
        assertEquals("7", address.getHousenumber(), "Rued Langgaards Vej 7, 2300 København S should register 7 as the housenumber");
        assertEquals("Rued Langgaards Vej", address.getStreet(), "Rued Langgaards Vej 7, 2300 København S should register Rued Langgaards Vej as the street name");
        assertEquals("2300", address.getPostcode(), "Rued Langgaards Vej 7, 2300 København S should register 2300 as the postcode");
        assertEquals("København S", address.getCity(), "Rued Langgaards Vej 7, 2300 København S should register København S as the city");
    }

    @Test
    void valueOfIllegalAddres() {
        Address address = Address.valueOf("321 Hvor er du på vej hen? 123 123 123");
        assertNull(address, "An illegal address should not be created at all");
    }

    @Test
    void toStringWithComma() {
        Address address = Address.valueOf("Rued Langgaards Vej 7, 2300 København S");
        assertEquals("Rued Langgaards Vej 7 2300 København S", address.toString(), "ToString on an address should remove commas");
    }

    @Test
    void toStringWithFloor() {
        Address address = Address.valueOf("Rued Langgaards Vej 7 3tv 2300 København S");
        assertEquals("Rued Langgaards Vej 7 2300 København S", address.toString(), "ToString on an address without commas should not contain floor number");
    }

    @Test
    void gettersAndSetters() {
        Address address = new Address();
        address.setStreet("Rued Langgaards Vej");
        address.setHousenumber("7");
        address.setCity("København S");
        address.setPostcode("2300");
        address.setLat(55.6598860f);
        address.setLon(12.5912350f);

        assertEquals("Rued Langgaards Vej", address.getStreet(), "Address setters and getters should not manipulate with the data");
        assertEquals("7", address.getHousenumber(), "Address setters and getters should not manipulate with the data");
        assertEquals("København S", address.getCity(), "Address setters and getters should not manipulate with the data");
        assertEquals("2300", address.getPostcode(), "Address setters and getters should not manipulate with the data");
        assertEquals(55.6598860, address.getMainLat(), 0.001, "Address setters and getters should not manipulate with the data");
        assertEquals(12.5912350, address.getMainLon(), 0.001, "Address setters and getters should not manipulate with the data");
    }
}