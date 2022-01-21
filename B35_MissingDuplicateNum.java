package arrays;

public class MissingDuplicateNum {

    void getMissingNumber(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            sum += numbers[i];
        }
        int total = (numbers.length + 1) * numbers.length / 2;
        int missing = total - sum;
        System.out.println("missing number is: " + missing);
    }

    void findDuplicateNumberInRange(int numbers[]) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        int total = (numbers.length - 1) * (numbers.length) / 2;
        int duplicate = sum - total;
        System.out.println("Duplicate Number: " + duplicate);
    }


}
