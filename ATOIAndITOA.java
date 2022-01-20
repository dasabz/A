package maths;

public class ATOIAndITOA {
    int atoi(String s) {
        int flag = 1, k = 0;
        if (s.charAt(0) == '-') {
            flag = flag * -1;
            k++;
        } else if (s.charAt(0) == '+') {
            flag = flag * 1;
            k++;
        } else
            flag = 1;
        int result = 0;
        for (int i = k; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                result = (result * 10 + s.charAt(i) - '0');
            }
        }
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return result * flag;
    }

    String itoa(int x) {
        String s = "";
        if (x == 0)
            return "0";
        if (x < 0)
            x = -x;
        while (x != 0) {
            s = (x % 10) + s;
            x = x / 10;
        }
        if (x < 0)
            s = "-" + s;
        return s;
    }
}
