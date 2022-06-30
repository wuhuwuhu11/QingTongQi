package 练习练习.leecode;

public class 合并两个有序链表21 {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null ) return list2;
        if (list2 == null ) return list1;
        ListNode head = list1.val >= list2.val ? list2 : list1;
        ListNode cur1 = head.next;
        ListNode cur2 = head == list1 ? list2 : list1;
        ListNode pre = head;
        while (cur2 != null && cur1 != null) {
            if (cur1.val >= cur2.val) {
                pre.next = cur2;
                cur2 = cur2.next;
            } else {
                pre.next = cur1;
                cur1 = cur1.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}
