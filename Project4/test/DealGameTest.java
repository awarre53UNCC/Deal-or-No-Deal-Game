import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests DealGameTest class
 * @author Suzanne Balik
 * @author Jessica Young Schmidt
 * @author Austin Warren
 */
public class DealGameTest {

    /** Delta for double tests */
    private static final double DELTA = 0.01;

    /** Delta for array of values */
    public static final double[] BOX_VALUES = {0.01, 1, 5, 10, 25, 50, 75, 
        100, 200, 300, 400, 500, 
        750, 1000, 5000, 10000, 
        25000, 50000, 75000, 
        100000, 200000, 300000, 
        400000, 500000, 750000, 
        1000000};

    /** DealGame object for tests */
    private DealGame game;
    
    /**
     * Setup for tests
     * @throws FileNotFoundException for files not found
     */
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        game = new DealGame(true);
        PrintWriter out = new PrintWriter(
                new FileOutputStream(DealGame.HIGH_SCORE_FILE));
        out.print("0.0");
        out.close();
    }

    /**
     * Tests that boxes remeaning each round changes (decreasing from 6)
     */
    @Test
    public void testGetBoxesRemainingToOpenThisRound() {
        String id = "Six boxes to open in the first round";

        int exp = 6;
        int act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Select the player's box and show that there are still 6 boxes
        // to open
        id = "Select the player's box, 6 boxes to open in first round";
        game.selectBox(0);
        exp = 6;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Select the first of the six boxes and check that the count is
        // decreased
        id = "Select first box, 6 boxes left to open in first round";
        game.selectBox(1);
        exp = 5;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Select remaining boxes and ensure that the count fully decreases
        id = "Select 5 more boxes, 0 boxes left to open in first round";
        game.selectBox(2);
        game.selectBox(3);
        game.selectBox(4);
        game.selectBox(5);
        game.selectBox(6);
        exp = 0;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Check end of round
        id = "End of round";
        assertEquals(exp, act, id);

        // Move to next round
        game.startNextRound();
        id = "Five boxes to open in the second round";
        exp = 5;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Select the first of the five boxes and check that the count is
        // decreased
        id = "Select first box, 5 boxes left to open in first round";
        game.selectBox(10);
        exp = 4;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Select remaining boxes and ensure that the count fully decreases
        id = "Select 4 more boxes, 0 boxes left to open in first round";
        game.selectBox(11);
        game.selectBox(12);
        game.selectBox(13);
        game.selectBox(14);
        exp = 0;
        act = game.getBoxesRemainingToOpenThisRound();
        assertEquals(exp, act, id);

        // Check end of round
        id = "End of round";
        assertEquals(exp, act, id);

        // Move to next round
        game.startNextRound();
    }

    /**
     * Tests that boxes opened each round increases after opened box
     */
    @Test
    public void testGetBoxesOpenedThisRound() {
        // Test that there are no open boxes at the start of the game
        String id = "No Boxes open at start of game";
        int exp = 0;
        int act = game.getBoxesOpenedThisRound();
        assertEquals(exp, act, id);

        id = "Box opened after inital box is chosen";
        game.selectBox(1); 
        game.selectBox(2); 
        exp = 1; 
        act = game.getBoxesOpenedThisRound();
        assertEquals(exp, act, id);
    }

    /**
     * Tests playerBoxValue is correct
     */
    @Test
    public void testGetPlayerBoxValue() {
        // Test that the player's box value is 0.01 since the
        // player's box index is initialized to 0
        String id = "Player box value before selecting box";
        double exp = 0.01;
        double act = game.getPlayerBoxValue();
        assertEquals(exp, act, id);

        // that the player's box value changes to something
        // else when the player selects a box.
        id = "Player box value changing after selecting box";
        game.selectBox(1); 
        exp = game.getValueInBox(1);
        act = game.getPlayerBoxValue();
        assertEquals(exp, act, id);
    }

    /**
     * Tests that after box is opened, isBoxOpen is true
     */
    @Test
    public void testIsBoxOpen() {

        // Test that all boxes are closed at the start of the game
        String id = "All boxes closed initially: ";
        String desc = "game.isBoxOpen ";
        for (int i = 0; i < DealGame.NUM_BOXES; i++) {
            assertFalse(game.isBoxOpen(i), id + desc + i);
        }

        // Test that if a certain box is opened, that the game
        // knows it is open. A for loop is NOT the best test
        // here because you want to ensure the specific box you
        // open (via selectBox) is the one that is open.
        id = "Specific box is open: ";
        desc = "game.isBoxOpen ";
        game.selectBox(1); 
        game.selectBox(3);
        assertTrue(game.isBoxOpen(3));
    }

    /**
     * Tests that after boxes are opened, getCurrentOffer changes
     */
    @Test
    public void testGetCurrentOffer() {
        // Test initial current offer - this doesn't match
        // what is actually done in game play since the offer
        // isn't made until the end of the 1st round. That's
        // ok. White box tests focus on the method's functionality,
        // not the method's functionality as part of the overall
        // game.
        String id = "Initial current offer";
        double exp = 13147.75;
        double act = game.getCurrentOffer();
        assertEquals(exp, act, DELTA, id);

        // Test that if the Player's box and the first box
        // is selected, that the current offer changes. You'll
        // want to calculate the expected value by hand before you
        // run the test case (you should figure out the expected
        // values BEFORE running your tests - your code may not
        // be right).
   
        id = "Player's box and first box selected";
        game.selectBox(0);
        game.selectBox(1);

        // Calculate the expected offer using the corrected formula
        // Sum is of all boxes except box 2, 25 boxes left unopened, round 1
        double expectedOffer = (3418415.01 / 25) * game.getRound() / 10.0;

        // Compare the expected offer with the actual offer
        double actualOffer = game.getCurrentOffer();
        assertEquals(expectedOffer, actualOffer, DELTA, id);
    }

    /**
     * Tests that after game is played, high score is updated if 
     * higher than previous high score
     */
    @Test
    public void testGetHighScore() {
        // Start with no high score - do before starting game
        String id = "Default high score";
        // Test that the high score is 0.0
        double exp = 0.0;
        double act = game.getHighScore();
        assertEquals(exp, act, DELTA, id);

        // Test that a new high score is saved by setting
        // a new high score and then checking that the value
        // is saved.
        id = "New high score";
        game.isNewHighScore(20.0);
        exp = 20.0;
        act = game.getHighScore();
        assertEquals(exp, act, DELTA, id);

    }

    /**
     * Tests that after boxes are opened, values are correct
     */
    @Test
    public void testGetValueInBox() {
        // Check that the value in box 0 is 0.01
        String id = "Value in box 0";
        double exp = 0.01;
        double act = game.getValueInBox(0);
        assertEquals(exp, act, DELTA, id);

        // Check that the value in box 1 is 1.0
        id = "Value in box 1";
        exp = 1.0;
        act = game.getValueInBox(1);
        assertEquals(exp, act, DELTA, id);

        // Check that the value in box 13 is 1,000
        id = "Value in box 13";
        exp = 1000.0;
        act = game.getValueInBox(13);
        assertEquals(exp, act, DELTA, id);

        // Check that the value in box 25 is 1,000,000
        id = "Value in box 25";
        exp = 1000000.0;
        act = game.getValueInBox(25);
        assertEquals(exp, act, DELTA, id);
    }

    /**
     * Tests that lower scores don't change highschore, while higher ones do
     */
    @Test
    public void testIsNewHighScore() {
        // Start with no high score - do before starting game
        String id = "New high score";

        // Test that the new score was recorded
        assertTrue(game.isNewHighScore(10.0), id);
        double exp = 10.0;
        double act = game.getHighScore();
        assertEquals(exp, act, DELTA, id);

        // Test either that a new, higher, score is set, or
        // that a lower score is NOT set. The best idea,
        // would be to test both paths.

        id = "Lower score does not set new high score";
        assertFalse(game.isNewHighScore(1.0), id);
        exp = 10.0;
        act = game.getHighScore();
        assertEquals(exp, act, DELTA, id);

        id = "Higher score sets new high score";
        assertTrue(game.isNewHighScore(100.0), id);
        exp = 100.0;
        act = game.getHighScore();
        assertEquals(exp, act, DELTA, id);

    }

    /**
     * Tests that if no box is chosen, hasPlayerChosenBox is false
     */
    @Test
    public void testHasPlayerChosenBox() {
        assertFalse(game.hasPlayerChosenBox(), "No box chosen");
    }

    /**
     * Tests that if the round is not over, isEndOfRound is false
     */
    @Test
    public void testIsEndOfRound() {
        assertFalse(game.isEndOfRound(), "Not end of round");
    }

    /**
     * Tests that correct round number is returned
     */
    @Test
    public void testGetRound() {
        assertEquals(1, game.getRound(), "Get Round Number");
    }

    /**
     * Tests exceptions are thrown correctly with correct message
     */
    @Test
    public void testExceptions() {

        Exception e = assertThrows(IllegalArgumentException.class,
            () -> game.selectBox(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> game.selectBox(26));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> game.isBoxOpen(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> game.isBoxOpen(26));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> game.getValueInBox(-1));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");

        e = assertThrows(IllegalArgumentException.class,
            () -> game.getValueInBox(26));
        assertEquals("Invalid index", e.getMessage(),
                "Correct IllegalArgumentException message");
    }
}
