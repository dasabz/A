package goldmine.easy;

import java.util.HashMap;
import java.util.Map;

public class PascalTriangle {
    public static Map<Integer,Map<Integer,Integer>> pascalHash= new HashMap<>();
    public static int pascal(int col,int row){
        if(col ==0 || row==0)
                return 1;
        int pascalValue =0;
        if(pascalHash.containsKey(col)){
            if(pascalHash.get(col).containsKey(row)){
                return pascalHash.get(col).get(row);
            }else{
                pascalValue= pascal(col,row-1)+pascal(col-1,row-1);
                pascalHash.get(col).put(row,pascalValue);
            }
        }else{
            pascalValue=pascal(col,row-1)+pascal(col-1,row-1);
            Map<Integer,Integer> rowHashMap = new HashMap<>();
            rowHashMap.put(row,pascalValue);
            pascalHash.put(col,rowHashMap);
        }
        return pascalValue;
    }
}
