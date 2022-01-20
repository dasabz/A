package goldmine.easy;

public class LongestUniformSubstring {
    static int[] longestUniformSub(String input){
        int longestStart = -1;
        int longestLength = 0;
        int ix=1;
        int length = input.length();
        while(ix <length){
            int start = ix-1;
            int currentLength =1;
            while(ix <length && input.charAt(ix)== input.charAt(ix-1)){
                ix++;
                currentLength++;
            }
            if(currentLength>longestLength){
                longestStart=start;
                longestLength=currentLength;
            }
            ix++;
        }
        return  new int[]{longestStart,longestLength};
    }
}
