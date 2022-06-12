package 练习练习.树;

import java.util.ArrayList;
import java.util.Set;

//BM34判断是不是二叉搜索树
//二叉搜索树的特性就是中序遍历是递增序。既然是判断是否是二叉搜索树，那我们可以使用中序递归遍历。
// 只要之前的节点是二叉树搜索树，那么如果当前的节点小于上一个节点值那么就可以向下判断。
public class BM34 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    int pre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {

        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val < pre) {
            return false;
        }
        pre = root.val;

        return isValidBST(root.right);
    }
}
