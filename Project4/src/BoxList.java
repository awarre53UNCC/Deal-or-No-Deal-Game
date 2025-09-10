import java.util.Arrays;
import java.util.Random;

/**
 * Class to store information about boxList
 * Specifcally creating a list of boxes
 * @author Austin Warren
 */
public class BoxList {
    
    /** An array of Box objects. Each element in the array 
    refers to a single Box object. */
    private Box[] boxes;

    /**
     * Constructor for BoxList class
     * @param monetaryAmounts an array of monetary values for boxes
     * @throws IllegalArgumentException if the monetaryAmounts array has less than two elements
     */
    public BoxList(double[] monetaryAmounts) {
         
        if (monetaryAmounts == null) {
            throw new IllegalArgumentException("Null monetary amounts");
        }
        else if (monetaryAmounts.length < 2) {
            throw new IllegalArgumentException("Invalid monetary amounts");
        }

        this.boxes = new Box[monetaryAmounts.length];
        
        for (int i = 0; i < monetaryAmounts.length; i++) {
            this.boxes[i] = new Box(monetaryAmounts[i]);
        }
    }
    
    /**
     * Method returns the value of the Box at the given index in the Box array. 
     * @param index an integer for box number
     * @return a double with box value
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the Box array.
     */
    public double getValue(int index) {

        if (index < 0 || index >= boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.boxes[index].getValue();
    }

    /**
     * Method returns true if the Box at the give index in the Box array is open, 
     * false otherwise
     * @param index an integer for box number
     * @return a boolean true if box open, false if not
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the Box array.
     */
    public boolean isOpen(int index) {

        if (index < 0 || index >= boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.boxes[index].isOpen();
    }

    /**
     * Method “opens” the Box at the given index in the Box array.
     * @param index an integer for box number
     * @throws IllegalArgumentException if index is less than zero or 
     * greater or equal to the length of the Box array.
     */
    public void open(int index) {

        if (index < 0 || index >= boxes.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        this.boxes[index].open();
    }

    /**
     * Method returns the average value of the unopened Box’s in the Box Array. 
     * If all of the Box’s are open, it should return 0.
     * @return a boolean true if box open, false if not
     */
    public double averageValueOfUnopenedBoxes() {
        // initialize count and total
        double count = 0.0;
        double total = 0.0;

        for (int i = 0; i < boxes.length; i++) {
            // if a box is opened add to count and add its value to total
            if (this.boxes[i].isOpen() == false) {
                total += this.boxes[i].getValue();
                count += 1.0;
            }
        }
        // if 0 boxes have been opened return 0.0
        if (count == 0.0) {
            return 0.0;
        }
        else {
            return total / count;
        }
    }

    /**
     * Method handles “shuffling the boxes” so that the boxes will be randomly arranged. 
     * This is done by using the Random class to generate random numbers.
     * @param numberOfSwaps an integer for swap amounts
     * @throws IllegalArgumentException if numberOfSwaps is less than zero.
     */
    public void shuffle(int numberOfSwaps) {
            
        if (numberOfSwaps < 0) {
            throw new IllegalArgumentException("Invalid number of swaps");
        }

        // Use random class
        Random randomNumber = new Random();

        // For loop runs till numberOfSwaps
        for (int i = 0; i < numberOfSwaps; i++) {
            // Generate two random integers
            int randomInt = randomNumber.nextInt(boxes.length);
            int randomInt2 = randomNumber.nextInt(boxes.length);

            // Check that the integers are different
            while (randomInt == randomInt2) {
                // if there equal, generate new integer
                randomInt2 = randomNumber.nextInt(boxes.length);
            }

            // Using temp, switch the values of the boxes
            Box temp = boxes[randomInt] ;
            this.boxes[randomInt] = boxes[randomInt2];
            this.boxes[randomInt2] = temp;
        }
    }
        
    /**
     * method returns whether this BoxList and o are equal.
     * If o is not a BoxList, the method should return false. 
     * BoxList’s are considered equal if their arrays of Box objects are equal.
     * @param o an integer for box number
     * @return boolean if boxes are equal (true), or not (false)
     */
    public boolean equals(Object o) {
        if (o instanceof BoxList) {
            BoxList otherBoxList = (BoxList) o;
            // Use the Arrays class deepEquals()
            return Arrays.deepEquals(this.boxes, otherBoxList.boxes);
        }
        else {
            return false;
        }
    }

    /**
     * Method returns a String representation of the BoxList
     * false otherwise
     * @return a string formatted to display box values for each box
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < boxes.length; i++) {
            string += i + ": " + boxes[i].toString() + "\n";
        }
        return string;
    }
}
