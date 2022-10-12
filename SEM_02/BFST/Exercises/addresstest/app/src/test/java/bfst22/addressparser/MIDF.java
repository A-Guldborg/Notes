package bfst22.addressparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MIDF {
    @Test 
    void nullString() {
        assertThrows(RuntimeException.class, () -> Address.parse(null));
    }
}
