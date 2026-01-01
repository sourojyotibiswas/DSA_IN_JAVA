import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FreQuencyOfMostFrequentElement {
    /**
     * Brute force:-
     * 1. standing at j move backward and check till 0 if K < 0 break
     * O(n^2)
     */
    public static int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int j = 0;
        // 1, 3, 6, 9, 10, 11, 12, 13    k = 5
        int res = 1;
        while( j < n) {
            int i = j;
            int temp = k;
            while(i >= 0) {
                if(nums[j] - nums[i] <= temp) {
                    temp -= (nums[j] - nums[i]);
                    i--;
                }
                else break;
            }
            res = Math.max(j - i, res);
            j++;
        }
        return res;
    }

    /**
     * Approach 2: Binary Search...
     */

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int left = 1, right = n;
        int answer = 1;

        // Prefix sum to compute window sums quickly
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canMake(nums, prefix, k, mid)) {
                answer = mid;
                left = mid + 1;   // try larger frequency
            } else {
                right = mid - 1;  // try smaller frequency
            }
        }

        return answer;
    }
    private boolean canMake(int[] nums, long[] prefix, int k, int len) {
        int n = nums.length;

        for (int r = len - 1; r < n; r++) {
            int l = r - len + 1;

            long sum = prefix[r + 1] - prefix[l];
            long cost = (long) nums[r] * len - sum;

            if (cost <= k) {
                return true;
            }
        }

        return false;
    }

    /**
     * Approach 3: Sliding Window... (More Intuitive)
     * Cost calc. => cost = nums[right] * windowSize - sum
     * If cost exceeds K, move left forward and subtract from sum
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        long sum = 0;
        int res = 1;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Shrink window if cost exceeds k
            while ((long) nums[right] * (right - left + 1) - sum > k) {
                sum -= nums[left];
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {9940,9995,9944,9937,9941,9952,9907,9952,9987,9964,9940,9914,9941,9933,9912,9934,9980,9907,9980,9944,9910,9997};
        int k = 7925;

        System.out.println(maxFrequency(nums, k));

    }
}