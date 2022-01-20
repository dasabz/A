package goldmine.easy;

public class IsPowerOf10 {
    public static boolean isPowerOf10(int x){
        for(int i=1;i<=x;i++){
            if(i==x)
                return true;
            if(i >Integer.MAX_VALUE/10)
                return false;
        }
        return false;
    }
}
