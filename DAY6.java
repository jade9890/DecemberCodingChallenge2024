public class DAY6 {

    public static void main(String[] args){

        //the input
        String [][] grid = 
        {
            {"....#....."},
            {".........#"},
            {".........."},
            {"..#......."},
            {".......#.."},
            {".........."},
            {".#..^....."},
            {"........#."},
            {"#........."},
            {"......#..."}
        };

        //convert to character array

        int m = grid.length;
        int n = grid[0][0].length();

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ;j++){
                map[i][j] = grid[i][0].charAt(j);
            }  
        }
        int row = map.length;
        int col = map[0].length;

        for(int i = 0; i < row ; i++){
            for (int j = 0; j < col ; j++){
                if (map[i][j] == '^'){
                    guardPath(map, i, j);
                }
            }
        }   
        
        int count = 0 ;
        for(int i = 0; i < row ; i++){
            for (int j = 0; j < col ; j++){
                System.out.print(map[i][j]);
                if (map[i][j] == 'X'){
                    count = count + 1;
                }
            }
            System.out.println();
        }

       

        System.out.println("The guard visited " + count + " positions.");
        System.out.println();

    }

    public static void guardPath (char[][] map, int guardRow, int guardCol){
        
        //up, right, down, left
        int[][] directions = { {-1,0}, {0,1}, {1,0}, {0,-1} }; 
        
        //map dimensions
        int row = map.length;
        int col = map[0].length;
                
        //current direction
        int directionIndex = 0; //current direction is up
        
        while (guardRow >= 0 && guardRow < row && guardCol >= 0 && guardCol < col) {
            map[guardRow][guardCol] = 'X';

            int nextRow = guardRow + directions[directionIndex][0]; 
            int nextCol = guardCol + directions[directionIndex][1];
    
            // Exit the loop if out of bounds
            if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) {
                break; 
            }

            //if the next position is #, turn right
            if (map[nextRow][nextCol] == '#') {
                directionIndex = (directionIndex + 1) % 4;
            }

            //else, put an X and move one spot in the direction
            else{
                guardRow = nextRow;
                guardCol = nextCol;

            }

        }
    }
}
    