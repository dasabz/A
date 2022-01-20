package goldmine.easy;

public class ComputeBaseToPowerExponent {

    public static double power(double base, int exp){
        if(base ==0) return 0;
        if(exp ==0) return 1;
        if(exp==1) return base;
        int positiveExp = exp<0?exp*-1:exp;
        double result =0;
        if (positiveExp%2==0)
            result =power(base*base,positiveExp/2);
        else
            result = base *power(base*base,(positiveExp-1)/2);

        return exp<0 ? 1/result:result;
    }
}
