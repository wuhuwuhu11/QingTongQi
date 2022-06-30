package 练习练习.leecode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class 合并K个升序链表23 {
    public class  ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val- o2.val;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null) return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i]!=null){
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next!= null){
            heap.add(pre.next);
        }
        while (!heap.isEmpty()){
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next !=null){
                heap.add(cur.next);
            }
        }
        return head;
    }
}
