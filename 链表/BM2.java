package 练习练习.链表;
//BM2 链表内指定区间反转
public class BM2 {
    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseBetween (ListNode head, int m, int n) {
        //设置虚拟头结点
        ListNode first=new ListNode(-1);
        first.next=head;
        ListNode pre=first;
        //1.m的前一个节点处
        for (int i = 0; i <m-1 ; i++) {
            pre=pre.next;
        }
        //2.走n-m+1步到n节点
        ListNode rightNode=pre;
        for (int i = 0; i < n-m+1 ; i++) {
            rightNode=rightNode.next;
        }
        //3.截取一个子链
        ListNode leftNode=pre.next;
        ListNode cur=rightNode.next;
        //4.切断链表
        pre.next=null;
        rightNode.next=null;
        //5.反转局部链表
        fanzhaun(leftNode);
        //6.接回原来的链表
        pre.next=rightNode;
        leftNode.next=cur;
        return first.next;
    }


    public void fanzhaun(ListNode head){
        ListNode cur=head;
        ListNode pre=null;
        while (cur!=null){
            ListNode temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
    }
}
