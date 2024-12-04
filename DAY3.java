//import java.util.regex.*; is the package used  to work with expressions
import java.util.regex.Matcher; //I only need these 2
import java.util.regex.Pattern;

public class DAY3 {

    public static void main(String[] args) {

        //variables
        int num1, num2, multiply, finalResult = 0;
        
        //given sequence in question
        String sequence = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5)";
       
        //the phrase we are looking for
        //  \\( to match opening bracket since in regex, ( has a special meaning
        //  remember, in java, use double backslash to represent single backslash

        //  (\\d{1,3} >> \\d matches any digit 0-9, {1,3} between 1-3 digits
        //  () around each capturing group
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        
        // pattern object (to be used in a search)
        Pattern pattern = Pattern.compile(regex);
        
        //used to search for the pattern
        Matcher matcher = pattern.matcher(sequence);

        //goes through till all matches are found
        while (matcher.find()) {

            System.out.println("Found: " + matcher.group(0));
            System.out.println("First number: " + matcher.group(1));
            System.out.println("Second number: " + matcher.group(2));

            //turn string into integer
            num1 = Integer.parseInt(matcher.group(1));
            num2 = Integer.parseInt(matcher.group(2));
            
            //multiply the two integer values
            multiply = num1 * num2 ;
            System.out.println("Multiplying both numbers: " + multiply);
            System.out.println();
            
            //final result 
            finalResult += multiply;

        }
        //print out final result after loop ends
        System.out.println("Final result after adding all multiplications: " + finalResult);
        System.out.println();
        
    }


}