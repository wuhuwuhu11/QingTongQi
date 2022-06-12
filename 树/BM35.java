package 练习练习.树;
//
//BM35 判断是不是完全二叉树

import java.util.LinkedList;
import java.util.Queue;

public class BM35 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean isCompleteTree (TreeNode root) {
        // write code here
        if (root==null) return true;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        boolean notComplete = false;
        while (!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if (cur==null){
                notComplete=true;
                continue;
            }
            if (notComplete){
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }
}
