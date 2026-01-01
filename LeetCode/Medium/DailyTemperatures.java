import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class DailyTemperatures {
    // generic pair class {temperature, wait}; wait = j - i
    public static class Pair <T, U> {
        T first;
        U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
    public static int[] solution(int[] nums) {
        int n = nums.length;
        // monotonic decreasing stack
        Deque<Pair<Integer, Integer>> st = new ArrayDeque<>();
        int[] res = new int[n];

        for(int i = n-1; i >=0; i--) {
            while(!st.isEmpty() && st.peek().first <= nums[i]) {
                st.pop();
            }
            if(st.isEmpty()) res[i] = 0;
            else res[i] = st.peek().second - i;
            st.push(new Pair<>(nums[i], i));
        }
        return res;
    }
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(",");
        int[] arr = new int[parts.length];
        int[] res = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        res = solution(arr);
        System.out.println(Arrays.toString(res));
        br.close();
    }
}
