package 练习练习.树;

import java.util.ArrayList;
import java.util.List;

//BM24 二叉树的中序遍历
public class BM24 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void zhongxu(List<Integer> list, TreeNode head){
        if (head==null){
            return;
        }
        zhongxu(list,head.left);
        list.add(head.val);
        zhongxu(list,head.right);
    }

    public int[] inorderTraversal (TreeNode root) {
        List<Integer> list=new ArrayList<>();
        zhongxu(list,root);
        int[] res=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }
}
