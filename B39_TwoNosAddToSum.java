package arrays;

public class TwoNosAddToSum {

    int[] twoSum(int[] sortedNum, int target) {
        if (sortedNum == null || sortedNum.length == 0)
            return null;
        int i = 0;
        int j = sortedNum.length - 1;
        while (i < j) {
            int x = sortedNum[i] + sortedNum[j];
            if (x < target)
                ++i;
            else if (x > target)
                j--;
            else {
                System.out.println("Pair" + "=[" + i + "," + j);
                return new int[]{i, j};
            }
        }
        return null;
    }

}
