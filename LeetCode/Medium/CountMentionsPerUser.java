import java.io.*;
import java.util.*;

public class CountMentionsPerUser {
    public static int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            if (timeA != timeB)
                return timeA - timeB;
            return a.get(0).equals("OFFLINE") ? -1 : 1;
        });
        int[] mentions = new int[numberOfUsers];

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Set<Integer> offlineUsers = new HashSet<>();
        Map<Integer, Integer> offlineUntil = new HashMap<>();

        for (List<String> event : events) {
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));

            while (!minHeap.isEmpty() && minHeap.peek()[0] <= timestamp) {
                int[] e = minHeap.poll();
                int onlineTime = e[0];
                int userId = e[1];

                if (offlineUntil.getOrDefault(userId, -1) == onlineTime) {
                    offlineUsers.remove(userId);
                    offlineUntil.remove(userId);
                }
            }

            if (type.equals("MESSAGE")) {
                String mention = event.get(2);
                if (mention.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++)
                        mentions[i]++;
                }
                else if (mention.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (!offlineUsers.contains(i))
                            mentions[i]++;
                    }
                }
                else {
                    for (String s : mention.split(" ")) {
                        int id = Integer.parseInt(s.substring(2));
                        mentions[id]++;
                    }
                }

            }
            else { // OFFLINE
                int userId = Integer.parseInt(event.get(2));
                int onlineTime = timestamp + 60;

                offlineUsers.add(userId);
                offlineUntil.put(userId, onlineTime);
                minHeap.offer(new int[] { onlineTime, userId });
            }
        }

        return mentions;
    }
    public static void main(String[] args) throws Exception {
        int numberOfUsers1 = 2;
        List<List<String>> events1 = Arrays.asList(
            Arrays.asList("MESSAGE", "10", "id1 id0"),
            Arrays.asList("OFFLINE", "11", "0"),
            Arrays.asList("MESSAGE", "71", "HERE")
        );
        int[] result1 = countMentions(numberOfUsers1, events1);
        System.out.println(Arrays.toString(result1));
    }
}