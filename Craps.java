import java.util.ArrayList; 
import java.util.concurrent.TimeUnit; 
import java.util.Scanner; 
import java.util.Collections;
/**
 * Driver class that creates the craps game, using Dice and Player objects
 * Creates a list of players, determines the "shooter"
 * Allows player to gamble on the outcome using a given balance
 * Will roll dice, and end the turn if the sum is 7,11,2,3 or 12  
 * Program will loop until dice roll matches INDEX1 roll, or gets a 7 
 * @author Del Huang
 * @version 09/03/21
 */
public class Craps
{
    // fields (instance variables)
    
    // creates a scanner object that can read user input
    static Scanner scanner = new Scanner(System.in);
       
    // creates a boolean that remains true until game finishes
    static boolean running = true; 
    
    // creates a boolean that determines the game's outcome
    static boolean pass = true;
    
    // creates an array for both Player objects and name Strings
    static ArrayList<Player> playerList = new ArrayList<Player>();
    static ArrayList<String> plNameList = new ArrayList<String>();
    
    // creates an int that stores the number of players
    static int playNum = 0;
    static final int INDEX1 = 0; // INDEX1 index num constant
    static final int INDEX2 = 1; // second index num constant
    static final int INDEX3 = 2; // third index num constant
    static final int GAMBLEWIN = 2; // winning multiplier constant
    static final int GAMBLELOSS = -1; // losing multiplier constant
    static final int DELAY = 500; // print delay constant
    /**
     * Creates the Craps Game
     *  @param args all command line arguments given by the user
     */
    public static void main (String [] args) throws Exception {
        
        createGame(); //runs CreateGame method
        gamble(); //runs Gamble method
        
        // creates two dice objects
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        
        // creates an array to store dice face rolls
        ArrayList<Integer> faces = new ArrayList<Integer>();
        
        // rolls dice, stores values and sum in array
        faces.add(dice1.getFace());
        faces.add(dice2.getFace());
        faces.add(faces.get(INDEX1) + faces.get(INDEX2));
        int rollSum = faces.get(INDEX3);
        
        // prints the result of both rolls and their sum
        System.out.println("Your come out roll was a " + 
            faces.get(INDEX1) + " and a " + 
            faces.get(INDEX2) + ", for a total of " + 
            rollSum + "!");
        TimeUnit.MILLISECONDS.sleep(DELAY);
       
        // program will act differently based on roll sum
        switch (rollSum) { 
            
            // if sum is 7 or 11, shooter wins
            case 7: 
            case 11: {   
                System.out.println("Shooter Wins!"); 
                TimeUnit.MILLISECONDS.sleep(DELAY); break;
            }
            
            // if sum is 2, 3, 12 shooter loses
            case 2:
            case 3:
            case 12: {
                System.out.println("Shooter loses!"); 
                TimeUnit.MILLISECONDS.sleep(DELAY);
                pass = false; break;
            }
            // if case doesn't match previous values, rerolls
            default: {
                
                // original roll sum is set as point number
                int pointNum = rollSum; 
                
                // runs until a win or loss outcome is reached
                while (running) { 
                    
                    //rolls dice objects again
                    dice1.rollDice();
                    dice2.rollDice();
                    
                    //clears faces array, adds new roll values
                    faces.clear();
                    faces.add(dice1.getFace());
                    faces.add(dice2.getFace());
                    faces.add(faces.get(INDEX1) + faces.get(INDEX2));
                    rollSum = faces.get(INDEX3);
                    
                    // prints new roll values and sum
                    System.out.println("You rolled a " + 
                        faces.get(INDEX1) + " and a " + faces.get(INDEX2) + 
                        ", for a total of " + rollSum + "!"); 
                    TimeUnit.MILLISECONDS.sleep(DELAY);
                    
                    // if a win or loss outcome is reached, breaks
                    if (rollSum == pointNum) {
                        System.out.println("Shooter wins!"); 
                        TimeUnit.MILLISECONDS.sleep(DELAY); break;    
                    }
                    else if (rollSum == 7) {
                        System.out.println("Out 7! Shooter Loses!"); 
                        TimeUnit.MILLISECONDS.sleep(DELAY); 
                        pass = false; break;
                    }
                    
                }
            }
        }
        
        gamblePayout();
    }
    /**
     * Sets player number, names and shooter role 
     */
    public static void createGame () throws Exception { 
        
        // asks for player number, and stores it as an int variable
        System.out.println("How many players will be playing today?");
        playNum = Integer.valueOf(scanner.nextLine());

        // creates player objects, reads and stores name input in array
        System.out.println(playNum + 
            " players? Alright, please enter their names in turn order.");
        for (int i = 0; i < playNum; i++) {
            String name = scanner.nextLine();
            playerList.add(new Player(name)); 
            plNameList.add(name); 
        } 
        
        // loops until players decide on a shooter
        while (running) {
            System.out.println("Which player will be the shooter?");
            String shooter = scanner.nextLine();
            if (plNameList.contains(shooter)) { //swaps with index 0
                int i = plNameList.indexOf(shooter);
                Collections.swap(playerList, INDEX1, i); 
                Collections.swap(plNameList, INDEX1, i);
                System.out.println("Alright, " + plNameList.get(INDEX1) + 
                    " will be the shooter."); 
                break;
                
            }
            else {  //will loop if input is invalid
                System.out.println("I cannot recognise that name." +  
                    "Make sure that your input is caps sensitive.");
            }
        }
    }
    /**
     * Allows players to gamble on 'Pass' or 'Don't Pass' options
     * Alternatively, they can just choose not to bet
     * Also prints total betting pools
     */
    public static void gamble () throws Exception {
        
        // creates betting pools for each gambling option
        int passPool = 0;
        int noPassPool = 0;
        
        // loop cycles through each player, allowing them to gamble
        for (int i = 0; i < playNum; i++) {
            while (running) {
                
                // asks if player wants to gamble, reads & stores answer
                System.out.println(plNameList.get(i) + 
                    " has a balance of $" + playerList.get(i).getBal() +
                    ". Would they like to make a bet? Yes / No");
                String betChoice = scanner.nextLine();
                
                // lets player bet on 'Pass' or 'Don't Pass'
                if (betChoice.equals("Yes")) {
                    while (running) {
                        System.out.println("Would they like to bet on" + 
                            " 'Pass' or 'Don't Pass'?");
                        String passChoice = scanner.nextLine();
                        if (passChoice.equals("Pass")) {
                            playerList.get(i).betPass();
                            break;
                        }
                        else if (passChoice.equals("Don't Pass")) {
                            break;
                        }
                        else {  // will loop if input is invalid
                            System.out.println("Unrecognisable input." + 
                                "Make sure it is caps sensitive" +
                                " and try again.");
                        }
                    }
                    
                    // player inputs bet amount, int is read and stored 
                    System.out.println("How much would they like to bet?");
                    int betAmount = Integer.valueOf(scanner.nextLine());
                    if (playerList.get(i).checkBal(betAmount)) {
                        playerList.get(i).setBet(betAmount); // bet is set
                        System.out.println("Your bet has been placed!");
                        
                        // bet amounts are added to betting pools
                        if (playerList.get(i).checkPass()) {
                            passPool += betAmount;
                        }
                        else {
                            noPassPool += betAmount;
                        }
                        break;
                    }
                    else {
                        System.out.println("You cannot afford this bet!");
                    }  
                }
                
                // breaks out of loop if player does not want to gamble
                else if (betChoice.equals("No")) { 
                    break;
                }
                
                else {  // will loop if input is invalid
                    System.out.println("I cannot recognise that input." + 
                        " Make sure it is caps sensitive and try again.");
                }   
            }
        }
        
        // prints out the amount in each betting pool
        System.out.println("Currently there is $" + passPool + 
            " on 'Pass' and $" + noPassPool + " on 'Don't Pass'!");
    }  
    /**
     * Updates Player balances based on their inital bets
     * Adds winnings, deducts losses
     */
    public static void gamblePayout () throws Exception {
        // loops for each player, updates their balance
        for (int i = 0; i < playNum; i++) { 
            
            // if player's bet matches outcome, adds winnings
            if (playerList.get(i).checkPass() == (pass)) {
                int addBet = playerList.get(i).checkBet() * GAMBLEWIN;
                playerList.get(i).updateBal(addBet);
                
                // prints new player balance
                System.out.println(plNameList.get(i) + " wins " +
                    addBet + " dollars! They now have a balance of $" +
                    playerList.get(i).getBal() + "!"); 
                TimeUnit.MILLISECONDS.sleep(DELAY);
            }
            
            // if player's bet does not match outcome, deducts losses
            else {
                int addBet = playerList.get(i).checkBet() * GAMBLELOSS;
                playerList.get(i).updateBal(addBet);
                
                // prints new player balance
                System.out.println(plNameList.get(i) + " loses " + addBet +
                    " dollars! They now have a balance of $" + 
                    playerList.get(i).getBal() + "!"); 
                TimeUnit.MILLISECONDS.sleep(DELAY);
            }
        }
    }
}
