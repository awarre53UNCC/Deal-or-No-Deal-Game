import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Class to handle DealGame gameplay
 * @author Austin Warren
 */
public class DealGame {
  
    /** Number of boxes in the game */
    public static final int NUM_BOXES = 26;
  
  
    /** Monetary values that will be used in the game */
    public static final double[] BOX_VALUES = {0.01, 1, 5, 10, 25, 50, 75, 
                                               100, 200, 300, 400, 500, 
                                               750, 1000, 5000, 10000, 
                                               25000, 50000, 75000, 
                                               100000, 200000, 300000, 
                                               400000, 500000, 750000, 
                                               1000000};
  
    /** Number of boxes to be opened in each round */
    public static final int[] BOXES_IN_ROUND = {0, 6, 5, 4, 3, 2, 
                                                1, 1, 1, 1, 1};
                                                
    /** Number of rounds in the game */
    public static final int NUM_ROUNDS = 10;
  
    /** Number of times boxes are swapped during the set up */
    public static final int BOX_SWAPS = 500;
  
    /** Name of the file that contains the high score */
    public static final String HIGH_SCORE_FILE = "highscore.txt";
        
    /** An instance of the BoxList class */
    private BoxList boxList;

    /** An integer which stores the index of the player’s box in the BoxList */
    private int playerBoxIndex;

    /** A boolean variable that knows whether or not the player has chosen a box */
    private boolean hasPlayerChosenBox;

    /** An integer that stores the current round number */
    private int round;

    /** An integer that keeps track of the number of boxes 
        that have been opened in the current round. */
    private int boxesOpenedThisRound;

    /** An integer that keeps track of the total number of boxes 
        that have been opened in the game */
    private int boxesOpenedTotal; 

    /** A double that stores the highest score (highest amount of money won) 
        over all plays of the game. */
    private double highScore;

    /**
     * Constructor for DealGame class, creating the BoxList
     * If the testing parameter is false, the BoxList is shuffled.
     * If the HIGH_SCORE_FILE exists and can be opened, the variable that stores the 
     * highest score is set to the value in the file. Otherwise its set to 0.
     * The current round number is set to 1 and the number of boxes opened in the 
     * current round and overall are set to 0.
     * @param testing a boolean of whether the game is in testing mode
     */
    public DealGame(boolean testing) {

        boxList = new BoxList(BOX_VALUES);

        // only shuffle if DealGame is not in testing mode
        if (!testing) {
            boxList.shuffle(BOX_SWAPS);
        }

        File highScoreFile = new File(HIGH_SCORE_FILE);
        FileInputStream fileByteStream = null;
        Scanner in = null;

        if (highScoreFile.exists()) {
            try {
                // read double from highScoreFile
                fileByteStream = new FileInputStream(highScoreFile);
                in = new Scanner(fileByteStream);
                highScore = in.nextDouble();
            }
            catch (FileNotFoundException e) {
                // if file is not found, no highscore is recorded
                highScore = 0.0;
            }
        }
        // if file dosent exist, no highscore is recorded
        else {
            highScore = 0.0;
        }

        // set up inital values
        round = 1;
        boxesOpenedThisRound = 0;
        boxesOpenedTotal = 0;

        // close if scanner is not null
        if (in != null) {
            in.close();
        }
    }

    /**
     * Getter method for hasPlayerChosenBox
     * @return a boolean true if player has chosen box, false if not
     */
    public boolean hasPlayerChosenBox() {
        return hasPlayerChosenBox;
    }

    /**
     * Method to select a box
     * If the player has not yet chosen a box, the index is stored as the 
     * player’s box index and the variable that keeps track of whether or not the player 
     * has chosen a box is set to true.If the player has already chosen a box, 
     * the Box at the given index in the BoxList is opened and the number
     * of boxes opened in the current round and total number of boxes are incremented.
     * @param index an integer for box number
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the BOX_VALUES array
     */
    public void selectBox(int index) {

        if (index < 0 || index >= BOX_VALUES.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        
        if (hasPlayerChosenBox == false) {
            this.playerBoxIndex = index;
            this.hasPlayerChosenBox = true;
        }
        
        else if (hasPlayerChosenBox == true) {
            boxList.open(index);
            this.boxesOpenedThisRound += 1;
            this.boxesOpenedTotal += 1; 

        }
    }

    /**
     * Method returns the number of boxes remaining to be opened in the current round.
     * @return a integer with remaining number of boxes to open
     */
    public int getBoxesRemainingToOpenThisRound() {
        return BOXES_IN_ROUND[round] - boxesOpenedThisRound;
    }

    /**
     * Method returns the number of boxes opened in the current round.
     * @return a integer with  number of boxes opened in round
     */
    public int getBoxesOpenedThisRound() {
        return boxesOpenedThisRound;
    }

    /**
     * Method returns the current round number.
     * @return a integer with number of rounds
     */
    public int getRound() {
        return round;
    }

    /**
     * Method increments the current round number 
     * and sets the number of boxes opened in the round to 0.
     */
    public void startNextRound() {
        this.round += 1;
        this.boxesOpenedThisRound = 0;
    }

    /**
     * Method returns true if all boxes for the current round have been opened, false otherwise
     * @return a boolean value determining if round is over
     */
    public boolean isEndOfRound() {

        if (boxesOpenedThisRound == BOXES_IN_ROUND[round]) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method returns the value of the player’s box.
     * @return a double value determining box value of first box (players box)
     */
    public double getPlayerBoxValue() {

        return BOX_VALUES[playerBoxIndex];
    }

    /**
     * Method returns true if the Box at the given index in the BoxList is open, 
     * false otherwise. 
     * @param index an integer for box number
     * @return a boolean value determining if box is open/closed.
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the BOX_VALUES array
     */
    public boolean isBoxOpen(int index) {

        if (index < 0 || index >= BOX_VALUES.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.boxList.isOpen(index);
    }
         
    /**
     * Method returns the value of the Box at the given index in the BoxList. 
     * @param index an integer for box number
     * @return a double value determining box's value
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the BOX_VALUES array
     */
    public double getValueInBox(int index) {

        if (index < 0 || index >= BOX_VALUES.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return boxList.getValue(index);
    }
    
    /**
     * Method calculates and returns the banker’s current offer. Calculated as: the average 
     * value of the unopened boxes (including the player’s box) * the current round number / 10.
     * @return a double value determining the banks current offer
     */
    public double getCurrentOffer() {
        return (boxList.averageValueOfUnopenedBoxes() * round) / 10;
    }

    /**
     * Method returns the highest score in all plays of the game.
     * @return a double value for games highscore
     */
    public double getHighScore() {
        return highScore;
    }

    /**
     * Method compares the value to the highest score so far. If the value is greater, 
     * the method returns true and writes the value to the HIGH_SCORE_FILE 
     * (overwriting the file if it already exists) and updates highScore field. 
     * If the value is not greater than the highest score, the method returns false.
     * @param value an double value of the game score 
     * @return a boolean value determining if there is new highscore
     */
    public boolean isNewHighScore(double value) {

        PrintWriter out = null;
        
        if (highScore < value) {
            try {
                // set up printwrite for new highscore and output the value to file
                out = new PrintWriter(new FileOutputStream(HIGH_SCORE_FILE));
                out.print(value);
                this.highScore = value;
                out.close();
            }
            catch (FileNotFoundException e) {
                // Catch Exception
            }
            return true;
        }
        else {
            return false;
        }  
    }
}