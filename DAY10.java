
public class DAY10 {

    // Directions for up, down, left, right movement
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static int DFS(int[][] map, int x, int y, boolean[][] visited) {
        int rows = map.length;
        int cols = map[0].length;

        // If out of bounds, already visited, or invalid start, return 0
        if (x < 0 || y < 0 || x >= rows || y >= cols || visited[x][y]) {
            return 0;
        }

        // Mark this cell as visited
        visited[x][y] = true;

        // Current height of the cell
        int currentHeight = map[x][y];

        // Base case: if we reach 9, count it as part of the trail
        if (currentHeight == 9) {
            return 1;
        }

        int count = 1; // Count this cell

        // Explore all 4 possible directions
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            // Continue DFS only if the next cell's height is currentHeight + 1
            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && map[nx][ny] == currentHeight + 1) {
                count += DFS(map, nx, ny, visited);
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[][] map = {
            {8, 9, 0, 1, 0, 1, 2, 3},
            {7, 8, 1, 2, 1, 8, 7, 4},
            {8, 7, 4, 3, 0, 9, 6, 5},
            {9, 6, 5, 4, 9, 8, 7, 4},
            {4, 5, 6, 7, 8, 9, 0, 3},
            {3, 2, 0, 1, 9, 0, 1, 2},
            {0, 1, 3, 2, 9, 8, 0, 1},
            {1, 0, 4, 5, 6, 7, 3, 2}
        };

        int rows = map.length;
        int cols = map[0].length;

        // Print the map
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        int totalCount = 0;

        // Iterate through the map to find trailheads
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) {
                    // Create a fresh visited array for each trailhead
                    boolean[][] visited = new boolean[rows][cols];
                    int count = DFS(map, i, j, visited);
                    System.out.println("Trailhead at (" + i + "," + j + ") = " + count);
                    totalCount += count;
                }
            }
        }

        System.out.println("The sum of the scores of all trailheads is: " + totalCount);
    }
}
