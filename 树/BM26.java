package 练习练习.树;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//BM26 求二叉树的层序遍历
public class BM26 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> res =new ArrayList<>();
        if(root==null) return  res;
        Queue<TreeNode> temp= new LinkedList<>();
        temp.offer(root);
        while (!temp.isEmpty()){
            ArrayList<Integer> row =new ArrayList<>();
            int n= temp.size();
            for (int i = 0; i <n ; i++) {
                TreeNode p=temp.poll();
                row.add(p.val);
                if (p.left!=null){
                    temp.offer(p.left);
                }
                if (p.right!=null){
                    temp.offer(p.right);
                }
            }
            res.add(row);
        }
        return  res;
    }
}
