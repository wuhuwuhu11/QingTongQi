package 练习练习.树;

import java.util.LinkedList;
import java.util.Queue;

public class bm31 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot==null) return true;
        Queue<TreeNode> q1=new LinkedList<>();
        Queue<TreeNode> q2=new LinkedList<>();
        q1.add(pRoot.left);
        q2.add(pRoot.right);
        while (!q1.isEmpty()&&!q2.isEmpty()){
            TreeNode left=q1.poll();
            TreeNode right=q2.poll();
            if (left==null&&right==null){
                continue;
            }
            if (left==null||right==null||left.val!=right.val) return false;
            q1.offer(left.left);
            q1.offer(left.right);
            q2.offer(right.right);
            q2.offer(right.left);
        }
        return true;
    }
}
