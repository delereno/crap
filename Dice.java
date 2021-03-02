import java.util.ArrayList;
/**
 * Creates a Dice object
 *
 * @author Del Huang
 * @version 01/03/21
 */
public class Dice {
    // fields (instance variables)
    private int face; //Current side showing
    private int roll; //Allows roll variable to pass between classes
    /**
     * Constructor creates dicepair
     */
    public Dice() { 
    }
    /**
     * Rolls dice by choosing random values between 1-6
     */
    public void rollDice() {
        roll = (int) (Math.random() * 6 + 1);
    }
    
    /**
     * returns the rollSum
     * @return the rollNum 
     */
    public int getFace() {
        return roll;
    }
}