package 练习练习.链表;

import java.lang.reflect.Field;

//BM10 两个链表的第一个公共结点
public class BM10 {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static  ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        ListNode head1=pHead1;
        ListNode head2=pHead2;
        int a=0;
        int b=0;
        while (head1!=null){
            head1=head1.next;
            a++;
        }
        while (head2!=null){
            head2=head2.next;
            b++;
        }
        head1=pHead1;
        head2=pHead2;
        while (a>b){
            head1=head1.next;
            a--;
        }
        while (a<b){
            head2=head2.next;
            b--;
        }
        while (head1!=null){
            if (head1==head2) return head1;
            head1=head1.next;
            head2=head2.next;
        }
        return null;
    }
}
