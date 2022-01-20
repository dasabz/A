package goldmine.easy;

import java.util.HashMap;
import java.util.Map;

public class CountLengthOfCycle {
    public static int countLengthOfCycle(int []arr, int startIndex){
        Map<Integer,Integer> visited = new HashMap<>();
        int count=1;
        int index = startIndex;
        while(!visited.containsKey(index)){
            if(arr[index]> arr.length) return -1;
            visited.put(index,count);
            count++;
            index = arr[index];
        }
        return count-visited.get(index);
    }
}
