/**
 * If i need to find whether [&] of all possible pairs in [i.....j] == 0
 * Initialize left = 0, mask = 0, ans = 0
 * For each right from 0 → n-1
 * While (mask & nums[right]) != 0, do mask = mask ^ nums[left], left++
 * Set mask = mask | nums[right], update ans = max(ans, right - left + 1)
 */

import java.io.*;
import java.util.*;

public class LongestNiceSubarray {
    /**
     * Brute force:- [Check for every possible subarray] - O(n^2)
     */

    /**
     * Approach 2:- [Sliding window] - O(n)
     */
    public static int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int mask = 0;
        int i = 0;

        for(int j = 0; j < n; j++) {
            // shrinking
            while((mask & nums[j]) != 0) {
                mask ^= nums[i];
                i++;
            }
            // expansion
            mask |= nums[j];
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        int[] nums = {1,3,8,48,10};
        System.out.print(longestNiceSubarray(nums));
    }
}
