package 练习练习.树;

//BM36 判断是不是平衡二叉树
public class BM36 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int deep(TreeNode head) {
        if (head == null) return 0;
        int left = deep(head.left);
        int right = deep(head.right);
        return left > right ? left + 1 : right + 1;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        int left = deep(root.left);
        int right = deep(root.right);
        if (left - right > 1 || right - left > 1) return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
}
