import java.util.Arrays; import java.util.concurrent.TimeUnit; import java.util.Scanner;
/**
 * Driver class that creates the craps game, using DicePair object
 * Will roll dice, and end the turn if the sum is 7,11,2,3 or 12  
 * Program will loop until dice roll matches first roll, or gets a 7 
 * @author Del Huang
 * @version 01/03/21
 */
public class Craps
{  
    public static void main (String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    System.out.println("How many players will be playing today?");
    int playNum = Integer.valueOf(scanner.nextLine());
    Player[] playerList = new Player [playNum]; String[] plNameList = new String [playNum];
    System.out.println(playNum + " players? Alright, please enter their names.");
    for (int i=0; i < playNum; i++){
        String name = scanner.nextLine();
        playerList[i] = (new Player(name)); 
        plNameList[i] = name; 
    } 
    while (running = true){
        System.out.println("Which player will be the shooter?");
        String shooter = scanner.nextLine();
        if (Arrays.asList(plNameList).contains(shooter)){
               System.out.println(plNameList);
               int i = Arrays.binarySearch(plNameList, shooter);
               System.out.println(i);
               playerList[i].getName();
               //Test starts here
               System.out.println("enter index?");
               i = Integer.valueOf(scanner.nextLine());
               playerList[i].getName();
        }
    }
    DicePair Dice = new DicePair();
    Dice.rollDice();
    int[] faces = Dice.getFace();
    int rollSum = faces[2];
    System.out.println("Your come out roll was a " + faces[0] +" and a " + faces[1] + ", for a total of " + rollSum + "!"); TimeUnit.MILLISECONDS.sleep(500);
    switch (rollSum){
        case 7: 
        case 11:{   
            System.out.println("You won!"); TimeUnit.MILLISECONDS.sleep(500); break;
        }
        case 2:
        case 3:
        case 12:{
            System.out.println("You lost!"); TimeUnit.MILLISECONDS.sleep(500);break;
        }
        default:{
            int pointNum = rollSum;
            while (running){
                Dice.rollDice();
                faces = Dice.getFace();
                rollSum = faces[2];
                System.out.println("You rolled a " + faces[0] +" and a " + faces[1] + ", for a total of " + rollSum + "!"); TimeUnit.MILLISECONDS.sleep(500);
                if (rollSum == pointNum){
                    System.out.println("You won!"); TimeUnit.MILLISECONDS.sleep(500); break;    
                }
                else if (rollSum == 7){
                    System.out.println("Out 7! You Lost!"); TimeUnit.MILLISECONDS.sleep(500); break;
                }
                
            }
        }
    }  
}   
}
