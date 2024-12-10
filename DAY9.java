import java.util.LinkedList;

public class DAY9 {
    public static void main (String[] args){
        int[] input = {2,3,3,3,1,3,3,1,2,1,4,1,4,1,3,1,4,0,2};
        int length = input.length;

        //character linked list
        LinkedList<Character> diskMap = new LinkedList<>();
        int fileId = 0; // File ID starts at 0

        for (int i = 0; i < length ; i++){
            if(i % 2 == 0){ //even
                for ( int j = 0; j < input[i]; j++){
                    diskMap.add((char) ('0' + fileId)); // Add the file ID as a character
                }
                fileId++; // Increment the file ID for the next file block

            }
            else{
                for(int j = 0; j < input[i] ; j++){
                    diskMap.add('.');
                }
            }
        }
        printLinkedList(diskMap);

        int len = diskMap.size();

        int i = 0;
        int j = len - 1;

        //time complexity is o(n)

        while (i < j){

            //increment i till it's a .
            if (diskMap.get(i) == '.'){

                //decrement j till its a number
                while (j > i && diskMap.get(j) == '.'){
                    j--;
                }

                //swap values
                if ( j > i && diskMap.get(j) != '.'){
                    diskMap.set(i, diskMap.get(j));
                    diskMap.set(j, '.');
                    printLinkedList(diskMap);
                
                // Move i and j for next iteration
                    i++;
                    j--;
                }
            } else {
                i++;
            }
        }
        int total = getTotal(diskMap, len);
        System.out.println("The checksum is: " + total);
    }

    public static void printLinkedList(LinkedList<Character> diskMap){
        int length = diskMap.size();
        for (int i = 0; i < length; i++){
            System.out.print(diskMap.get(i));
        }
        System.out.println();
    }

    public static int getTotal(LinkedList<Character> diskMap, int length){
        int total = 0;
        //make sure you dont access the ASCII value
        for (int i = 0; i < length; i++){
            char currentChar = diskMap.get(i);
            if (diskMap.get(i) == '.'){
                break;
            }
            int fileId = currentChar - '0'; //to get the num instead of ASCII
            total+= (i * fileId);

        }
        return total;
    }
    
}
