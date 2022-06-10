package 练习练习.树;
//题解 | #二叉搜索树与双向链表#
public class BM30 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeNode pre=null;
    TreeNode head=null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree==null) return null;
        Convert(pRootOfTree.left);

        if (pre==null){
            head=pRootOfTree;
            pre=pRootOfTree;
        }else {
            pre.right=pRootOfTree;
            pRootOfTree.left=pre;
            pre=pRootOfTree;
        }
        Convert(pRootOfTree.right);
        return head;
    }
}
