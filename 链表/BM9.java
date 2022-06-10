package 练习练习.链表;
//BM9 删除链表的倒数第n个节点
public class BM9 {

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        if (head == null) return head;
        ListNode fast = new ListNode(-1);
        fast.next=head;
        head=fast;
        ListNode slow=fast;
        for (int i = 0; i <=n; i++) {
            fast=fast.next;
        }
        while (fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;
        return head.next;
    }
}
