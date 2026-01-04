import java.io.*;
import java.util.*;

public class KthSmallestPrimeFraction {
    /**
    Approach 1:-
    Brute force:- TLE (Min heap)
    public int[] kthSmallestPrimeFraction(int[] nums, int k) {
        int n = nums.length;
        
        // Min heap
        Queue<Map.Entry<Double, int[]>> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(a.getKey(), b.getKey())
        );

        Map<Double, int[]> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int numerator = nums[i];
            for(int j = i+1; j < n; j++) {
                double value = (double)nums[i]/(double)nums[j];
                map.put(value, new int[]{nums[i], nums[j]});
            }
        }

        for(Map.Entry<Double, int[]> entry : map.entrySet()) {
            pq.offer(entry);
        }

        while(k-- > 1) pq.poll();
        
        return new int[]{pq.peek().getValue()[0], pq.peek().getValue()[1]};
    }
     */
    
    /**
    Approach 2:-
    Optimization:- (Max heap)
    */
    public static int[] kthSmallestPrimeFraction(int[] nums, int k) {
        int n = nums.length;
        // Max heap
        Queue<Map.Entry<Double, int[]>> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getKey(), a.getKey())
        );

        for(int i = 0; i < n; i++) {
            int numerator = nums[i];
            for(int j = i+1; j < n; j++) {
                double value = (double)nums[i]/(double)nums[j];
                pq.offer(new AbstractMap.SimpleEntry<>(value, new int[]{nums[i], nums[j]})); // Java 9+ : Map.entry(value, new int[]{nums[i], nums[j]})
            }
            while(pq.size() > k) pq.poll();
        }
        
        return new int[]{pq.peek().getValue()[0], pq.peek().getValue()[1]};
    }
    public static void main(String[] args) throws Exception {
        System.out.print(Arrays.toString(kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3)));
    }
}