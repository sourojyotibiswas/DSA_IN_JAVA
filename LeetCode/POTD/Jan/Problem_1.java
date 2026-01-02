// LC-961

import java.io.*;
import java.util.*;

public class Problem_1 {
    public static int repeatedNTimes(int[] nums) {
        // nums = 2n = 8
        // n = 4 i.e. 5 unique and 4 duplicate
        // 1 2 3 4 5 5 5 5
        Set<Integer> set = new HashSet<>();
        int res = -1;
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                res = nums[i];
                break;
            }
            set.add(nums[i]);
        }

        return res;
    }
    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 3, 4, 5, 5, 5, 5};
        System.out.println(repeatedNTimes(nums));
    }
}