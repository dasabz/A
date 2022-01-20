package goldmine.easy;

import java.util.HashSet;

public class UniqueTuples {
    public static HashSet<String> uniqueTuples(String input, int len){
        HashSet<String> resultSet = new HashSet<>();
        int inputLength = input.length();
        for(int i=0;i<inputLength;i++){
            resultSet.add(input.substring(i,(i+len)));
        }
        return resultSet;
    }
}
