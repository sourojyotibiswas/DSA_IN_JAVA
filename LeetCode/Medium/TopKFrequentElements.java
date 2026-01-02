import java.io.*;
import java.util.*;

public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();

        for(int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        // 1 -> 3, 2 -> 2, 3 -> 1

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pq.offer(entry);
        }

        // {1, 3}, {2, 2}, {3, 1}

        int[] res = new int[k];

        for(int i = 0; i < k; i++) {
            res[i] = pq.poll().getKey();
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = topKFrequent(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}