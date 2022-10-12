package bfst22.addressparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LANR {
    @Test
    public void ituApartmentTest(){
        Address address = Address.parse("Rued Langaards Vej 7A, 1. th 4000 København S");
        assertEquals(address.street, "Rued Langaards Vej");
        assertEquals(address.house, "7A");
        assertEquals(address.floor, "1.");
        assertEquals(address.side, "th");
        assertEquals(address.postcode, "4000");
        assertEquals(address.city, "København S");
    }
}
