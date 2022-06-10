package 练习练习.树;

import java.util.ArrayList;
import java.util.List;

//BM25 二叉树的后序遍历
public class BM25 {
    public class  TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void houxu(List<Integer> list, TreeNode head){

        houxu(list,head.left);
        houxu(list,head.right);
        list.add(head.val);

    }


    public int[] postorderTraversal (TreeNode root) {
        List<Integer> list=new ArrayList<>();
        houxu(list,root);
        int[] res=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }
}
