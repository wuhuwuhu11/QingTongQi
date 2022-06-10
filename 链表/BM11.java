package 练习练习.链表;

import java.util.ArrayList;
import java.util.Arrays;

//BM11 链表相加(二)
public class BM11 {
    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(7);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l4.next = l5;
        ListNode L1 = addInList(l1, l4);
        while (L1 != null) {
            System.out.print(L1.val);
            L1 = L1.next;
        }
    }

    public static ListNode addInList(ListNode head1, ListNode head2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        while (head1 != null) {
            str1.append(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            str2.append(head2.val);
            head2 = head2.next;
        }
        Long a = Long.valueOf(String.valueOf(str1));
        Long b = Long.valueOf(String.valueOf(str2));
        Long c= a+b;
        str3.append(c);
        ArrayList<Long> arr=new ArrayList<>();
        for (int i = 0; i < str3.length(); i++) {
            arr.add((long) (str3.charAt(i)-'0'));
        }
        System.out.println(arr);
        ListNode pre=new ListNode(Math.toIntExact(arr.get(0)));
        ListNode cur=pre;
        for (int i = 1; i < arr.size(); i++) {
            cur.next = new ListNode(Math.toIntExact(arr.get(i)));
            cur = cur.next;
        }
        return pre;
    }
}

