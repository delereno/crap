import java.util.Arrays;
/**
 * Driver class that creates the craps game, using DicePair object
 * Will roll dice, and end the turn if the sum is 7,11,2,3 or 12  
 * Program will loop until dice roll matches first roll, or gets a 7 
 * @author Del Huang
 * @version 01/03/21
 */
public class Craps
{
    public static void main (String[] args){
    DicePair Dice = new DicePair();
    Dice.rollDice();
    int[] faces = Dice.getFace();
    int rollSum = faces[2];
    System.out.println("Your come out roll was a " + faces[0] +" and a " + faces[1] + ", for a total of " + rollSum + "!");
    switch (rollSum){
        case 7: 
        case 11:{
            System.out.println("You won!"); break;
        }
        case 2:
        case 3:
        case 12:{
            System.out.println("You lost!"); break;
        }
        default:{
            int pointNum = rollSum;
            boolean running = true;
            while (running){
                Dice.rollDice();
                faces = Dice.getFace();
                rollSum = faces[2];
                System.out.println("You rolled a " + faces[0] +" and a " + faces[1] + ", for a total of " + rollSum + "!");
                if (rollSum == pointNum){
                    System.out.println("You won!"); break;    
                }
                else if (rollSum == 7){
                    System.out.println("Out 7! You Lost!"); break;
                }
                
            }
        }
    }
     
    
}
}
