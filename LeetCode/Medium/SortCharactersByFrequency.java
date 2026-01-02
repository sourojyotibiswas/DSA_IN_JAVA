import java.io.*;
import java.util.*;

public class SortCharactersByFrequency {
    /**
     * Approach 1:-
     */
    // public static String frequencySort(String s) {
    //     StringBuilder sb = new StringBuilder();
    //     Map<Character, Integer> mp = new HashMap<>();

    //     // count frequency
    //     for (char ch : s.toCharArray()) {
    //         mp.put(ch, mp.getOrDefault(ch, 0) + 1);
    //     }

    //     // convert map to list
    //     List<Map.Entry<Character, Integer>> list =
    //             new ArrayList<>(mp.entrySet());

    //     // sort by frequency descending
    //     list.sort((a, b) ->
    //             Integer.compare(b.getValue(), a.getValue())
    //     );

    //     // build result
    //     for (Map.Entry<Character, Integer> entry : list) {
    //         char key = entry.getKey();
    //         int freq = entry.getValue();

    //         for (int i = 0; i < freq; i++) {
    //             sb.append(key);
    //         }
    //     }

    //     return sb.toString();
    // }

    /**
     * Approach 2: (Priority Queue)
     */

    public static String frequencySort(String s) {
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int freq = pq.peek().getValue();
            char ch = pq.peek().getKey();
            for(int i = 0; i < freq; i++) {
                sb.append(ch);
            }
            pq.poll();
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String s = "tree";

        String result = frequencySort(s);
        System.out.println(result);
    }
}