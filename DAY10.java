import java.util.LinkedList;
import java.util.Queue;

public class DAY10 {

    //breadth first search (BFS) - Queue FIFO
    //depth first search (DFS) - Stack LIFO
    public static void main (String[] args){

        int[][] map = {
            {8,9,0,1,0,1,2,3},
            {7,8,1,2,1,8,7,4}, 
            {8,7,4,3,0,9,6,5},
            {9,6,5,4,9,8,7,4},
            {4,5,6,7,8,9,0,3},
            {3,2,0,1,9,0,1,2},
            {0,1,3,2,9,8,0,1},
            {1,0,4,5,6,7,3,2}
        };

        /*int[][] map = {
            {0,5,2,3},
            {1,2,3,4},
            {8,6,6,5},
            {9,8,7,6}
        };
*/
        int mapRow = map.length;
        int mapCol = map[0].length;

        for (int i = 0 ; i < mapRow ; i++){
            for (int j = 0 ; j < mapCol ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }

        int totalCount = 0;
        for (int i = 0 ; i < mapRow ; i++){
            for (int j = 0 ; j < mapCol ; j++){
                if (map[i][j] == 0){
                    int count = explore(map, i, j);
                    System.out.println("trailhead at (" + i + "," + j + ") = " + count);
                    totalCount += count;
                }
            }
        }
        System.out.println("The sum of the scores of all trailheads is: " + totalCount);

    }
     public static int explore(int[][] grid, int x, int y) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 1;  // Start with the current cell

        // Queue for BFS-like exploration
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        // Mark the initial cell as visited
        grid[x][y] = -1;

        // Directions (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int curX = cell[0];
            int curY = cell[1];

            // Explore all 4 directions (up, down, left, right)
            for (int i = 0; i < 4; i++) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];

                // Check if the new cell is within bounds and has the value currentHeight + 1
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == grid[curX][curY] + 1) {
                    count++;  // Increment count for valid cell
                    grid[newX][newY] = -1;  // Mark as visited
                    queue.add(new int[]{newX, newY});  // Add to queue for further exploration
                }
            }
        }

        return count;
    }
}


    /*public static int DFS (int[][] grid, int x, int y){
        int row = grid.length;
        int col = grid[0].length;
        int currentHeight = grid[x][y];
        int count = 0;

        if (grid[x][y] == 9){
            return 1;
        }

        if (x-1 >= 0 && grid[x-1][y] == currentHeight + 1){
            count += DFS(grid, x-1, y); //move up
        }
        if (x+1 < row && grid[x+1][y] == currentHeight + 1){
            count += DFS(grid, x+1, y); //move down
        }

        if (y - 1 >= 0 && grid[x][y - 1] == currentHeight + 1) {
            count += DFS(grid, x, y - 1);  // Move left
        }
        if (y + 1 < col && grid[x][y + 1] == currentHeight + 1) {
            count += DFS(grid, x, y + 1);  // Move right
        }

        return count;
    }
} 
    */
//i know the problem is that it returns count when all directions are incorrect
//which is why the output is huge
//idk how to solve it though,