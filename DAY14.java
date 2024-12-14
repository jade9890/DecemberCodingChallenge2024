import java.util.ArrayList;
import java.util.List;

public class DAY14 {

    //private inner class for robots
    private static class Robot{

        //attributes
        int x, y;
        int vx, vy;

        //constructor
        Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    //method: % for edges
    void updatePosition(){
        x = (x + vx + 11) % 11;
        y = (y + vy + 7) % 7;
    }
}



    public static void main(String[] args){

        //new Array List for hold the robot info
        List<Robot> robots = new ArrayList<>();
            robots.add(new Robot(0, 4, 3, -3));
            robots.add(new Robot(6, 3, -1, -3));
            robots.add(new Robot(10,3,-1,2));
            robots.add(new Robot(2,0,2,-1));
            robots.add(new Robot(0,0,1,3));
            robots.add(new Robot(3,0,-2,-2));
            robots.add(new Robot(7,6,-1,-3));
            robots.add(new Robot(3,0,-1,-2));
            robots.add(new Robot(9,3,2,3));
            robots.add(new Robot(7,3,-1,2));
            robots.add(new Robot(2,4,2,-3));
            robots.add(new Robot(9,5,-3,-3));
    
 

    //100 seconds, update each robots position
    for ( int t = 0; t < 100; t++){
        for (Robot robot: robots){
            robot.updatePosition();
        }
    }

    //new 7 x 11 grid
    int[][] grid = new int[7][11];
        for (Robot robot : robots) {
            grid[robot.y][robot.x]++;
        }

        //4 quadrants
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        // Print the grid
        for (int y = 0; y < 7; y++) {
            
            for (int x = 0; x < 11; x++) {
                System.out.print(grid[y][x] > 0 ? grid[y][x] : ".");
                if (y < 5 && x < 3) { // Top-left quadrant (Q1)
                    q1 += grid[y][x];
                } else if (y < 5 && x > 3) { // Top-right quadrant (Q2)
                    q2 += grid[y][x];
                } else if (y > 5 && x < 3) { // Bottom-left quadrant (Q3)
                    q3 += grid[y][x];
                } else if(y > 5 && x > 3){ // Bottom-right quadrant (Q4)
                    q4 += grid[y][x];
                }
            
            }
            System.out.println();
        }
        System.out.println("Quadrant 1 (Top-left): " + q1);
        System.out.println("Quadrant 2 (Top-right): " + q2);
        System.out.println("Quadrant 3 (Bottom-left): " + q3);
        System.out.println("Quadrant 4 (Bottom-right): " + q4);

        //get total
        int safetyFactor = q1*q2*q3*q4;
        System.out.println("The safety factor after 100 seconds has passed will be: " + safetyFactor);

    }
}
