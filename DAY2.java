import java.util.Random;
public class DAY2 {

    //do bubble sort
    //check if increasing order >> if not call decreasing
    //check if they differ from 1-3
    public static boolean increasing(int[][] array, int rowIndex, int colIndex){
            for (int j = 0; j < colIndex - 1; j++){
                    if (array[rowIndex][j] < array[rowIndex][j+1]){ //if j<k                    
                        if ((array[rowIndex][j+1] - array[rowIndex][j] ) < 1 ||
                        (array[rowIndex][j+1] - array[rowIndex][j]) > 3 ){ //if difference is too big
                            return false; //unsafe
                        }                    
                    }
                
                    else{
                        return decreasing(array, rowIndex, colIndex);
                        }
                    }
                    return true;
                }

    public static boolean decreasing(int[][] array, int rowIndex, int colIndex){
        for (int j = 0; j < colIndex - 1; j++){
                if (array[rowIndex][j] > array[rowIndex][j+1]){ //if j<k                
                    if ((array[rowIndex][j+1] - array[rowIndex][j] ) < 1 ||
                    (array[rowIndex][j+1] - array[rowIndex][j]) > 3 ){ //if difference is too big
                        return false; //unsafe
                    }                    
                }
                else{
                    return false;
                } 
            }
        return true;
    }

        
        
             
    

    public static void main(String[] args){
        Random rand = new Random();

        int m = rand.nextInt(50)+50; //random amount of reports
        int n = 3;
        int[][] array = new int[m][n]; //new 2D array

        for (int i = 0; i < m ; i++){
            for (int j = 0; j < n; j++){
                array[i][j] = rand.nextInt(5); 
                System.out.print(array[i][j]+ " ");
            }
            if (increasing(array, i, n)){ //array, row index, level
                System.out.print(" Safe");
            }
            else {
                System.out.print(" Unsafe");
            }
            System.out.println();
        }
    }
}
