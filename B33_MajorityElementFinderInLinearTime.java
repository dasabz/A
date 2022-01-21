package arrays;

import java.util.NoSuchElementException;
/* This should be the most efficient implementation as it does not need any extra space and runs in O(N) time */

public class MajorityElementFinderInLinearTime  {
    /*O(N) complexity with no extra space complexity
    We can utilize this information that the number occurs more than 50% of times in the array
    So each time we get a number, we increment its count, when we get another number we decrement the count.
    Since it is the majority element occuring more than 50% times, all the other elements cannot cancel it out and hence it remains at the end.
    */
    public int majorityElement(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            throw new IllegalStateException("Input array is null or empty, cannot find majority element");
        if (numbers.length == 1)
            return numbers[0];
        int result = 0, currCount = 0;
        for (Integer num :numbers) {
            if (currCount == 0) {
                result = numbers[num];
                currCount = 1;
            } else if (result == numbers[num]) {
                currCount++;
            } else {
                currCount--;
            }
        }
        if (currCount+1 > numbers.length/2)
            return result;
        throw new NoSuchElementException("Majority element not found");
    }

}
