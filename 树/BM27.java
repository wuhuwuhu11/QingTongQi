package 练习练习.树;

import java.util.*;

//BM27 按之字形顺序打印二叉树
public class BM27 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        TreeNode head=pRoot;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (head==null) return  res;
        Queue<TreeNode> temp = new LinkedList<>();
        temp.offer(head);
        TreeNode p;
        boolean flag=true;
        while (!temp.isEmpty()){
            ArrayList<Integer> row=new ArrayList<>();
            int n=temp.size();
            flag=!flag;
            for (int i = 0; i < n; i++) {
                p=temp.poll();
                row.add(p.val);
                if (p.left!=null) {
                    temp.offer(p.left);
                }
                if (p.right!=null){
                    temp.offer(p.right);
                }
            }
            if (flag){
                Collections.reverse(row);
            }
            res.add(row);
        }
        return res;
    }

}
