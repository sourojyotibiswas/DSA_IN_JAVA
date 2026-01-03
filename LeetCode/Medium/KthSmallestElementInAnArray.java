import java.io.*;
import java.util.*;

public class KthSmallestElementInAnArray {
    public static int numsLesserThanOrEquals(int[] arr, int guess) {
        int res = 0;
        for (int it : arr) {
            if (it <= guess) res++;
        }
        return res;
    }

    // Find kth smallest element
    public static int kthSmallest(int[] arr, int k) {
        int maxi = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE;

        // Find range of values
        for (int val : arr) {
            if (val > maxi) maxi = val;
            if (val < mini) mini = val;
        }

        int ans = -1;

        while (mini <= maxi) {
            int ourGuess = mini + (maxi - mini) / 2;
            int res = numsLesserThanOrEquals(arr, ourGuess);

            if (res >= k) {
                ans = ourGuess;       // valid candidate
                maxi = ourGuess - 1; // try smaller
            } else {
                mini = ourGuess + 1; // too small
            }
        }

        return ans;
    }

    // Main method to test
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;

        int result = kthSmallest(arr, k);
        System.out.println(result);
    }
}