import java.io.*;
import java.util.*;

public class Problem_7 {
    class Solution {
    private long maxProduct = 0;
    private long totalSum = 0;
    private static final int MOD = 1_000_000_007;

    private static int totalSum(TreeNode r) {
        if (r == null) return 0;
        return r.val + totalSum(r.left) + totalSum(r.right);
    }

    // Post-order traversal to compute subtree sums and max product
    private static long dfs(TreeNode r) {
        if (r == null) return 0;

        long left = dfs(r.left);
        long right = dfs(r.right);

        long subTreeSum = r.val + left + right;

        long product = subTreeSum * (totalSum - subTreeSum);
        maxProduct = Math.max(maxProduct, product);

        return subTreeSum;
    }

    public static int maxProduct(TreeNode root) {
        totalSum = totalSum(root);   
        dfs(root);                  
        return (int)(maxProduct % MOD);
    }
}

    public static void main(String[] args) throws Exception {
        
    }
}