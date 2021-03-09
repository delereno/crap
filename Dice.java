import java.util.ArrayList;
/**
 * Creates a Dice object
 *
 * @author Del Huang
 * @version 01/03/21
 */
public class Dice {
    // fields (instance variables)
    private int roll; //Allows roll variable to pass between classes
    private static final int MIN = 1;
    private static final int MAX = 6;
    
    /**
     * Constructor creates dicepair
     */
    public Dice() {
        rollDice();
    }
    
    /**
     * Rolls dice by choosing random values between 1-6
     */
    public void rollDice() {
        roll = (int) (Math.random() * MAX + MIN);
    }
    
    /**
     * returns the rollSum
     * @return the rollNum 
     */
    public int getFace() {
        return roll;
    }
}