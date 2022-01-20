package goldmine.easy;

import java.util.HashMap;
import java.util.Map;

public class FindFirstNonRepeatingChar {
    public static char findFirst(String input){
        Map<Character,Integer> charFreq = new HashMap<>(input.length());
        for(char c:input.toCharArray()){
            charFreq.merge(c,1,Integer::sum);
        }
        for(char i :input.toCharArray()){
            if(charFreq.get(i)==1)
                return i;
        }
        return 0;

    }
}
