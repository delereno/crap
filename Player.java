    
/**
 * Creates a player object
 *
 * @author Del Huang
 * @version 01/03/21
 */
public class Player
{
    // fields (instance variables)
    private String name;
    private boolean shooter = false; // Shooter = true, other = false
    private boolean pass = false; // determined by what the player bets on
    private int bal = 1000; //Current gambling balance
    private int amountBet = 0; //The amount that the player bets for the round
    // Constructor for player objects
    public Player(String playerName){
        name = playerName;
    }
    /*
     *Sets a player to be the shooter
     */
    public void setShooter(){
        shooter = true;
    }
    /**
     * returns the player name
     */
    public String getName()
    {
        return name;
    }
    
    
}
