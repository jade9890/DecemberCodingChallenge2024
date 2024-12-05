import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DAY5 {

    public static void addRule(Map<Integer, List<Integer>> adj, int X, int Y){
        adj.putIfAbsent(X, new ArrayList<>()); //make sure list exists
        adj.get(X).add(Y); //add Y to list for X
    }

    public static boolean checkValid(int[] update, Map<Integer, List<Integer>> adj){
        for (int i = 0, n = update.length; i < n ; i++){
            for (int j = i + 1; j < n ; j++){
                int X = update[i]; //current page
                int Y = update[j]; //pages after
                if (!adj.containsKey(X) || !adj.get(X).contains(Y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void print(int[] update){
        int flag = 0;
        for (int i = 0, n = update.length; i < n; i++) {
            if (flag == 0){
                System.out.print(update[i]);
                flag = 1;
            }
            else{
                System.out.print(", " + update[i]);
            }
        }
    }

    public static int getMiddle(int[] update){
        return (update[update.length/2]);
    }
    public static void main(String[] args) {

        //create new adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        
        //add all rules
        //use private method to encapsulate adding logic
        //add new rule >> add a directed edge from X to Y
        //where X is vertex, Y is detination node
        addRule(adj, 47, 53);
        addRule(adj, 97, 13);
        addRule(adj, 97, 61);
        addRule(adj, 97, 47);
        addRule(adj, 75, 29);
        addRule(adj, 61, 13);
        addRule(adj, 75, 53);
        addRule(adj, 29, 13);
        addRule(adj, 97, 29);
        addRule(adj, 53, 29);
        addRule(adj, 61, 53);
        addRule(adj, 97, 53);
        addRule(adj, 61, 29);
        addRule(adj, 47, 13);
        addRule(adj, 75, 47);
        addRule(adj, 97, 75);
        addRule(adj, 47, 61);
        addRule(adj, 75, 61);
        addRule(adj, 47, 29);
        addRule(adj, 75, 13);
        addRule(adj, 53, 13);

        int[][] updates =
        {
            {75,47,61,53,29},
            {97,61,53,29,13},
            {75,29,13},
            {75,97,47,61,53},
            {61,13,29},
            {97,13,75,29,47}
        };
        int result = 0;
        for (int i = 0, n = updates.length; i < n ; i++){
            int[] update = updates[i];
            if(checkValid(update, adj)){
                print(update);
                int middle = getMiddle(update);
                System.out.println("  Middle is: " + middle + ".");
                result = result + middle;
            }
            else{
                updates[i] = null;
            }
        }

            System.out.println("");
        System.out.println("Adding all middles: " + result);
        System.out.println();

    }

}