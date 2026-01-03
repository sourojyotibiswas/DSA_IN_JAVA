import java.io.*;
import java.util.*;

public class TotalCostToHireKWorkers {
    /*
    Brute force:- TLE
    1. scans first candidates
    2. scans last candidates
    3. choses min
    4. adds it to res
    5. deletes it and move on... till k > 0...
     */
    
    // public static long totalCost(int[] costs, int k, int candidates) {

    //     List<Integer> ls = new ArrayList<>();
    //     for (int i = 0; i < costs.length; i++) {
    //         ls.add(costs[i]);
    //     }

    //     long sum = 0;

    //     while (k-- > 0) {

    //         int min = Integer.MAX_VALUE;
    //         int min_idx = -1;

    //         int range = Math.min(candidates, ls.size());

    //         // first candidates
    //         for (int i = 0; i < range; i++) {
    //             if (ls.get(i) < min) {
    //                 min = ls.get(i);
    //                 min_idx = i;
    //             }
    //         }

    //         // last candidates
    //         int rightStart = Math.max(range, ls.size() - candidates);
    //         for (int i = ls.size() - 1; i >= rightStart; i--) {
    //             if (ls.get(i) < min || (ls.get(i) == min && i < min_idx)) {
    //                 min = ls.get(i);
    //                 min_idx = i;
    //             }
    //         }

    //         sum += ls.get(min_idx);
    //         ls.remove(min_idx);
    //     }

    //     return sum;
    // }

    /**
    Appraoch 2 - with 2 Heap:- O(k * log candidates)
        1. res = 0
        2. i = 0, j = n-1
        3. pQ1, pQ2 [min heap]
        4. till k-- > 0
        5. till pQ1.size() < candidates && i <= j
        6. pQ1.offer(costs[i]), i++
        7. till pQ2.size() < candidates && j >= i
        8. pQ2.offer(costs[j]), j--

        9. int minPq1 = pQ1.size() > 0 ? pQ1.peek() : Integer.MAX_VALUE;
        10. int minPq2 = pQ2.size() > 0 ? pQ2.peek() : Integer.MAX_VALUE;

        11. if(minPq1 <= minPq2) res += minPq1, pQ1.poll()
        12. else res += minPq2, pQ2.poll()
    */
    public static long totalCost(int[] costs, int k, int candidates) {
        long res = 0L;
        int i = 0, j = costs.length - 1;
        Queue<Integer> pQ1 = new PriorityQueue<>();
        Queue<Integer> pQ2 = new PriorityQueue<>();

        while(k-- > 0) {
            while(pQ1.size() < candidates && i <= j) pQ1.offer(costs[i++]);
            while(pQ2.size() < candidates && j >= i) pQ2.offer(costs[j--]);

            int minPq1 = pQ1.size() > 0 ? pQ1.peek() : Integer.MAX_VALUE;
            int minPq2 = pQ2.size() > 0 ? pQ2.peek() : Integer.MAX_VALUE;

            res += (minPq1 <= minPq2) ? pQ1.poll() : pQ2.poll();
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int k = 3;
        int candidates = 4;

        long result = totalCost(costs, k, candidates);
        System.out.println(result);
    }
}