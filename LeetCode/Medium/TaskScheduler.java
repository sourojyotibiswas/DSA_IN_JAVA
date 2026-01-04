import java.io.*;
import java.util.*;

public class TaskScheduler {
    //     /**
//     Direct Formula:-
//     Total Time = max(task.length, (f_max - 1) * (n - 1) + count_max);
//     f_max:     The frequency of the most common task.
//     n:         The cooling period.
//     count_max: The number of different tasks that have that maximum frequency.

//     A-4, B-4, C-3, D-2; n = 3; size = 13

//     A_ _ _ A_ _ _ A_ _ _ A => A B C D | A B C D | A B C _ | A B

//     no. of task in each gaps => 12 i.e. (4 - 1) * (3 + 1)
//     count of max. => 2
//     freq. of max. => 4

//     Total time = max((no. of task in each gaps + no. of max.), size)
//                = max(14, 13)
//      */
//     public int leastInterval(char[] tasks, int n) {
//         int[] freq = new int[26];
//         for (char c : tasks) {
//             freq[c - 'A']++;
//         }
        
//         // Sort to find the max frequency easily
//         Arrays.sort(freq);
//         int f_max = freq[25];
        
//         // Calculate how many tasks have that same max frequency
//         int count_max = 0;
//         for (int i = 25; i >= 0; i--) {
//             if (freq[i] == f_max) count_max++;
//             else break;
//         }
        
//         int formulaResult = (f_max - 1) * (n + 1) + count_max;
        
//         // The answer is the maximum of the formula or the actual task count
//         return Math.max(formulaResult, tasks.length);
//     }
// }

    /** Using Heap:-
    Count frequencies of each task
    Use max-heap to always process the most frequent task available
    Use queue to track tasks in cooldown with their available time
    Simulate time units:
        Each time unit, check if any task finished cooldown (add back to heap)
        If heap not empty, process most frequent task:
            Decrease its frequency
            If frequency > 0, add to cooldown queue with time it becomes available
        If heap empty but queue has tasks, we're in idle time
        Continue until both heap and queue are empty

    TC: O(m * log k); m = total tasks, k = unique tasks
    */
    public static int leastInterval(char[] tasks, int n) {
        // freq map
        Map<Character, Integer> map = new HashMap<>();
        for(char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        
        // max heap based on frequency
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int freq : map.values()) {
            pq.offer(freq);
        }
        
        // queue to track tasks in cooldown: [frequency, availableTime]
        /**
        The key difference:
            poll() on ArrayList requires shifting all remaining elements left by one position → O(n)
            poll() on LinkedList just updates the head pointer → O(1)
        */
        Queue<int[]> cooldown = new LinkedList<>();
        
        int time = 0;
        
        // continue until all tasks are processed
        while(!pq.isEmpty() || !cooldown.isEmpty()) {
            time++;
            
            // check if any task finished cooldown
            if(!cooldown.isEmpty() && cooldown.peek()[1] == time) {
                pq.offer(cooldown.poll()[0]);
            }
            
            // process most frequent available task
            if(!pq.isEmpty()) {
                int freq = pq.poll();
                freq--;
                
                // if task still has occurrences, add to cooldown
                if(freq > 0) {
                    cooldown.offer(new int[]{freq, time + n + 1});
                }
            }
            // if heap is empty but queue has tasks, we're idle (still counts as time)
        }
        return time;
    }

    public static void main(String[] args) throws Exception {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        int result = leastInterval(tasks, n);
        System.out.println(result);
    }
}