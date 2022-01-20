package goldmine.medium;

public class SubarrayExceedingSum {
    //length of the shortest subarray whose sum is atleast the target integer
    public static int subArrayExceedsSum(int arr[], int target){
        int i=0,j=0, length=Integer.MAX_VALUE,size=arr.length;
        if(target<=0)
            return 0;
        if(size<1)
            return -1;
        int currSum = arr[0];
        while (true){
            if(currSum<target){
                if(i==j)
                    return 1;
                else{
                    if(j-i+1<length)
                        length= j-i+1;
                    currSum -=arr[i];
                }
            }
            else {
                j++;
                if(j==size)
                    break;
                else
                    currSum+=arr[j];
            }
        }
        if(length==Integer.MAX_VALUE)
            return -1;
        return length;
    }
}
