package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/* Added for performance demonstration purposes compare to the majority finder in linear time.
 */
public class MajorityElementFinderInOrderNSpace  {
    //O(N) time O(N) space complexity
    public int majorityElement(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            throw new IllegalStateException("Input array is null or empty, cannot find majority element");
        if (numbers.length == 1)
            return numbers[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer number : numbers) {
            if (map.containsKey(number)) {
                int cnt = map.get(number) + 1;
                if (cnt > numbers.length / 2) {
                    return number;
                } else
                    map.put(number, cnt);
            } else {
                map.put(number, 1);
            }
        }
        throw new NoSuchElementException("Majority element not found");
    }


}
