    
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
    private boolean pass = false; // determined by what the player bets on
    private boolean canAfford;
    private int bal = 1000; //Current gambling balance
    private int amountBet = 0; //The amount that the player bets for the round
    // Constructor for player objects
    public Player(String playerName){
        name = playerName;
    }
    /**
     * returns the player name
     */
    public String getName()
    {
        return name;
    }
    /**
     * returns the player balance
     */
    public int getBal()
    {
        return bal;
    }
    /**
     * checks if the player can afford their bet, if they can returns true
     */
    public boolean checkBal(int betAmount)
    {
        return (betAmount <= bal);
    }
    /**
     * sets the player's bet as 'pass'
     */
    public void betPass()
    {
        pass = true;
    }
    /**
     * checks if player bet on pass
     */
    public boolean checkPass()
    {
        return pass;
    }
    /**
     * sets the amount bet by the player
     */
    public void setBet(int betAmount)
    {
        amountBet = betAmount;
    }
    /**
     * returns bet amount
     */
    public int checkBet()
    {
        return amountBet;
    }
    /**
     * updates the player's balance
     */
    public void updateBal(int addBet)
    {
        bal += addBet;
    }
    
    
}
