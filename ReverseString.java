package goldmine.easy;

public class ReverseString {
    public static String reverseStr(String str){
        if(str.length()==0)
            return str;

        int strLength = str.length();
        StringBuilder sb=new StringBuilder(strLength);
        for(int i=strLength-1;i>=0;i--)
            sb.append(str.charAt(i));
        return sb.toString();
    }
}
