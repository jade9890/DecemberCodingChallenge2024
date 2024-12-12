import java.util.*;

public class DAY11 {
    public static void main(String[] args){
        ArrayList<Integer> pebbles = new ArrayList<>();
        pebbles.add(125);
        pebbles.add(17);

        System.out.println("Initial arrangement: ");
        System.out.println(pebbles);
        System.out.println();

        System.out.println("After 1 blink: ");
        manipulate(pebbles);
        System.out.println(pebbles);

        System.out.println();


        for ( int i = 2; i < 25; i++){
            System.out.println("After " + i + " blinks: ");
            manipulate(pebbles);
            System.out.println(pebbles);

            System.out.println();

        }

        System.out.println("The total amount of pebbles you will have after blinking 25 times is: " + pebbles.size());
    }

    public static void manipulate(ArrayList<Integer> pebbles){
        for (int i = 0 ; i < pebbles.size(); i++){
            if(pebbles.get(i) == 0){
                pebbles.set(i, 1);
                continue;
            }
            int numOfDigits = numOfDigits(pebbles.get(i));

            if(numOfDigits %2 ==1){
                int n = pebbles.get(i);
                pebbles.set(i, n*2024);
                
            }

            else {
                String num = Integer.toString(pebbles.get(i)); 
                String left = num.substring(0, numOfDigits / 2);
                String right = num.substring(numOfDigits / 2);

                int leftNum = Integer.parseInt(left);
                int rightNum = Integer.parseInt(right);

                System.out.println(leftNum); // Output: 12
                System.out.println(rightNum); // Output: 34
                if (leftNum * 2 ==0){
                    leftNum = 0;
                }

                if(rightNum * 2 ==0){
                    rightNum = 0;
                }
                pebbles.set(i, leftNum);
                pebbles.add(i+1, rightNum);
                

            }
            

            }
            
        }

    //get number of digits
    private static int numOfDigits(int n){
        return Integer.toString(n).length(); 

    }

    
}

