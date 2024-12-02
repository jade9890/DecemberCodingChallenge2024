import java.util.Random;

public class DAY1{

    public static void quicksort(int[] list ,int A, int B){
        if (A >= B){ //nothing to sort anymore
            return;
        }
        int l = A; //start
        int r = B-1; //end-1
        int p = list[B]; //pivot point

        while(l <= r){

            while(l <= r && list[l] <= p){ //increment till l>p
                l = l + 1; //to get all #s bigger than pivot to the R
            }   
            while(l <= r && list[r] >= p){ //till r < p
                r = r - 1;
            }

            if(l < r){ //haven't reached middle yet
                swap(list, l, r);
            }
        }
        swap(list, l, B); //r < l
        quicksort(list, A, l - 1); //sort first half
        quicksort(list, l + 1, B); //sort second half
    }

    private static void swap(int[] list, int i, int j){
        int temp = list[i]; //temp value
        list[i] = list[j];
        list[j] = temp;
    }
    


    public static void main(String[] args){
        Random rand = new Random(); //instance of random class
        int n = 10;

        //generate 2 lists with 10 random numbers

        int[] list1 = new int[n];
        for(int i = 0; i < n ; i++){
            list1[i] = rand.nextInt(20);
            
        }
        int[] list2 = new int[n];
        for(int i = 0; i < n; i++){
            list2[i] = rand.nextInt(20);
        }

        //order both lists from smallest to largest (selection)

        quicksort(list1, 0, list1.length-1);
        quicksort(list2, 0, list2.length-1);

        //output the 2 lists

        int distance;
        int totalDistance = 0;
       
        System.out.println("The 2 lists are: ");

        for (int i = 0; i < n; i++){
            if(list1[i] < list2[i]){
                distance = list2[i] - list1[i];
            }
            else {
                distance = list1[i] - list2[i];
            }
            totalDistance = totalDistance + distance;

            System.out.printf("%-6d %-6d distance: %d\n", list1[i], list2[i], distance);
        }// padded 6 spaces left aligned

        System.out.println("The total distance of list 1 and list 2 is " + totalDistance);

    }
}