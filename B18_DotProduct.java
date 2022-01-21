package goldmine.easy;

public class DotProduct {
    public static int dotProduct(int []a1,int[]a2){
        int sum=0;
        for(int i=0;i<a1.length;i++){
            sum=sum+ a1[1]*a2[i];
        }
        return sum;
    }
}
