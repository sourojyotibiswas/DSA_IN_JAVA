import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

// HashMap + Check for need
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        // {need, index}
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int need = target - nums[i];
            if(map.containsKey(need)) return new int[]{map.get(need), i};
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] parts = line.split(",");
        int[] inp = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {
            inp[i] = Integer.parseInt(parts[i]);
        }

        int target = Integer.parseInt(br.readLine());
        System.out.println(Arrays.toString(twoSum(inp, target)));
        br.close();
    }
}