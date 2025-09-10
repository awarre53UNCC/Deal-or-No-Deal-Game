/**
 * Class to store information about box
 * Specifcally if the box is open and its value
 * @author Austin Warren
 */
public class Box {
    
    /** Used to compare two Box objects for equality */
    public static final double DELTA = 0.01;

    /**  A double that represents the monetary value contained in the box */
    private double value;

    /** A boolean that knows whether or not the box is open */
    private boolean isOpen;

    /**
     * Constructor for Box class
     * The field that knows if the box is open should be set to false. 
     * @param value sets value of the box
     * @throws IllegalArgumentException if value is less than a cent
     */
    public Box(double value) {
        if (value < DELTA) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.isOpen = false;
    }

    /**
     * Getter method for the instance field that knows the value of the money stored in the box.
     * It simply returns the instance field.
     * @return a double with the value of the box
     */
    public double getValue() {
        return this.value;
    }

    /**
     * This is a predicate method that returns true if the box is open, false otherwise.
     * @return a boolean either true if open, false if not
     */
    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * This method “opens” the box by setting the appropriate instance field to true.
     */
    public void open() {
        this.isOpen = true;
    }

    /**
     * Method returns whether this Box and o are equal. If o is not a Box, 
     * the method should return false. Boxes are considered equal if they are both open/not open 
     * and if the absolute value of the difference of their values is less than 0.01. 
     * @param o an object to compare boxes
     * @return boolean if boxes are equal (true), or not (false)
     */
    public boolean equals(Object o) {

        if (o instanceof Box) {
            // Creat otherBox to use to compare
            Box otherBox = (Box) o;
            if (otherBox.isOpen() == this.isOpen && 
                Math.abs(otherBox.getValue() - this.value) < DELTA) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * This method returns a String that tells whether or not the box is open 
     * and its monetary value using a specific format.
     * @return a string with isOpen value and monetary value formatted
     */
    public String toString() {
        // Formatted string
        return "Open: " + this.isOpen + " Value: " + this.value;
    }
}   

