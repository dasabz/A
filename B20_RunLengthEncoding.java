package goldmine.easy;

public class RunLengthEncoding {
    public static String rle(String input){
        if(input.isEmpty())
            return "";
        StringBuilder result =new StringBuilder();
        char lastSeen=0;
        int count =1;
        for(int i=0;i<input.length();i++){
            char current=input.charAt(i);
            if(lastSeen==current)
                count++;
            else{
                if(lastSeen!=0){
                    result.append(lastSeen).append(count);
                }
                count=1;
                lastSeen=current;
            }
        }
        result.append(lastSeen).append(count);
        return result.toString();
    }
}
