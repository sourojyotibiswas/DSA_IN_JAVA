import java.io.BufferedReader;
import java.io.InputStreamReader;

// Two pointers - start & end + Slide i or j based on smaller height
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int n = height.length;

        int i = 0, j = n-1, cap = 0;
        while(i < j) {
            int minHeight = Math.min(height[i], height[j]);
            int tempCap = minHeight * (j - i);
            cap = Math.max(tempCap, cap);

            // slide i or j based on which is small
            if(height[i] < height[j]) i++;
            else j--;
        }
        return cap;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(",");
        int[] inp = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {
            inp[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(maxArea(inp));
        br.close();
    }
}