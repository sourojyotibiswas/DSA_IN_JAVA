import java.io.*;
import java.util.*;

public class KthLargestElementInAnArray {
    /**
    Approach 1:- Sorting (Normal, Bucket)
    Approach 2:- Priority Queue (Max Heap)
    Approach 3:- Binary Search
    */

   // Approach 2
    // public static int findKthLargest(int[] nums, int k) {
    //     Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    //     for(int it : nums) {
    //         pq.offer(it);
    //     }

    //     while(k-- > 1) pq.poll();
    //     return pq.peek();
    // }

    // Approach 3
    public static int numsGreaterThanOrEquals(int[] arr, int k) {
        int res = 0;
        for(int it : arr) {
            if(it >= k) res++;
        }
        return res;
    }
    public static int findKthLargest(int[] arr, int k) {
        /* 
            everytime we guess a number check in the array how many 
            numbers are >= our guess
        */
        int maxi = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE;
        
        for (int val : arr) { 
            if (val > maxi) maxi = val; 
            if (val < mini) mini = val;
        }
        int ans = -1;
        while(mini <= maxi) {
            int ourGuess = mini + (maxi - mini)/2;
            int res = numsGreaterThanOrEquals(arr, ourGuess);
            
            if(res >= k) {
                ans = ourGuess;
                mini = ourGuess + 1;
            }
            else maxi = ourGuess - 1;
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        int result = findKthLargest(nums, k);
        System.out.println(result);
    }
}