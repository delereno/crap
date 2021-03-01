import java.util.ArrayList;
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
     * Constructor creates dicepair
     */
    public DicePair(){
    }
    /*
     * Rolls dice by choosing random values between 1-6
     */
    public void rollDice(){
    roll = (int) (Math.random() * (6 - 1 + 1) + 1);
    roll2 = (int) (Math.random() * (6 - 1 + 1) + 1);
    }
    
    /**
     * returns the rollSum
     */
    public ArrayList<Integer> getFace()
    {
        
        ArrayList<Integer> faces = new ArrayList<Integer>();
        faces.add(roll); faces.add(roll2); faces.add(roll + roll2);
        return faces;
    }
}