package bfst22.addressparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TRBJ {
    @Test void itu() {
        var addr = Address.parse("Rued Langgaards Vej 7, 2300 København S");
        assertEquals("Rued Langgaards Vej", addr.street);
        assertEquals("7", addr.house);
        assertEquals("2300", addr.postcode);
        assertEquals("København S", addr.city);
    }
    @Test void ituComma() {
        var addr = Address.parse("Rued Langgaards Vej    7 ,  , 2300     København S");
        assertEquals("Rued Langgaards Vej", addr.street);
        assertEquals("7", addr.house);
        assertEquals("2300", addr.postcode);
        assertEquals("København S", addr.city);
    }
    @Test void ituSimple() {
        var addr = Address.parse("Rued Langgaards Vej 7");
        assertEquals("Rued Langgaards Vej", addr.street);
        assertEquals("7", addr.house);
    }
}
