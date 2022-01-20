package goldmine.medium;
/*
Given array of non negative numbers representing elevations from vertical cross section
of a range of hills, determine how many units of snow can be captured between hilla

0,1,3,0,`,1,0,4,2,0,3,0 ---> 13 units of snow
               _
    _         | |
   | |    _   | |_   _
  _| |  _| |  | | | | |
_|_|_|_|_|_|__|_|_|_|_|
 */
public class SnowPack {
    public static Integer computeSnowPack(Integer[] arr){
        if(arr.length==0)
            return 0;
        Integer total =0;
        Integer left_highest[] = new Integer[arr.length];
        Integer left_max=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] > left_max)
                left_max=arr[i];
            left_highest[i] = left_max;
        }
        Integer right_max=0;
        for(Integer i=arr.length-1;i>=0;i--){
            if(arr[i]>right_max)
                right_max=arr[i];
            if(Math.min(right_max,left_highest[i])>arr[i])
                total+=Math.min(right_max,left_highest[i]-arr[i]);
        }
        return total;
    }
}

