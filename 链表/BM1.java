package 练习练习.链表;

//BM1 反转链表
public class BM1 {
    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode ReverseList(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode cur=head;
        ListNode pre=null;
        while(cur!=null){
            ListNode temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }

}
