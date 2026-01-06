import java.io.*;
import java.util.*;

public class Problem_6 {
    private static int bfsLevelOrder(TreeNode root) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        if (root == null) {
            return res;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 1;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            int currentLevelSum = 0;
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                currentLevelSum += node.val;
                
                // Add children to queue for next level
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            
            if(currentLevelSum > max) {
                max = currentLevelSum;
                res = level;
            }
            level++;
        }
        
        return res;
    }
    public int maxLevelSum(TreeNode root) {
        return bfsLevelOrder(root);
    }

    public static void main(String[] args) throws Exception {
        
    }
}