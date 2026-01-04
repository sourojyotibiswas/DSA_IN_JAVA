import java.io.*;
import java.util.*;

public class ReorganizeString {
    /**
    1. How can you say that you can have a solution??
    -  If one character appears more than half the positions, then not possible
    i.e. max_freq ≤ (n + 1) / 2

    2. How to build the solution string??
    Approach 1:-
        Repeatedly:
            Extract the character with highest frequency
            Append it to result
            Extract the character with next highest frequency
            Append it to result
            Decrease both frequencies and put them back if frequency > 0
        Handle the last remaining character (if string length is odd)
    
    Approach 2:-
        Fill even indices first (0, 2, 4, ...) with the most frequent character
        If even indices fill up, continue with odd indices (1, 3, 5, ...)
        Move to next most frequent character and repeat
        This ensures maximum spacing between identical characters
    */
   // Approach 1:-
    public static String reorganizeString(String s) {
        int n = s.length();

        // max heap
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // freq map
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        // build the max heap
        for(Map.Entry<Character, Integer> entry : map.entrySet()) pq.offer(entry);

        // early check - if you can get a valid permutation or not
        if(pq.peek().getValue() > (n+1)/2) return "";

        // resultant string
        StringBuilder sb = new StringBuilder();

        while(pq.size() >= 2) {
            // extract two most frequent characters
            Map.Entry<Character, Integer> first = pq.poll();
            Map.Entry<Character, Integer> second = pq.poll();
            
            // append both to result
            sb.append(first.getKey());
            sb.append(second.getKey());
            
            // decrease frequencies and add back if still positive
            int newFreq1 = first.getValue() - 1;
            int newFreq2 = second.getValue() - 1;
            
            if(newFreq1 > 0) pq.offer(new AbstractMap.SimpleEntry<>(first.getKey(), newFreq1)); // Java 9+ : Map.entry(first.getKey(), newFreq1)
            if(newFreq2 > 0) pq.offer(new AbstractMap.SimpleEntry<>(first.getKey(), newFreq1)); // Java 9+ : Map.entry(second.getKey(), newFreq2)
        }
        
        // handle last remaining character (if any)
        if(!pq.isEmpty()) sb.append(pq.poll().getKey());

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.print(reorganizeString("aab"));
    }
}