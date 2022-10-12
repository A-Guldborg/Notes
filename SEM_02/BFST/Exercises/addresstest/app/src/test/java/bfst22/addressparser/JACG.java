package bfst22.addressparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JACG {

    @Test void egenAdresse() {
        var addr = Address.parse("Høje Tåstrup Boulevard 49,2,5, 2630 Tåstrup");
        assertEquals("Høje Tåstrup Boulevard", addr.street);
        assertEquals("49", addr.house);
        assertEquals("2",addr.floor);
        assertEquals("5",addr.side);
        assertEquals("2630", addr.postcode);
        assertEquals("Tåstrup", addr.city);
    }
    @Test void egenAdresseMedSideSomBogstaver() {
        var addr = Address.parse("Høje Tåstrup Boulevard 49,2,th, 2630 Tåstrup");
        assertEquals("Høje Tåstrup Boulevard", addr.street);
        assertEquals("49", addr.house);
        assertEquals("2",addr.floor);
        assertEquals("th",addr.side);
        assertEquals("2630", addr.postcode);
        assertEquals("Tåstrup", addr.city);
    }

    @Test void medDanskeTegn() {
        var addr = Address.parse("Øksen Bøksen 5000,2,th, 1237 Åkande");
        assertEquals("Øksen Bøksen", addr.street);
        assertEquals("5000", addr.house);
        assertEquals("2",addr.floor);
        assertEquals("th",addr.side);
        assertEquals("1237", addr.postcode);
        assertEquals("Åkande", addr.city);
    }
}
