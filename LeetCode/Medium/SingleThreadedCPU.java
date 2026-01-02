import java.io.*;
import java.util.*;

public class SingleThreadedCPU {
    public static int[] getOrder(int[][] tasks) {
        /**
        1. Sort tasks based on enqueueTime and also store the index... {enqueueTime, processingTime, index}
        2. Put {processing time, index} in PriorityQueue (min heap)
        3. keep track of current time = 0...
        */

        int n = tasks.length;
        List<int[]> sortedTasks = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int enqueueTime = tasks[i][0];
            int processingTime = tasks[i][1];
            int index = i;

            sortedTasks.add(new int[]{enqueueTime, processingTime, index});
        }

        // Sort by enqueue time
        sortedTasks.sort(Comparator.comparingInt(a -> a[0]));

        int[] res = new int[n];

        // Min-heap: first by processing time, then by index
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );

        long currentTime = 0;
        int index = 0;
        int resIndex = 0;

        while (index < n || !pq.isEmpty()) {

            // If no available task, jump time forward
            if (pq.isEmpty() && currentTime < sortedTasks.get(index)[0]) {
                currentTime = sortedTasks.get(index)[0];
            }

            // Add all tasks that have arrived
            while (index < n && sortedTasks.get(index)[0] <= currentTime) {
                pq.offer(new int[]{sortedTasks.get(index)[1], sortedTasks.get(index)[2]});
                index++;
            }

            // Process the next task
            int[] task = pq.poll();
            currentTime += task[0];
            res[resIndex++] = task[1];
        }

        return res;
    }
    public static void main(String[] args) throws Exception {
        int[][] tasks = {
            {1, 2},
            {2, 4},
            {3, 2},
            {4, 1}
        };

        int[] result = getOrder(tasks);

        for (int idx : result) {
            System.out.print(idx + " ");
        }
    }
}