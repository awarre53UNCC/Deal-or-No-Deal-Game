import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Box class
 * 
 * @author Suzanne Balik
 * @author Jessica Young Schmidt
 * @author Austin Warren
 */
public class BoxTest {

    /** Delta for double tests */
    private static final double DELTA = 0.01;

    /** Box 1 for testing */
    private Box box1;

    /** Box 1 for testing */
    private Box box2;

    /**
     * Create Boxes for testing
     */
    @BeforeEach
    public void setUp() {
        box1 = new Box(52.80);
        box2 = new Box(9.12);
    }

    /**
     * Tests Preconditions to the game
     */
    @Test
    public void testConstructorPreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new Box(0));
        assertEquals("Invalid value", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class, () -> new Box(-5));
        assertEquals("Invalid value", e.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Tests getValue returns correct value for Box1
     */
    @Test
    public void testGetValueBox1() {
        assertEquals(52.80, box1.getValue(), DELTA, "box1 value");

    }
    
    /**
     * Tests getValue returns correct value for Box2
     */
    @Test
    public void testGetValueBox2() {
        assertEquals(9.12, box2.getValue(), DELTA, "box2 value");

    }

    /**
     * Tests Box1 starts out closed, returning false
     */
    @Test
    public void testIsOpenBox1() {
        assertFalse(box1.isOpen(), "box1 is open");
    }
    
    /**
     * Tests Box2 starts out closed, returning false
     */
    @Test
    public void testIsOpenBox2() {
        assertFalse(box2.isOpen(), "box1 is open");
    }

    /**
     * Tests Box1 after being opened, returning true
     */
    @Test
    public void testOpenBox1() {
        box1.open();
        assertTrue(box1.isOpen(), "box1 is open");
    }

    /**
     * Tests Box2 after being opened, returning true
     */
    @Test
    public void testOpenBox2() {
        box2.open();
        assertTrue(box2.isOpen(), "box2 is open");
    }

    /**
     * Compares boxes similar and equal to box1
     */
    @Test
    public void testEqualsBox1() {
        assertTrue(box1.equals(box1), "box1 equals with same instance");
        assertTrue(box1.equals(new Box(52.80)),
                "box1 equals with different instances");
        assertEquals(box1, new Box(52.80),
                "box1 equals with different instances");
        assertTrue(box1.equals(new Box(52.809)), "box1 with similar value");
        assertFalse(box1.equals(new Box(52.81)),
                "box1 with somewhat different value");
        Box openBox = new Box(52.80);
        openBox.open();
        assertFalse(box1.equals(openBox), "box1 with same value but open");
        Box openDifferentValueBox = new Box(45.2);
        openDifferentValueBox.open();
        assertFalse(box1.equals(openDifferentValueBox),
                "box1 with different value and open");
        assertFalse(box1.equals(null), "box1 compared to null object");
        assertFalse(box1.equals("box 1"), "box1 compared to String");
    }

    /**
     * Compares boxes similar and equal to box2
     */
    @Test
    public void testEqualsBox2() {
        assertTrue(box2.equals(box2), "box2 equals with same instance");
        assertTrue(box2.equals(new Box(9.12)),
                "box2 equals with different instances");
        assertEquals(box2, new Box(9.12),
                "box2 equals with different instances");
        assertTrue(box2.equals(new Box(9.129)), "box2 with similar value");
        assertFalse(box2.equals(new Box(9.13)),
                "box2 with somewhat different value");
        Box openBox = new Box(9.12);
        openBox.open();
        assertFalse(box2.equals(openBox), "box2 with same value but open");
        Box openDifferentValueBox = new Box(45.2);
        openDifferentValueBox.open();
        assertFalse(box2.equals(openDifferentValueBox),
                "box2 with different value and open");
        assertFalse(box2.equals(null), "box2 compared to null object");
        assertFalse(box2.equals("box 2"), "box2 compared to String");
    }

    /**
     * Testing string formatting of values for box1
     */
    @Test
    public void testToStringBox1() {
        assertEquals("Open: false Value: 52.8", box1.toString(),
                "box1 toString");
    }

    /**
     * Testing string formatting of values for box2
     */
    @Test
    public void testToStringBox2() {
        assertEquals("Open: false Value: 9.12", box2.toString(),
                "box2 toString");
    }
}
