package arrays;

import java.util.Arrays;
import java.util.NoSuchElementException;
/* Added for performance demonstration purposes compare to the majority finder in linear time.*/

public class MajorityElementFinderInNLogNTime  {

    /*O(NLogN) time complexity with no extra space complexity
    Since it occurs more than 50% times, sorting the array will definitely put the majority element in the middle.
    */
    public int majorityElement(int[] numbers){
        if (numbers == null || numbers.length == 0)
            throw new IllegalStateException("Input array is null or empty, cannot find majority element");
        if (numbers.length == 1)
            return numbers[0];
        Arrays.sort(numbers);
        int mid=numbers.length/2;
        if(numbers[mid]==numbers[mid-1] && numbers[mid]==numbers[mid+1])
            return numbers[numbers.length/2];
        throw new NoSuchElementException("Majority element not found");
    }
}
