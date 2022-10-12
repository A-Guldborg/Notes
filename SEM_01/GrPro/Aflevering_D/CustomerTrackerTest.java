import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CustomerTrackerTest {
    private MockDB db;
    private CustomerTracker cTracker;
    
    @BeforeEach
    public void setUp() {
        db = new MockDB();
        cTracker = new CustomerTracker(db, 47);
    }
    
    @AfterEach
    public void tearDown() {
        db = null;
        cTracker = null;
    }
    
    @Test
    public void today_returns35() {
        int expected = 35;
        int actual = cTracker.today();
        assertEquals(expected, actual);
    }
    
    @Test
    public void avgThisWeek_returns30() {
        double expected = 30;
        double actual = cTracker.avgThisWeek();
        assertEquals(expected, actual, 0.0001);
    }
    
    @Test
    public void comparedToWeek_given43_returns285() {
        double expected = 2.85;
        double actual = cTracker.comparedToWeek(43);
        assertEquals(expected, actual, 0.01);
    }
    
    @Test
    public void comparedToWeek_given47_returns0() {
        double expected = 0.0;
        double actual = cTracker.comparedToWeek(47);
        assertEquals(expected, actual, 0.0001);
    }
    
    @Test
    public void comparedToWeek_given0_throwsAnException() {
        assertThrows(NullPointerException.class, () -> cTracker.comparedToWeek(0));
    }
    
    @Test
    public void comparedToWeek_given48_throwsAnException() {
        assertThrows(NullPointerException.class, () -> cTracker.comparedToWeek(48));
    }
}