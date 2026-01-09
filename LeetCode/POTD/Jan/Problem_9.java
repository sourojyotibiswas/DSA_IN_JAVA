class Solution {
    private class NodeDepth {
        TreeNode node;
        int depth;
        
        NodeDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return findSubtree(root).node;
    }
    
    private NodeDepth findSubtree(TreeNode node) {
        if (node == null) {
            return new NodeDepth(null, -1);
        }
        
        NodeDepth left = findSubtree(node.left);
        NodeDepth right = findSubtree(node.right);
        
        if (left.depth == right.depth) {
            return new NodeDepth(node, left.depth + 1);
        }
        
        if (left.depth > right.depth) {
            return new NodeDepth(left.node, left.depth + 1);
        } else {
            return new NodeDepth(right.node, right.depth + 1);
        }
    }
}
