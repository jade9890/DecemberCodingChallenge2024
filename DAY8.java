import java.util.*;

public class DAY8 {
    public static void main(String[] args){
        
        //string array
        String[][] grid = {
            {"............"},
            {"........0..."},
            {".....0......"},
            {".......0...."},
            {"....0......."},
            {"......A....."},
            {"............"},
            {"............"},
            {"........A..."},
            {".........A.."},
            {"............"},
            {"............"}
        };
        
        //convert to char array 

        int m = grid.length;
        int n = grid[0][0].length();

        char[][] city = new char[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ;j++){
                city[i][j] = grid[i][0].charAt(j);
            }  
        }
        

        int rows = city.length;
        int cols = city[0].length;

        //print out input
        System.out.println("city with frequencies only: ");
        
        for (int i = 0; i < rows ; i++){
            for (int j = 0; j < cols ; j++){
                System.out.print(city[i][j]);
            }
            System.out.println();
        }
        System.out.println("");

        //create hash map
        Map<Character, ArrayList<int[]>> antennaMap = new HashMap<>();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                //get frequency
                if (city[i][j] != '.'){
                    char frequency = city[i][j];

                    //if the hashmap doesn't already have the frequency, make a 
                    //new list with the frequency as the key
                    if (!antennaMap.containsKey(frequency)) {
                        antennaMap.put(frequency, new ArrayList<>());
                    }

                    //add coordinates to the key
                    antennaMap.get(frequency).add(new int[] {i, j});
                    
                }
            }
        }
        //call method to get total locations
        int totalLocations = getAntinodes(city, rows, cols, antennaMap);
        System.out.println("City with both frequency and antinodes: ");
        
        //print out city with antinodes
        for (int i = 0; i < rows ; i++){
            for (int j = 0; j < cols ; j++){
                System.out.print(city[i][j]);
            }
            System.out.println();
        }

        System.out.println("There are " + totalLocations + " unique locations in the map that contain an antinode!");
        
    }

    //returns total count
    public static int getAntinodes(char[][] city, int rows, int cols, Map<Character, ArrayList<int[]>> antennaMap){
        int count = 0;

        //go through each key
        for (Map.Entry<Character, ArrayList<int[]>> entry : antennaMap.entrySet()) {
            
            //get all values in key
            ArrayList<int[]> coordinates = entry.getValue();
            
            //if only 1 value
            if (coordinates.size() < 2) {
                continue; //go to next key
            }

            //find antinodes
            int length = coordinates.size();
            for (int i = 0; i < length; i++){
                for (int j = i + 1; j < length; j++) {
                    int[] c1 = coordinates.get(i); //get (x,y) from i
                    int[] c2 = coordinates.get(j); //get (x,y) from j

                    //get absolute value of both row and col
                    int rowDif = Math.abs(c1[0] - c2[0]);
                    int colDif = Math.abs(c1[1] - c2[1]);

                    //declare 2 antinodes
                    int[] antinode1 =  new int[2];
                    int[] antinode2 = new int[2];

                    //find row placement of both antinodes
                    if (c1[0] < c2[0]){
                        antinode1[0] = c1[0] - rowDif;
                        antinode2[0] = c2[0] + rowDif;  
                    }
                    else{
                        antinode1[0] = c1[0] + rowDif;
                        antinode2[0] = c2[0] - rowDif;

                    }
                    //find col placement of both antinodes
                    if (c1[1] < c2[1]){
                        antinode1[1] = c1[1] - colDif;
                        antinode2[1] = c2[1] + colDif;
                    }
                    else{
                        antinode1[1] = c1[1] + colDif;
                        antinode2[1] = c2[1] - colDif;
                    }

                    //antinode 1
                    //if unique and within city bounds, inc count
                    if (isValidAntinode(city, antinode1)) {
                        if (city[antinode1[0]][antinode1[1]] != '#'){
                            count += 1;
                        }
                        //change to # if its a . (don't change if it's a frequency)
                        if (city[antinode1[0]][antinode1[1]] == '.'){
                            city[antinode1[0]][antinode1[1]] = '#';  // Mark the antinode
                        }
                    }

                    //antinode 2
                    if (isValidAntinode(city, antinode2)) {
                        if(city[antinode2[0]][antinode2[1]] != '#'){
                            count += 1;
                        }
                        if(city[antinode2[0]][antinode2[1]] == '.'){
                            city[antinode2[0]][antinode2[1]] = '#';  // Mark the antinode
                        }
                    }
                }
            }
        }
        return count; //total unique number of locations
    }
                
    //private method to check if within bounds
    private static boolean isValidAntinode(char[][] city, int[] antinode) {
        int row = antinode[0];
        int col = antinode[1];
        return row >= 0 && row < city.length && col >= 0 && col < city[0].length;
    }
}
