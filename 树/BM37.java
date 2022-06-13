package 练习练习.树;
//BM37 二叉搜索树的最近公共祖先
public class BM37 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        // write code here
        if (root==null) return -1;
        if ((p>root.val&&q<root.val)||(q>root.val&&p<root.val)){
            return root.val;
        }else if (p>root.val&&q>root.val){
           return lowestCommonAncestor(root.right,p,q);
        }else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}
