package goldmine.easy;

public class AddFractions {
    public static int addFractions(int []f1, int []f2){
        return findGCD((f1[0]*f2[1])+(f2[0]*f1[0]), f1[1]*f2[1]);
    }
    public static int findGCD(int a, int b){
        int gcd = Math.min(Math.abs(a),Math.abs(b));
        if(gcd!=0){
            while (gcd > 0) {
                if(a%gcd ==0 && b%gcd ==0){
                    break;
                }
                gcd--;
            }
        }
        else
            gcd =1;
        return gcd;
    }
}
