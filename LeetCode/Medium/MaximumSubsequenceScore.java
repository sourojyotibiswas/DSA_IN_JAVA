import java.io.*;
import java.util.*;

public class MaximumSubsequenceScore {
    /**
    TLE...
    Brute force approach:- O(2^n)
    Checking for all k length subsequences...
    Total number of possibilities => nCk...
    */
    // private int res = Integer.MIN_VALUE;
    // public static void checkSubSeq(int idx, int[] nums1, int[] nums2, int k, List<Integer> curr) {
    //     // If subsequence size == k → process it
    //     if (curr.size() == k) {
    //         int min = Integer.MAX_VALUE; 
    //         int sum = 0;
    //         for(int i = 0; i < k; i++) {
    //             int index = curr.get(i);
    //             min = Math.min(min, nums2[index]);
    //             sum += nums1[index];
    //         }
    //         res = Math.max(res, sum*min);
    //         return;
    //     }

    //     // If we reached end or cannot reach size k anymore
    //     if (idx == nums1.length) return;

    //     curr.add(idx);
    //     checkSubSeq(idx + 1, nums1, nums2, k, curr);

    //     // Backtrack
    //     curr.remove(curr.size() - 1);
    //     // Skip current element
    //     checkSubSeq(idx + 1, nums1, nums2, k, curr);
    // }

    // public static long maxScore(int[] nums1, int[] nums2, int k) {
    //     checkSubSeq(0, nums1, nums2, k, new ArrayList<>());
    //     return res;
    // }


    /**
    Approach 2: O(n log n) - Greedy with Sorting and Min Heap
    
    Key Idea: Sort by nums2 in descending order, then maintain the top k elements from nums1 
    using a min heap while iterating through possible minimum values from nums2.
    
    Steps:
    1. Create pairs {nums1[i], nums2[i]} and sort by nums2 in descending order
       - This ensures we can iterate through all possible minimum values of nums2 efficiently
       - When we're at index i, nums2[i] is the minimum among indices 0 to i
    
    2. Initialize the first k elements:
       - Calculate kSum = sum of first k elements from nums1 (after sorting)
       - Add these k elements to a min heap to track the smallest element
       - Calculate initial result = kSum * nums2[k-1] (nums2[k-1] is the minimum among first k)
    
    3. Iterate from index k to n-1:
       - For each position i, we try making nums2[i] the new minimum
       - Replace the smallest element in our current k elements (top of min heap) 
         with the new element nums1[i] if it's larger
       - Update kSum: kSum += (nums1[i] - pq.poll()) - remove smallest, add new
       - Add nums1[i] to the heap to maintain k elements
       - Calculate score = kSum * nums2[i] and update result if better
       - This works because nums2[i] is guaranteed to be ≤ all previous nums2 values (sorted order)
    
    Time Complexity: O(n log n) for sorting + O(n log k) for heap operations
    Space Complexity: O(n) for the list + O(k) for the heap
    */
    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;

        // Step 1: Create pairs and sort by nums2 in descending order
        List<int[]> ls = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ls.add(new int[]{nums1[i], nums2[i]});
        }
        ls.sort((a, b) -> Integer.compare(b[1], a[1])); // Sort by nums2 descending

        // Min heap to maintain the k largest elements from nums1
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Step 2: Initialize with first k elements
        long kSum = 0;
        for(int i = 0; i <= k - 1; i++) {
            kSum += ls.get(i)[0];      // Add nums1[i] to sum
            pq.offer(ls.get(i)[0]);    // Add nums1[i] to min heap
        }

        // Initial result: sum of first k nums1 elements * minimum nums2 (at index k-1)
        long res = kSum * ls.get(k - 1)[1];

        // Step 3: Try each subsequent element as the new minimum from nums2
        for(int i = k; i < n; i++) {
            // Replace the smallest nums1 element with the current one if beneficial
            kSum += (ls.get(i)[0] - pq.poll()); // Remove min, add current
            pq.offer(ls.get(i)[0]);              // Add current to heap
            
            // Calculate score with nums2[i] as the minimum
            res = Math.max(res, kSum * ls.get(i)[1]);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        
        // Test Case 1: Example from problem
        int[] nums1_1 = {1, 3, 3, 2};
        int[] nums2_1 = {2, 1, 3, 4};
        int k1 = 3;
        long result1 = maxScore(nums1_1, nums2_1, k1);
        System.out.println(result1);
    }
}