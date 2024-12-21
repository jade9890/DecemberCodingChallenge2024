import java.util.*;

public class DAY20 {
    // Directions
    private static final char[] DIRECTIONS = {'E', 'S', 'W', 'N'};
    private static final int[][] DELTAS = {
        {0, 1},  // East
        {1, 0},  // South
        {0, -1}, // West
        {-1, 0}  // North
    };

    public static void main(String[] args) {
        String[][] mazeInput = { 
            {"###############"},
            {"#...#...#.....#"},
            {"#.#.#.#.#.###.#"},
            {"#S#...#.#.#...#"},
            {"#######.#.#.###"},
            {"#######.#.#...#"},
            {"#######.#.###.#"},
            {"###..E#...#...#"},
            {"###.#######.###"},
            {"#...###...#...#"},
            {"#.#####.#.###.#"},
            {"#.#...#.#.#...#"},
            {"#.#.#.#.#.#.###"},
            {"#...#...#...###"},
            {"###############"}
        };

        analyzeCheats(mazeInput);
    }

    public static void analyzeCheats(String[][] mazeInput) {
        int rows = mazeInput.length;
        int cols = mazeInput[0][0].length();
        char[][] maze = new char[rows][cols];

        // Parse the input into a grid and find start and end positions
        int startX = -1, startY = -1;
        for (int i = 0; i < rows; i++) {
            maze[i] = mazeInput[i][0].toCharArray();
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        // Priority queue for Dijkstra's Algorithm
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        // Set to track visited states
        Set<String> visited = new HashSet<>();
        // Add the start state to the queue
        pq.add(new State(startX, startY, 'E', 0, false));

        // List to store cheat details
        List<Cheat> cheats = new ArrayList<>();

        while (!pq.isEmpty()) {
            State current = pq.poll();
            String stateKey = current.x + "," + current.y + "," + current.facing;

            // Skip if already visited
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            // Move forward
            for (int i = 0; i < DIRECTIONS.length; i++) {
                if (DIRECTIONS[i] == current.facing) {
                    int newX = current.x + DELTAS[i][0];
                    int newY = current.y + DELTAS[i][1];
                    if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '#') {
                        pq.add(new State(newX, newY, current.facing, current.cost + 1, current.cheated));
                    } else if (!current.cheated) {
                        // Cheat by bypassing walls
                        int cheatX = newX + DELTAS[i][0];
                        int cheatY = newY + DELTAS[i][1];
                        if (isInBounds(cheatX, cheatY, rows, cols) && maze[cheatX][cheatY] != '#') {
                            int cheatCost = current.cost + 2; // Cost with cheat
                            int normalCost = current.cost + 1000; // Cost without cheat

                            int timeSaved = normalCost - cheatCost;
                            if (timeSaved >= 100) {
                                cheats.add(new Cheat(current.x, current.y, cheatX, cheatY, timeSaved));
                            }

                            pq.add(new State(cheatX, cheatY, current.facing, cheatCost, true));
                        }
                    }
                }
            }

            // Turn clockwise or counterclockwise
            for (int i = 0; i < DIRECTIONS.length; i++) {
                if (DIRECTIONS[i] == current.facing) {
                    char clockwise = DIRECTIONS[(i + 1) % DIRECTIONS.length];
                    char counterclockwise = DIRECTIONS[(i + 3) % DIRECTIONS.length];
                    pq.add(new State(current.x, current.y, clockwise, current.cost + 1000, current.cheated));
                    pq.add(new State(current.x, current.y, counterclockwise, current.cost + 1000, current.cheated));
                    break;
                }
            }
        }

        // Output the list of cheats
        System.out.println("List of cheats saving at least 100 picoseconds:");
        for (Cheat cheat : cheats) {
            System.out.printf(
                "Cheat from (%d, %d) to (%d, %d): Saved %d picoseconds\n",
                cheat.startX, cheat.startY, cheat.endX, cheat.endY, cheat.timeSaved
            );
        }
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    // Helper class to represent a state in the maze
    static class State {
        int x, y;
        char facing;
        int cost;
        boolean cheated;

        State(int x, int y, char facing, int cost, boolean cheated) {
            this.x = x;
            this.y = y;
            this.facing = facing;
            this.cost = cost;
            this.cheated = cheated;
        }
    }

    // Helper class to store cheat details
    static class Cheat {
        int startX, startY, endX, endY, timeSaved;

        Cheat(int startX, int startY, int endX, int endY, int timeSaved) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.timeSaved = timeSaved;
        }
    }
}
