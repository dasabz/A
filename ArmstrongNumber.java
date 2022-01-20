package maths;

public class ArmstrongNumber {

    boolean isArmStrong(int number) {
        int result = 0;
        int orig = number;
        while (number != 0) {
            int remainder = number % 10;
            result = result + remainder * remainder * remainder;
            number = number / 10;
        }
        if (orig == result) {
            return true;
        }
        return false;
    }

}
