import java.util.*;


public class DAY12 {
    public static void main(String[] args) {
        String[][] grid = {
            {"RRRRIICCFF"},
            {"RRRRIICCCF"},
            {"VVRRRCCFFF"},
            {"VVRCCCJFFF"},
            {"VVVVCJJCFE"},
            {"VVIVCCJJEE"},
            {"VVIIICJJEE"},
            {"MIIIIIJJEE"},
            {"MIIISIJEEE"},
            {"MMMISSJEEE"}
        };   

        int m = grid.length;
        int n = grid[0][0].length();


        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ;j++){
                map[i][j] = grid[i][0].charAt(j);
            }  
        }

        int rows = map.length;
        int cols = map[0].length;

        //print out initial map 
        for (int i = 0; i < rows ; i++){
            for (int j = 0; j < cols ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        Map<Character, List<List<int[]>>> regions = findRegions(map);
    
        int totalCost = 0;

        //print out all coordinates 
        for (Map.Entry<Character, List<List<int[]>>> entry : regions.entrySet()) {
            char plantType = entry.getKey();
            List<List<int[]>> plantRegions = entry.getValue();
            for (List<int[]> coordinates : plantRegions) {
                int area = coordinates.size();
                System.out.println("Plant Type: " + plantType);
                System.out.println("Area: " + area);

                // Get perimeter count
                int perimeter = findPerimeter(map, plantType, coordinates);
                System.out.println("Perimeter: " + perimeter);
                int cost = perimeter * area;
                totalCost += cost;
                System.out.println("Cost: " + cost);

                System.out.println();
            }
        }

        System.out.println("Total cost is " + totalCost);
    }
    public static int findPerimeter(char[][] map, char plantType, List<int[]> coordinates){
        
        int perimeter = 0;
        int[][] directions = {
            {-1,0},  //up
            {1,0},   //down
            {0,-1},  //left
            {0,1}    //right
        };

        for(int[] coord : coordinates) {
            int i = coord[0];
            int j = coord[1];


            for (int[] direction : directions) {

                //moves in whatever direction is called
                int newRow = i + direction[0]; //up,down,same
                int newCol = j + direction[1]; //left,right,same
                
                // Check bounds
                if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map[0].length || map[newRow][newCol] != plantType) {

                        perimeter++; 
                            
            }
            }
        }
        return perimeter;
    }


    public static Map<Character, List<List<int[]>>> findRegions(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Map to hold the regions, where the key is the plant type
        // and the value is a list of regions (each region is a list of coordinates)
        Map<Character, List<List<int[]>>> plantRegions = new HashMap<>();

        // Directions for exploring neighbors (up, down, left, right)
        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        // Iterate through every cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the current plot is unvisited and not empty ('.')
                if (!visited[i][j] && map[i][j] != '.') {
                    char plantType = map[i][j];
                    List<int[]> region = new ArrayList<>();
                    // Perform DFS to find all connected plots of the same plant type
                    dfs(map, visited, i, j, plantType, region, directions);

                    // Add the region to the map for the specific plant type
                    plantRegions.putIfAbsent(plantType, new ArrayList<>());
                    plantRegions.get(plantType).add(region);
                }
            }
        }

        return plantRegions;
    }

    public static void dfs(char[][] map, boolean[][] visited, int i, int j, char plantType, List<int[]> region, int[][] directions) {
        int rows = map.length;
        int cols = map[0].length;
        
        // Mark the current plot as visited
        visited[i][j] = true;
        region.add(new int[]{i, j});

        // Explore all four directions (up, down, left, right)
        for (int[] direction : directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];

            // Check bounds and if the plant type matches
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                !visited[newRow][newCol] && map[newRow][newCol] == plantType) {
                // Continue DFS to visit the next plot
                dfs(map, visited, newRow, newCol, plantType, region, directions);
            }
        }
    }



}
