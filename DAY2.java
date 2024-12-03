import java.util.Random;
public class DAY2 {

    //do bubble sort
    //check if increasing order >> if not call decreasing
    //check if they differ from 1-3
    public static boolean increasing(int[][] array, int rowIndex, int colIndex){
            for (int j = 0; j < colIndex - 1; j++){ //go through all levels
                    if (array[rowIndex][j] < array[rowIndex][j+1]){ //if j< j+1                   
                        if ((array[rowIndex][j+1] - array[rowIndex][j] ) < 1 ||
                        (array[rowIndex][j+1] - array[rowIndex][j]) > 3 ){ //if difference is too big, unsafe
                            return false; 
                        }                    
                    }
                
                    else{
                        return decreasing(array, rowIndex, colIndex); //call the decreasing method
                        }
                    }
                    return true; //meets all criteria for increasing

                }

    public static boolean decreasing(int[][] array, int rowIndex, int colIndex){ 
        for (int j = 0; j < colIndex - 1; j++){ //go through all levels
                if (array[rowIndex][j] > array[rowIndex][j+1]){ //if j+1 < j                 
                    if ((array[rowIndex][j+1] - array[rowIndex][j] ) < 1 ||
                    (array[rowIndex][j+1] - array[rowIndex][j]) > 3 ){ //if difference is too big, unsafe
                        return false; 
                    }                    
                }
                else{
                    return false; //j+1 is not < j (its =)
                } 
            }
        return true; //meets all criteria for decreasing
    }
    

    public static void main(String[] args){
        int count = 0;
        Random rand = new Random();

        int m = rand.nextInt(50)+50; //random amount of reports from 50-100
        int n = 3; //3 columns
        int[][] array = new int[m][n]; //new 2D array

        for (int i = 0; i < m ; i++){
            for (int j = 0; j < n; j++){
                array[i][j] = rand.nextInt(5); //range = 0-4
                System.out.print(array[i][j]+ " ");
            }
            if (increasing(array, i, n)){ //array, row index, level
                System.out.print(" Safe");
                count = count + 1;
            }
            else {
                System.out.print(" Unsafe");
            }
            System.out.println();
        }
        System.out.println(count + " reports are safe!");
    }
}
