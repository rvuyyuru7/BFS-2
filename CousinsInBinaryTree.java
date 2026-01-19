/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Approach 1: BFS with queue
    // TC: O(N); N = total number of TreeNodes
    // SC: O(N) for nodesByLevel queue
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodesByLevel = new LinkedList<>();
        nodesByLevel.offer(root);
        while (!nodesByLevel.isEmpty()) {
            int levelSize = nodesByLevel.size();
            boolean xFound = false;
            boolean yFound = false;
            for (int i = 0; i < levelSize; i ++) {
                TreeNode currentNode = nodesByLevel.poll();
                if (currentNode.val == x) {
                    xFound = true;
                }
                if (currentNode.val == y) {
                    yFound = true;
                }
                // Check if x and y belong to same parent
                if (currentNode.left != null && currentNode.right != null) {
                    if ((currentNode.left.val == x && currentNode.right.val == y)
                        || (currentNode.left.val == y && currentNode.right.val == x)
                    ) {
                        return false; // same parent
                    }
                }
                // Add left child to nodesByLevel queue.
                if (currentNode.left != null) {
                    nodesByLevel.offer(currentNode.left);
                }
                // Add right child to nodesByLevel queue.
                if (currentNode.right != null) {
                    nodesByLevel.offer(currentNode.right);
                }
            }
            if (xFound && yFound) {
                return true; // cousins
            }
            if (xFound || yFound) {
                return false; // not cousins
            }
        }
        return false; // not found
    }
}