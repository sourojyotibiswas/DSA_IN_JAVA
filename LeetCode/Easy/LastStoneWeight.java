import java.io.*;
import java.util.*;

public class LastStoneWeight {
    public static int lastStoneWeight(int[] stones) {
        int n = stones.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0; i < n; i++) {
            pq.offer(stones[i]);
        }
        while(pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            if(x - y > 0) pq.offer(x-y);
        }
        return (pq.size() == 1) ? pq.peek() : 0;
    }
    public static void main(String[] args) throws Exception {
        int[] stones = {2, 7, 4, 1, 8, 1};

        int result = lastStoneWeight(stones);

        System.out.println(result);
    }
}