import java.util.Arrays;
/**
 * Creates a Dice pair object
 *
 * @author Del Huang
 * @version 01/03/21
 */
public class DicePair
{
    // fields (instance variables)
    private int face; //Current side showing
    private int roll; //Allows roll variable to pass between classes
    private int roll2;
    /*
     * Flip this coin by randomly choosing a face value
     */
    public void rollDice(){
    roll = (int) (Math.random() * (6 - 1 + 1) + 1);
    roll2 = (int) (Math.random() * (6 - 1 + 1) + 1);
    }
    
    /**
     * returns the rollSum
     */
    public int[] getFace()
    {
        int[] faces = {roll, roll2, roll + roll2}; 
        return faces;
    }
}