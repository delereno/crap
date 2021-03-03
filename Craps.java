import java.util.ArrayList; 
import java.util.concurrent.TimeUnit; 
import java.util.Scanner; 
import java.util.Collections;
/**
 * Driver class that creates the craps game, using DicePair and Player objects
 * Will roll dice, and end the turn if the sum is 7,11,2,3 or 12  
 * Program will loop until dice roll matches first roll, or gets a 7 
 * @author Del Huang
 * @version 02/03/21
 */
public class Craps
{  
    /**
     * Creates the Craps Game
     *  @param args all command line arguments given by the user
     */
    public static void main (String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; 
        boolean playing = true;
        System.out.println("How many players will be playing today?");
        int playNum = Integer.valueOf(scanner.nextLine());
        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<String> plNameList = new ArrayList<String>();
        System.out.println(playNum + 
            " players? Alright, please enter their names in turn order.");
        for (int i = 0; i < playNum; i++) {
            String name = scanner.nextLine();
            playerList.add(new Player(name)); 
            plNameList.add(name); 
        } 
        while (playing) {    
            while (running) {
                System.out.println("Which player will be the shooter?");
                String shooter = scanner.nextLine();
                if (plNameList.contains(shooter)) {
                    int i = plNameList.indexOf(shooter);
                    Collections.swap(playerList, 0, i);
                    Collections.swap(plNameList, 0, i);
                    System.out.println("Alright, " + plNameList.get(0) + 
                        " will be the shooter."); 
                    break;
                    
                }
                else {
                    System.out.println("I cannot recognise that name." +  
                        "Make sure that your input is caps sensitive.");
                }
            }
            int passPool = 0;
            int noPassPool = 0;
            for (int i = 0; i < playNum; i++) {
                while (running) {
                    System.out.println(plNameList.get(i) + 
                        " has a balance of $" + playerList.get(i).getBal() +
                        ". Would they like to make a bet? Yes / No");
                    String betChoice = scanner.nextLine();
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
                            else {
                                System.out.println("Unrecognisable input." + 
                                    "Make sure it is caps sensitive" +
                                    " and try again.");
                            }
                        }
                    }
                    else if (betChoice.equals("No")) {
                        break;
                    }
                    else {
                        System.out.println("I cannot recognise that input." + 
                            " Make sure it is caps sensitive and try again.");
                    }
                    System.out.println("How much would they like to bet?");
                    int betAmount = Integer.valueOf(scanner.nextLine());
                    if (playerList.get(i).checkBal(betAmount)) {
                        playerList.get(i).setBet(betAmount);
                        System.out.println("Your bet has been placed!");
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
            }
            
            System.out.println("Currently there is $" + passPool + 
                " on 'Pass' and $" + noPassPool + " on 'Don't Pass'!");
            boolean pass = true;
            Dice dice1 = new Dice();
            Dice dice2 = new Dice();
            ArrayList<Integer> faces = new ArrayList<Integer>();
            faces.add(dice1.getFace());
            faces.add(dice2.getFace());
            faces.add(faces.get(0) + faces.get(1));
            int rollSum = faces.get(2);
            System.out.println("Your come out roll was a " + 
                faces.get(0) + " and a " + faces.get(1) + 
                ", for a total of " + rollSum + "!");
            TimeUnit.MILLISECONDS.sleep(500);
            switch (rollSum) {
                case 7: 
                case 11: {   
                    System.out.println("Shooter Wins!"); 
                    TimeUnit.MILLISECONDS.sleep(500); break;
                }
                case 2:
                case 3:
                case 12: {
                    System.out.println("Shooter loses!"); 
                    TimeUnit.MILLISECONDS.sleep(500);
                    pass = false; break;
                }
                default: {
                    int pointNum = rollSum;
                    while (running) {
                        dice1.rollDice();
                        dice2.rollDice();
                        faces.clear();
                        faces.add(dice1.getFace());
                        faces.add(dice2.getFace());
                        faces.add(faces.get(0) + faces.get(1));
                        rollSum = faces.get(2);
                        System.out.println("You rolled a " + 
                            faces.get(0) + " and a " + faces.get(1) + 
                            ", for a total of " + rollSum + "!"); 
                        TimeUnit.MILLISECONDS.sleep(500);
                        if (rollSum == pointNum) {
                            System.out.println("Shooter wins!"); 
                            TimeUnit.MILLISECONDS.sleep(500); break;    
                        }
                        else if (rollSum == 7) {
                            System.out.println("Out 7! Shooter Loses!"); 
                            TimeUnit.MILLISECONDS.sleep(500); 
                            pass = false; break;
                        }
                        
                    }
                }
            }  
            for (int i = 0; i < playNum; i++) {
                if (playerList.get(i).checkPass() == (pass)) {
                    int addBet = playerList.get(i).checkBet() * 2;
                    playerList.get(i).updateBal(addBet);
                    System.out.println(plNameList.get(i) + " wins " +
                        addBet + " dollars! They now have a balance of $" +
                        playerList.get(i).getBal() + "!"); 
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                else {
                    int addBet = playerList.get(i).checkBet() * -1;
                    playerList.get(i).updateBal(addBet);
                    System.out.println(plNameList.get(i) + " loses " + addBet +
                        " dollars! They now have a balance of $" + 
                        playerList.get(i).getBal() + "!"); 
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
        }
    }   
}

