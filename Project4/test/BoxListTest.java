import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests BoxList class
 * @author Suzanne Balik
 * @author Jessica Young Schmidt
 * @author
 */
public class BoxListTest {

    /** Delta for double tests */
    private static final double DELTA = 0.01;

    /** BoxList 1 for testing */
    private BoxList boxList1;

    /** Monetary amounts for boxes */
    private double[] monetaryAmounts1 = { 1.0, 5.0, 10.0, 25.0 };

     /** BoxList 2 for testing */
    private BoxList boxList2;

    /** Monetary amounts for boxes */
    private double[] monetaryAmounts2 = { 2.0, 4.0, 6.0, 8.0 };
    
    /**
     * Create BoxLists for testing
     */
    @BeforeEach
    public void setUp() {
        boxList1 = new BoxList(monetaryAmounts1);
        boxList2 = new BoxList(monetaryAmounts2);
    }

    /**
     * Tests Preconditions to the game (constructor)
     */
    @Test
    public void testConstructorPreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new BoxList(null));
        assertEquals("Null monetary amounts", e.getMessage(),
                "Correct IllegalArgumentException message");

        double[] noElements = {};
        e = assertThrows(IllegalArgumentException.class,
            () -> new BoxList(noElements));
        assertEquals("Invalid monetary amounts", e.getMessage(),
                "Correct IllegalArgumentException message");

        double[] oneElement = { 2.3 };
        e = assertThrows(IllegalArgumentException.class,
            () -> new BoxList(oneElement));
        assertEquals("Invalid monetary amounts", e.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Tests Preconditions to the game (getValue)
     */
    @Test
    public void testGetValuePreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.getValue(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.getValue(4));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.getValue(5));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Tests Preconditions to the game (isOpne)
     */
    @Test
    public void testIsOpenPreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.isOpen(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.isOpen(4));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.isOpen(5));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Tests Preconditions to the game (open)
     */
    @Test
    public void testOpenPreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.open(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.open(4));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.open(5));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");
    }

    /**
     * Tests Preconditions to the game (shuffle)
     */
    @Test
    public void testShufflePreConditions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> boxList1.shuffle(-1));
        assertEquals("Invalid number of swaps", e.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Tests shuffle that randomizes boxes
     */
    @Test
    public void testShuffle() {
        String initialToString = "0: Open: false Value: 1.0\n"
                + "1: Open: false Value: 5.0\n" + "2: Open: false Value: 10.0\n"
                + "3: Open: false Value: 25.0\n";
        assertEquals(initialToString, boxList1.toString(), "Testing toString");
        boxList1.shuffle(1);
        assertNotSame(initialToString, boxList1.toString(),
                "boxlist should change when shuffled");
    }

    /**
     * Tests getValue for different boxes in boxList1
     */
    @Test
    public void testGetValue1() {
        assertEquals(1.0, boxList1.getValue(0), DELTA,
                "getValue for BoxList 1 0");
        assertEquals(5.0, boxList1.getValue(1), DELTA,
                "getValue for BoxList 1 1");
        assertEquals(10.0, boxList1.getValue(2), DELTA,
                "getValue for BoxList 1 2");
        assertEquals(25.0, boxList1.getValue(3), DELTA,
                "getValue for BoxList 1 3");
    }

    /**
     * Tests getValue for different boxes in boxList2
     */
    @Test
    public void testGetValue2() {
        assertEquals(2.0, boxList2.getValue(0), DELTA,
                "getValue for BoxList 1 0");
        assertEquals(4.0, boxList2.getValue(1), DELTA,
                "getValue for BoxList 1 1");
        assertEquals(6.0, boxList2.getValue(2), DELTA,
                "getValue for BoxList 1 2");
        assertEquals(8.0, boxList2.getValue(3), DELTA,
                "getValue for BoxList 1 3");
    }

    /**
     * Tests isOpen for different boxes in boxList1
     */
    @Test
    public void testIsOpen1() {
        for (int i = 0; i < 4; i++) {
            assertFalse(boxList1.isOpen(i), "isOpen? for boxList1 " + i);
        }
    }

    /**
     * Tests isOpen for different boxes in boxList2
     */
    @Test
    public void testIsOpen2() {
        for (int i = 0; i < 4; i++) {
            assertFalse(boxList2.isOpen(i), "isOpen? for boxList1 " + i);
        }
    }

    /**
     * Tests Open for different boxes in boxList1
     */
    @Test
    public void testOpen1() {
        boxList1.open(0);
        boxList1.open(2);
        assertTrue(boxList1.isOpen(0), "Open? BoxList1 0");
        assertFalse(boxList1.isOpen(1), "Open? BoxList1 1");
        assertTrue(boxList1.isOpen(2), "Open? BoxList1 2");
        assertFalse(boxList1.isOpen(3), "Open? BoxList1 3");
    }

    /**
     * Tests Open for different boxes in boxList2
     */
    @Test
    public void testOpen2() {
        boxList2.open(1);
        boxList2.open(3);
        assertFalse(boxList2.isOpen(0), "Open? BoxList1 0");
        assertTrue(boxList2.isOpen(1), "Open? BoxList1 1");
        assertFalse(boxList2.isOpen(2), "Open? BoxList1 2");
        assertTrue(boxList2.isOpen(3), "Open? BoxList1 3");
    }

    /**
     * Tests the calculation of unopened boxes in boxList1
     */
    @Test
    public void testAverageValueOfUnopenedBoxes1() {
        assertEquals(10.25, boxList1.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 10.25");
        boxList1.open(0);
        boxList1.open(2);
        assertEquals(15.0, boxList1.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 15.0");
        boxList1.open(1);
        boxList1.open(3);
        assertEquals(0, boxList1.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 0");

    }

    /**
     * Tests the calculation of unopened boxes in boxList2
     */
    @Test
    public void testAverageValueOfUnopenedBoxes2() {
        assertEquals(5.0, boxList2.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 5.0");
        boxList2.open(0);
        boxList2.open(2);
        assertEquals(6.0, boxList2.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 6.0");
        boxList2.open(1);
        boxList2.open(3);
        assertEquals(0, boxList2.averageValueOfUnopenedBoxes(), DELTA,
                "Average Value Of Unopened Boxes: 0");

    }

    /**
     * Tests the compaison method of boxes in boxList1
     */
    @Test
    public void testEquals1() {
        assertTrue(boxList1.equals(boxList1),
                "boxList1 equals with same instance");
        BoxList same = new BoxList(monetaryAmounts1);
        assertTrue(boxList1.equals(same),
                "boxList1 equals with different instances");
        assertEquals(boxList1, same,
                "boxList1 equals with different instances");
        same.open(2);
        assertFalse(boxList1.equals(same),
                "boxList1 with same box list with opened box");
        double[] differentAmounts = { 2.0, 3.5, 4.5, 8.0 };
        assertFalse(boxList1.equals(new BoxList(differentAmounts)),
                "boxList1 with different values");
        double[] differentLength = { 1.0, 5.0, 10.0 };
        assertFalse(boxList1.equals(new BoxList(differentLength)),
                "boxList1 with different length");
        assertFalse(boxList1.equals(null), "boxList1 compared to null object");
        assertFalse(boxList1.equals("box list 1"),
                "boxList1 compared to String");
    }

    /**
     * Tests the compaison method of boxes in boxList2
     */
    @Test
    public void testEquals2() {
        assertTrue(boxList2.equals(boxList2),
                "boxList2 equals with same instance");
        BoxList same = new BoxList(monetaryAmounts2);
        assertTrue(boxList2.equals(same),
                "boxList2 equals with different instances");
        assertEquals(boxList2, same,
                "boxList2 equals with different instances");
        same.open(2);
        assertFalse(boxList2.equals(same),
                "boxList2 with same box list with opened box");
        double[] differentAmounts = { 2.0, 3.5, 4.5, 8.0 };
        assertFalse(boxList2.equals(new BoxList(differentAmounts)),
                "boxList2 with different values");
        double[] differentLength = { 1.0, 5.0, 10.0 };
        assertFalse(boxList2.equals(new BoxList(differentLength)),
                "boxList2 with different length");
        assertFalse(boxList2.equals(null), "boxList2 compared to null object");
        assertFalse(boxList2.equals("box list 2"),
                "boxList2 compared to String");
    }

    /**
     * Tests the string output of boxes in boxList1
     */
    @Test
    public void testToString1() {
        String expected = "0: Open: false Value: 1.0\n"
                + "1: Open: false Value: 5.0\n" + "2: Open: false Value: 10.0\n"
                + "3: Open: false Value: 25.0\n";
        assertEquals(expected, boxList1.toString(),
                "toString  after constructed");
        boxList1.open(1);
        boxList1.open(2);
        expected = "0: Open: false Value: 1.0\n" + "1: Open: true Value: 5.0\n"
                + "2: Open: true Value: 10.0\n"
                + "3: Open: false Value: 25.0\n";
        assertEquals(expected, boxList1.toString(),
                "toString  after opening two boxes");
    }

    /**
     * Tests the string output of boxes in boxList2
     */
    @Test
    public void testToString2() {
        String expected = "0: Open: false Value: 2.0\n"
                + "1: Open: false Value: 4.0\n" + "2: Open: false Value: 6.0\n"
                + "3: Open: false Value: 8.0\n";
        assertEquals(expected, boxList2.toString(),
                "toString  after constructed");
        boxList2.open(1);
        boxList2.open(2);
        expected = "0: Open: false Value: 2.0\n" + "1: Open: true Value: 4.0\n"
                + "2: Open: true Value: 6.0\n"
                + "3: Open: false Value: 8.0\n";
        assertEquals(expected, boxList2.toString(),
                "toString  after opening two boxes");
    }
}