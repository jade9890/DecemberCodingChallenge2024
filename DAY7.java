public class DAY7 {
    public static void main(String[] args) {

        //string array to hold input
        String[][] puzzle = {   
            {"190: 10 19"},
            {"3267: 81 40 27"},
            {"83: 17 5"},
            {"156: 15 6"},
            {"7290: 6 8 6 15"},
            {"161011: 16 10 13"},
            {"192: 17 8 14"},
            {"21037: 9 7 18 13"},
            {"292: 11 6 16 20"}
        };

        //variables
        int rows = puzzle.length;
        int totalCalibration = 0;

        //print out input
        System.out.println("Input for calibration equations: ");
        for (int i = 0; i < rows; i++) {
            System.out.println(puzzle[i][0]);
        }
        System.out.println();
        System.out.println("All possible results: ");

        //go through each line
        for (int i = 0; i < rows; i++) {

            //separate line based on colon (trim to get rid of excess space)
            String[] parts = puzzle[i][0].split(":");
            int testValue = Integer.parseInt(parts[0].trim());
            String operation = parts[1].trim();

            // convert from string to int[]
            int[] numbers = stringToInt(operation);

            // Call backtrack to check all possible combos
            boolean result = backtrack(numbers, 0, numbers[0], testValue, "+") || 
                             backtrack(numbers, 0, numbers[0], testValue, "*");
            
            //print out correct results only, add up testValues
            if (result) {
                System.out.println(puzzle[i][0]);
                totalCalibration += testValue;
            } 
        }
        System.out.println("Up to you to figure out the combination of operators LOL");
        System.out.println("Total Calibration: " + totalCalibration);
        System.out.println();
    }

    //split, put all parts into new integer array, return array
    public static int[] stringToInt(String line) {
        String[] parts = line.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

    // Backtrack: Recursively tries combinations of '+' and '*' to find the test value
    public static boolean backtrack(int[] numbers, int index, int currentValue, int testValue, String operator) {
        
        //if 1) all numbers were used and 2) it matches test value, return true
        if (index == numbers.length - 1) { //base case
            return currentValue == testValue;
        }
    
        // Try "+" operator on next index
        if (backtrack(numbers, index + 1, currentValue + numbers[index + 1], testValue, "+")) {
            return true;
        }
        // Try "*" operator
        return backtrack(numbers, index + 1, currentValue * numbers[index + 1], testValue, "*");
    }
    
}
