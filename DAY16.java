import java.util.*;

//COME BACK TO THIS ONE 


public class DAY16 {
    // Directions 
    private static final char[] DIRECTIONS = {'E', 'S', 'W', 'N'};
    private static final int[][] DELTAS = {
        {0, 1},  // East
        {1, 0},  // South
        {0, -1}, // West
        {-1, 0}  // North
    };

    public static void main(String[] args) {
        String[] mazeInput = { 
            "###############",
            "#.......#....E#",
            "#.#.###.#.###.#",
            "#.....#.#...#.#",
            "#.###.#####.#.#",
            "#.#.#.......#.#",
            "#.#.#####.###.#",
            "#...........#.#",
            "###.#.#####.#.#",
            "#...#.....#.#.#",
            "#.#.#.###.#.#.#",
            "#.....#...#.#.#",
            "#.###.#.#.#.#.#",
            "#S..#.....#...#",
            "###############"
        };

        System.out.println("Lowest Score: " + findLowestScore(mazeInput));
    }

    public static int findLowestScore(String[] mazeInput) {

        int rows = mazeInput.length;
        int cols = mazeInput[0].length();
        char[][] maze = new char[rows][cols];

        // Parse the input into a grid and find start and end positions
        int startX = -1, startY = -1;
        int endX = -1, endY = -1;
        for (int i = 0; i < rows; i++) {
            maze[i] = mazeInput[i].toCharArray();
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        // Priority queue for Dijkstra's Algorithm
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        // Set to track visited states
        Set<String> visited = new HashSet<>();
        // Add the start state to the queue
        pq.add(new State(startX, startY, 'E', 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            String stateKey = current.x + "," + current.y + "," + current.facing;

            // Skip if already visited
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            // Check if we've reached the end
            if (current.x == endX && current.y == endY) {
                return current.cost;
            }

            // Move forward
            for (int i = 0; i < DIRECTIONS.length; i++) {
                if (DIRECTIONS[i] == current.facing) {
                    int newX = current.x + DELTAS[i][0];
                    int newY = current.y + DELTAS[i][1];
                    if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '#') {
                        pq.add(new State(newX, newY, current.facing, current.cost + 1));
                    }
                }
            }

            // Turn clockwise or counterclockwise
            for (int i = 0; i < DIRECTIONS.length; i++) {
                if (DIRECTIONS[i] == current.facing) {
                    char clockwise = DIRECTIONS[(i + 1) % DIRECTIONS.length];
                    char counterclockwise = DIRECTIONS[(i + 3) % DIRECTIONS.length];
                    pq.add(new State(current.x, current.y, clockwise, current.cost + 1000));
                    pq.add(new State(current.x, current.y, counterclockwise, current.cost + 1000));
                    break;
                }
            }
        }

        return -1; // No path found
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    // Helper class to represent a state in the maze
    static class State {
        int x, y;
        char facing;
        int cost;

        State(int x, int y, char facing, int cost) {
            this.x = x;
            this.y = y;
            this.facing = facing;
            this.cost = cost;
        }
    }
}
