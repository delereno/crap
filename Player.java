    
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
    
    /** Constructor for player objects
     * @param playerName name of player
     */
    public Player(String playerName) {
        name = playerName;
    }
    
    /**
     * @return player name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the player balance
     */
    public int getBal() {
        return bal;
    }
    
    /**
     * checks if the player can afford their bet, if they can returns true
     * @param betAmount the amount bet
     * @return true if the player can afford the bet
     */
    public boolean checkBal(int betAmount)
    {
        return (betAmount <= bal);
    }
    
    /**
     * updates the player's balance
     * @param addBet the amount won / lost by the player
     */
    public void updateBal(int addBet)
    {
        bal += addBet;
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
     * @return true if they bet pass
     */
    public boolean checkPass() {
        return pass;
    }
    
    /**
     * sets the amount bet by the player
     * @param betAmount the amount bet by player
     */
    public void setBet(int betAmount) {
        amountBet = betAmount;
    }
    
    /**
     * @return bet amount
     */
    public int checkBet()
    {
        return amountBet;
    }
    
    
    
}
