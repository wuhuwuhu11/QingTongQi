package 练习练习.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//BM28 二叉树的最大深度
public class BM28 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public int maxDepth (TreeNode root) {
        Queue<TreeNode> temp= new LinkedList<>();
        int res = 0;
        if (root==null) return  res;
        temp.add(root);
        while (!temp.isEmpty()){
            int n= temp.size();
            for (int i = 0; i < n; i++) {
                TreeNode p =temp.poll();
                if (p.left!=null){
                    temp.add(p.left);
                }
                if (p.right!=null){
                    temp.add(p.right);
                }
            }
            res++;
        }
        return  res;
    }
}
