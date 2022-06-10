package 练习练习.链表;
//BM3 链表中的节点每k个一组翻转
public class BM3 {
    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode first = new ListNode(-1);
        first.next=head;

        ListNode pre=first;
        ListNode end=first;
        while (end.next!=null){
            for (int i = 0; i < k && end!=null ; i++) {
                end=end.next;
            }
            if (end==null) break;
            ListNode left = pre.next;
            ListNode next = end.next;
            end.next=null;
            pre.next= fanzhuan(left);
            left.next=next;
            pre=left;
            end=left;
        }

        return first.next;
    }
    public static ListNode fanzhuan(ListNode head){
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode temp = cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}
