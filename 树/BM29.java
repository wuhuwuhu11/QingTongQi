package 练习练习.树;

import java.util.LinkedList;
import java.util.Queue;

public class BM29 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if (root==null) return false;
        Queue<TreeNode> temp=new LinkedList<>();
        root.val=sum-root.val;
        temp.add(root);
        while (!temp.isEmpty()){
            TreeNode node=temp.poll();
            if (node.left==null && node.right==null && node.val==0) return true;
            if (node.left!=null){
                node.left.val=node.val-node.left.val;
                temp.add(node.left);
            }
            if (node.right!=null){
                node.right.val=node.val-node.right.val;
                temp.add(node.right);
            }


        }
        return false;
    }
}
