package goldmine.easy;

import java.util.ArrayList;

public class PrimeFactors {
    public static ArrayList<Integer> primeFactorization(int x){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        if(x<2)
            return factors;
        for(int i=2;i<=x;i++){
            while(x%i==0){
                x=x/i;
                factors.add(i);
            }
        }
        return factors;
    }
}
