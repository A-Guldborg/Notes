package bfst22.roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class RomanTest {
    @Test
    void fromRomanIX() {
        assertEquals(Roman.fromRoman("IX"), 9, "fromRoman(\"IX\") should return 9");
    }

    @Test
    void fromRomanEmpty() {
        assertEquals(Roman.fromRoman(""), 0, "Empty string should return 0");
    }

    @Test
    void invalidString() {
        new Roman();
        assertNull(Roman.fromRoman("MDM"), "MDM is not a valid Roman string");
    }

    @Test
    void fromRomanMCMLXVI() {
        assertEquals(Roman.fromRoman("MCMLXVI"), 1966, "MCMLXVI should return 1966");
    }

    @Test
    void fromRomanCXC() {
        assertEquals(Roman.fromRoman("CXC"), 190, "CXC should return 190");
    }

    @Test
    void exhaustive() {
        try {
            Scanner s = new Scanner(new File("roman.txt"));
            while (s.hasNext()) {
                String roman = s.next();
                int value = s.nextInt();
                assertEquals(Roman.fromRoman(roman), value ,roman + " should be " + value + " but is " + Roman.fromRoman(roman));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading roman.txt");
        }
    }
}
