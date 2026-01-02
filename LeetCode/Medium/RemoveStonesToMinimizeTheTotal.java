import java.io.*;
import java.util.*;

public class RemoveStonesToMinimizeTheTotal {
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < piles.length; i++) {
            pq.offer(piles[i]);
        }
        while(k-- > 0) {
            int largest = pq.poll();
            int afterOp = largest - (largest/2);
            pq.offer(afterOp);
        }
        int res = 0;
        while(!pq.isEmpty()) {
            res += (pq.poll()); 
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        int[] piles = {5, 4, 9};
        int k = 2;

        int result = minStoneSum(piles, k);
        System.out.println(result);
    }
}