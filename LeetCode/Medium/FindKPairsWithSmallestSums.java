import java.io.*;
import java.util.*;

public class FindKPairsWithSmallestSums {
    // Java 8+
    // public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    //     List<List<Integer>> res = new ArrayList<>();

    //     Queue<Map.Entry<Integer, int[]>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getKey(), a.getKey()));

    //     for(int i = 0; i < nums1.length; i++) {
    //         for(int j = 0; j < nums2.length; j++) {
    //             int sum = nums1[i] + nums2[j];
    //             if(pq.size() < k) pq.offer(Map.entry(sum, new int[]{nums1[i], nums2[j]}));
    //             else if(pq.peek().getKey() > sum) {
    //                 pq.poll();
    //                 pq.offer(Map.entry(sum, new int[]{nums1[i], nums2[j]}));
    //             }
    //             else break;
    //         }
    //     }

    //     while (!pq.isEmpty()) {
    //         int[] pair = pq.poll().getValue();
    //         res.add(Arrays.asList(pair[0], pair[1]));
    //     }
        
    //     return res;
    // }

    // Java 8
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<Map.Entry<Integer, int[]>> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(b.getKey(), a.getKey()));

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];

                if (pq.size() < k) {
                    pq.offer(new AbstractMap.SimpleEntry<>(
                            sum, new int[]{nums1[i], nums2[j]}
                    ));
                } 
                else if (pq.peek().getKey() > sum) {
                    pq.poll();
                    pq.offer(new AbstractMap.SimpleEntry<>(
                            sum, new int[]{nums1[i], nums2[j]}
                    ));
                } 
                else {
                    break;
                }
            }
        }

        while (!pq.isEmpty()) {
            int[] pair = pq.poll().getValue();
            res.add(Arrays.asList(pair[0], pair[1]));
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        List<List<Integer>> result = kSmallestPairs(nums1, nums2, k);

        for (List<Integer> pair : result) {
            System.out.print(pair + ",");
        }
    }
}