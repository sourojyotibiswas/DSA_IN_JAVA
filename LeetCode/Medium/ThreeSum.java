import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// sort - iterate i -> n : 2 pointer - i+1 & n-1 + skip duplicates
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < n-2; i++) {
            // slip duplicates for i
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i+1;
            int r = n-1;
            while(l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    // skip duplicates for l & r
                    while(l < r && nums[l] == nums[l+1]) l++;
                    while(l < r && nums[r] == nums[r-1]) r--;

                    l++; r--;
                }
                else if(sum < 0) l++;
                else r--;
            }
        }
        return res;
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(",");
        int[] inp = new int[parts.length];
        
        for(int i = 0; i < parts.length; i++) {
            inp[i] = Integer.parseInt(parts[i]);
        }

        List<List<Integer>> res = threeSum(inp);
        for(List<Integer> eachList : res) {
            for(Integer it : eachList) {
                System.out.print(it + " ");
            }
            System.out.println();
        }
        br.close();
    }
}