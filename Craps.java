import java.util.ArrayList; import java.util.concurrent.TimeUnit; import java.util.Scanner;
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
    ArrayList<Player> playerList = new ArrayList<Player>();
    ArrayList<String> plNameList = new ArrayList<String>();
    System.out.println(playNum + " players? Alright, please enter their names.");
    for (int i=0; i < playNum; i++){
        String name = scanner.nextLine();
        playerList.add(new Player(name)); 
        plNameList.add(name); 
    } 
    while (running = true){
        System.out.println("Which player will be the shooter?");
        String shooter = scanner.nextLine();
        if (plNameList.contains(shooter)){
               System.out.println(plNameList);
               int i = plNameList.indexOf(shooter);
               System.out.println("Alright, " + plNameList.get(i) + " will be the shooter."); 
               playerList.get(i).setShooter(); break;
        }
        else{
            System.out.println("I cannot recognise that name. Make sure that your input is caps sensitive.");
        }
    }
    DicePair Dice = new DicePair();
    Dice.rollDice();
    ArrayList<Integer> faces = Dice.getFace();
    int rollSum = faces.get(2);
    System.out.println("Your come out roll was a " + faces.get(0) +" and a " + faces.get(1) + ", for a total of " + rollSum + "!"); TimeUnit.MILLISECONDS.sleep(500);
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
                rollSum = faces.get(2);
                System.out.println("You rolled a " + faces.get(0) +" and a " + faces.get(1) + ", for a total of " + rollSum + "!"); TimeUnit.MILLISECONDS.sleep(500);
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
