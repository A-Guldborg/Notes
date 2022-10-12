package bfst22.factorial;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    Factorial math = new Factorial();

    @Test 
    void factorialOf0() {
        assertEquals(math.factorial(0), 1, "factorial(0) should return 1");
    }

    @Test
    void factorialOfMinus1() {
        assertThrows(BadUserException.class, () -> math.factorial(-1), "factorial(-1) should throw BadUserException");
    }

    @Test
    void factorialOf1() {
        assertEquals(math.factorial(1), 1, "factorial(1) should return 1");
    }

    @Test
    void factorialOf2() {
        assertEquals(math.factorial(5), 120, "factorial(5) should return 120");
    }
}
