import java.util.ArrayList;
import java.util.Scanner;

public class DAY13 {
    public static void main(String[] args) {
        ArrayList<int[]> bam = new ArrayList<>();
        int total = 0;



        while (true) { 
            try (Scanner keyboard = new Scanner(System.in)) {
                System.out.println("you wanna exit yes or no");
                String stayOrGo = keyboard.nextLine();
                if("yes".equalsIgnoreCase(stayOrGo)){
                    System.out.println("Bye Bye!");
                    System.exit(0);
                }
                System.out.println("Enter the X coordinate of the prize :");
                int prizeX = keyboard.nextInt();
                System.out.println("Enter the Y coordinate of the prize ");
                int prizeY = keyboard.nextInt();

                System.out.println("Enter button A for X ");
                int AX = keyboard.nextInt();

                System.out.println("Enter button A for Y");
                int AY = keyboard.nextInt();

                System.out.println("Enter button B for X ");
                int BX = keyboard.nextInt();

                System.out.println("Enter button B for Y ");
                int BY = keyboard.nextInt();

                int[] AB = (AB(prizeX, prizeY, AX, AY, BX, BY));

                //if the boundaries are valid then add to array list
                if (AB != null) {
                    int tokens = AB[0] + AB[1]; // Tokens for A and B presses combined
                    if (tokens <= 100) {
                        bam.add(AB);
                        total += tokens;
                        System.out.println("Tokens used: " + tokens);
                }
                
            }

        }
        System.out.println("the minimum tokens you have to spend is: " + total);
    }
        
}  
    public static int[] AB (int prizeX, int prizeY, int AX, int AY, int BX, int BY){
        for (int num2 = 0; num2 <= 100; num2++) {
            if ((prizeX - BX * num2) % AX == 0 && (prizeY - BY * num2) % AY == 0) {

            int num1X = (prizeX - BX * num2) / AX;
            int num1Y = (prizeY - BY * num2)/AY;
            if (num1X == num1Y) {
                return new int[]{num1X, num2};

            }
        }
        }
        return null;
    }
}


