package 练习练习.树;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

//BM33 二叉树的镜像
public class BM33 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if (pRoot==null) return null;
        TreeNode left = Mirror(pRoot.left);
        TreeNode right = Mirror(pRoot.right);
        pRoot.left=right;
        pRoot.right=left;
        return pRoot;
    }
}
