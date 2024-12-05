public class DAY4 {
    private static int rows;
    private static int cols;

    // Helper function to check if the target word matches from a given position in a specified direction
    private static boolean isMatch(char[][] grid, String word, int startRow, int startCol, int[] direction) {
        int targetLength = word.length();
        for (int i = 0; i < targetLength; i++) {

            //moves in whatever direction is called
            int newRow = startRow + i * direction[0]; //up,down,same
            int newCol = startCol + i * direction[1]; //left,right,same

            // Check bounds
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            // Check character mismatch
            if (grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Helper function to mark matched letters
    private static void markMatch(boolean[][] matchGrid, int startRow, int startCol, int[] direction, int wordLength) {
        for (int i = 0; i < wordLength; i++) {
            int newRow = startRow + i * direction[0];
            int newCol = startCol + i * direction[1];
            matchGrid[newRow][newCol] = true;
        }
    }

    // Main word search function
    public static char[][] wordSearch(char[][] grid, String word) {
        rows = grid.length; 
        cols = grid[0].length;
        int targetLength = word.length();
        boolean[][] matchGrid = new boolean[rows][cols];

        //arrays representing directions (coordinates kinda)

        // Single array for all 8 directions
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
         };
        // Search for the word in the grid
        //check in all 8 directions

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int dir = 0, n = directions.length; dir < n; dir++) {

                    //make array
                    //dx[0] = -1
                    if (isMatch(grid, word, i, j, directions[dir])) {
                        markMatch(matchGrid, i, j,  directions[dir], targetLength);
                    }
                }
            }
        }

        // Modify the grid: replace non-matching letters with '.'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!matchGrid[i][j]) {
                    grid[i][j] = '.';
                }
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        // Initialize 2D array of strings
        String[][] puzzle = {
            { "MMMSXXMASM" },
            { "MSAMXMSMSA" },
            { "AMXSXMAAMM" },
            { "MSAMASMSMX" },
            { "XMASAMXAMM" },
            { "XXAMMXXAMA" },
            { "SMSMSASXSS" },
            { "SAXAMASAAA" },
            { "MAMMMXMMMM" },
            { "MXMXAXMASX" }
        };

        // Convert String array to char array
        int numRows = puzzle.length;
        int numCols = puzzle[0][0].length();
        char[][] puzzle1 = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                puzzle1[i][j] = puzzle[i][0].charAt(j);
            }
        }

        // Print original grid
        printGrid(puzzle1);

        // Search for the word "XMAS"
        System.out.println("\nSearching for the word: XMAS");
        char[][] result = wordSearch(puzzle1, "XMAS");

        // Print the modified grid
        System.out.println("\nModified Grid:");
        printGrid(result);
    }

    // Helper function to print the grid
    public static void printGrid(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
