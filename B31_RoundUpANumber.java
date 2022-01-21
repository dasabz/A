package maths;

import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUpANumber {
    public static void main(String args[]){
        double currTime = 10000;
        double prevTime = 8000;
        double deltaTime = currTime - prevTime;
        double halfLife = 60;
        System.out.println(Math.log10(100) + " " + Math.log(100));
        System.out.println(-Math.log(2)*deltaTime/halfLife);
        //System.out.println(Math.log(1/2)*deltaTime/halfLife);
    }

    private static Double roundAsPerTickSize(double num, double tickSize,boolean roundUp) {
        return roundUp?Math.round(num/tickSize)*tickSize:Math.round(num/tickSize)*tickSize-tickSize;
    }


}
