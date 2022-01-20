package maths;

public class ISNumberPowerOf10 {
    static boolean isPowerOf10(double input){
        int count=0;
        while (input > 1 && input % 10 == 0) {
            input /= 10;
            count++;
        }
        return count!=0;
    }

    private static boolean powerOf2(int number){
        return (number > 0) && ((number & (number - 1)) == 0);
    }
    public static void main(String args[]){
        System.out.println(isPowerOf10(10000));
    }
}
