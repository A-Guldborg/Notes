package bfst22.addressparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MIKKJ {
   @Test void ituAddress() {
  	var addr = Address.parse("Rued Langgaards Vej    ");
	assertEquals("Rued Langgaards Vej", addr.street);

    }
    @Test void ituFloor() {
	var addr = Address.parse("Rued Langgaards Vej 7, 3. etage til venstre,   , , ,  , , 2300 København S");
	assertEquals("Rued Langgaards Vej", addr.street);
	assertEquals("7", addr.house);
	assertEquals("3", addr.floor);
	assertEquals("etage til venstre", addr.side);
	assertEquals("2300", addr.postcode);
	assertEquals("København S", addr.city);
    }
    @Test void ituSideNoFloor() {
	var addr = Address.parse("Rued Langgaards Vej 7, tv.,   , , ,  , , 2300 København S");
	assertEquals("Rued Langgaards Vej", addr.street);
	assertEquals("7", addr.house);
	assertEquals("tv.", addr.side);
	assertEquals("2300", addr.postcode);
	assertEquals("København S", addr.city);
    }

}
