package goldmine.easy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FindTopIPAddress {
    public static String findTopIPAddress(String [] lines){
       Map<String,Integer> counter = new java.util.HashMap<>();
       for(String line : lines){
           String ipAddress = line.split(" ")[0];
           if (!counter.containsKey(ipAddress)){
               counter.put(ipAddress,0);
           }else{
               counter.put(ipAddress,counter.get(ipAddress)+1);
           }
       }

       Integer max=null;
        List<String> topIpAddresses = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : counter.entrySet()){
            if(max==null || max<entry.getValue()){
                max = entry.getValue();
                topIpAddresses.clear();
                topIpAddresses.add(entry.getKey());
            }else if(max == entry.getValue()) {
                topIpAddresses.add(entry.getKey());
            }
        }
        Collections.sort(topIpAddresses);
        return String.join(",",topIpAddresses);
    }
}
