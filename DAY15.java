public class DAY15 {
    public static void main(String[] args) {
        // String[][] grid = {
        //     {"########"},
        //     {"#..O.O.#"},
        //     {"##@.O..#"},
        //     {"#...O..#"},
        //     {"#.#.O..#"},
        //     {"#...O..#"},
        //     {"#......#"},
        //     {"########"}
        // };

        //grid
        String[][] grid = {
            {"##########"},
            {"#..O..O.O#"},
            {"#......O.#"},
            {"#.OO..O.O#"},
            {"#..O@..O.#"},
            {"#O#..O...#"},
            {"#O..O..O.#"},
            {"#.OO.O.OO#"},
            {"#....O...#"},
            {"##########"}
        };

        //input string
        String moves = """
                       <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\r
                       vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\r
                       ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\r
                       <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\r
                       ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\r
                       ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\r
                       >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\r
                       <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\r
                       ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\r
                       v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^""";

        // Convert to character array
        int m = grid.length;
        int n = grid[0][0].length();

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = grid[i][0].charAt(j);
            }
        }

        int row = map.length;
        int col = map[0].length;

        //print out initial state
        System.out.println("Initial state: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        
        //flag value so guardPath doesn't get called more than once
        boolean guardFound = false;

        // Find guard and process movements
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == '@' && !guardFound) {
                    guardPath(map, i, j, moves);
                    guardFound = true;
                    break;
                }
            }
            if (guardFound) {
                break;
        }
    }
        //calculat total sum and print out grid simultaneously
        int sum;
        int totalSum = 0;
        // Final grid state
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 'O'){
                    sum = 100 * i + j;
                    totalSum += sum;
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        System.out.println("The total sum is: " + totalSum);


    }

    public static void guardPath(char[][] map, int guardRow, int guardCol, String moves) {
        int length = moves.length();
        int[][] directions = { {-1, 0}, {1, 0}, {0, 1}, {0,-1} };  // Up, Down, Right, Left

        int row = map.length;
        int col = map[0].length;

        

        // Process each move
        for (int index = 0; index < length; index++) {
            map[guardRow][guardCol] = '@';  // Guard's current position
            char direction = moves.charAt(index);

            int directionIndex;

            //
            switch (direction) {
                case '^' -> directionIndex = 0; //up
          
                case 'v' -> directionIndex = 1; //down
            
                case '>' -> directionIndex = 2; //right
            
                case '<' -> directionIndex = 3; //left
           
                default -> {
                    continue; // Skip invalid moves
                }
            }
            
            //get next row
            int nextRow = guardRow + directions[directionIndex][0];
            int nextCol = guardCol + directions[directionIndex][1];

            // Exit the loop if out of bounds (will never happen though)
            if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) {
                System.out.println("Out of bounds");
                continue;
                
            }
            //if its at the edge, do nothing
            if (map[nextRow][nextCol] == '#'){
                continue;
            }

            //if it's a dot, move the guard to next position
            if (map[nextRow][nextCol] == '.'){
                map[guardRow][guardCol] = '.';
                map[nextRow][nextCol] = '@';
                guardRow = nextRow;
                guardCol = nextCol;
            
            //if it's a box, check to see if theres a dot in that direction
            } else if (map[nextRow][nextCol] == 'O'){ 
                int tempRow = nextRow;
                int tempCol = nextCol;
                while (true) { 

                    //move in that direction
                    tempRow += directions[directionIndex][0]; 
                    tempCol += directions[directionIndex][1];

                    //if out of bounds / hitting a wall, exit
                    if (tempRow < 0 || tempRow >= row || tempCol < 0 || tempCol >= col || map[tempRow][tempCol] == '#') {
                        break; 
                    }
                    //if a dot is found, shift everything prior to that direction
                    if (map[tempRow][tempCol] == '.'){
                       
                        int shiftRow = tempRow;
                        int shiftCol = tempCol;

                        while (shiftRow != nextRow || shiftCol != nextCol) {
                            int prevRow = shiftRow - directions[directionIndex][0];
                            int prevCol = shiftCol - directions[directionIndex][1];

                            map[shiftRow][shiftCol] = map[prevRow][prevCol];

                            shiftRow = prevRow;
                            shiftCol = prevCol;
                        }
                 
                        //after shifting everything, update guard position
                        map[guardRow][guardCol] = '.';
                        map[nextRow][nextCol] = '@';
                        guardRow = nextRow;
                        guardCol = nextCol;
                        break;
                    }
                }
            }
        }
    }
}