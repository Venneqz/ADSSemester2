import java.util.Random;

public class maxTeilsumme {

    public static int maxSubarraySum(int[] arr) {
        return maxSubarraySum(arr, 0, arr.length - 1);
    }

    private static int maxSubarraySum(int[] arr, int left, int right) {
        if (left == right) {
            return Math.max(arr[left], 0);
        }

        int mid = (left + right) / 2;
        int maxLeftSum = maxSubarraySum(arr, left, mid);
        int maxRightSum = maxSubarraySum(arr, mid + 1, right);
        int maxCrossingSum = maxCrossingSum(arr, left, mid, right);

        return Math.max(Math.max(maxLeftSum, maxRightSum), maxCrossingSum);
    }

    private static int maxCrossingSum(int[] arr, int left, int mid, int right) {
        int leftSum = 0;
        int leftMaxSum = 0;
        for (int i = mid; i >= left; i--) {
            leftSum += arr[i];
            leftMaxSum = Math.max(leftMaxSum, leftSum);
        }

        int rightSum = 0;
        int rightMaxSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            rightSum += arr[i];
            rightMaxSum = Math.max(rightMaxSum, rightSum);
        }

        return leftMaxSum + rightMaxSum;
    }

    private static int[] generateArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(2001) - 1000;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = generateArray(30);
        int[] arr2 = generateArray(300);
        int[] arr3 = generateArray(3000);

        long start = System.currentTimeMillis();
        maxSubarraySum(arr1);
        long end = System.currentTimeMillis();
        System.out.println("n=30, time=" + (end - start) + "ms");

        start = System.currentTimeMillis();
        maxSubarraySum(arr2);
        end = System.currentTimeMillis();
        System.out.println("n=300, time=" + (end - start) + "ms");

        start = System.currentTimeMillis();
        maxSubarraySum(arr3);
        end = System.currentTimeMillis();
        System.out.println("n=3000, time=" + (end - start) + "ms");
    }
}
