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
// Approach 1: BFS with queue.
// TC: O(N); N = total number of nodes
// SC: O(N) for queue
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightmostValues = new ArrayList<>();
        if (root == null) {
            return rightmostValues;
        }
        Queue<TreeNode> nodesByLevel = new LinkedList<>();
        nodesByLevel.offer(root);
        // Iterate level by level.
        while (!nodesByLevel.isEmpty()) {
            int levelSize = nodesByLevel.size();
            // Iterate through nodes in the current level.
            for (int i = 0; i < levelSize; i ++) {
                TreeNode currentNode = nodesByLevel.poll();
                // Add left and right child nodes into nodesByLevel queue.
                if (currentNode.left != null) {
                    nodesByLevel.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodesByLevel.offer(currentNode.right);
                }
                if (i == levelSize - 1) {
                    // Last node in the level or right-most node.
                    rightmostValues.add(currentNode.val);
                }
            }
        }
        return rightmostValues;
    }
}