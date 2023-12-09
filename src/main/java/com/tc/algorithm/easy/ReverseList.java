package com.tc.algorithm.easy;

/**
 * desc 链表反转
 *
 * @author lvzf 2023年12月06日
 */
public class ReverseList {

    public static class ListNode {
        private int val;
        private ListNode next = null;

        public ListNode (int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static ListNode reverseList (ListNode head) {

        ListNode next = null;
        ListNode pre = null;

        if (head != null) {
            while (head != null) {
                next = head.getNext();
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        head.setNext(node2);
        node2.setNext(node3);

        ListNode h = head;

        while (head != null) {
            System.out.print(head.getVal() + ",");
            head = head.getNext();
        }
        System.out.println();

        ListNode reverse = ReverseList.reverseList(h);

        while (reverse != null) {
            System.out.print(reverse.getVal() + ",");
            reverse = reverse.getNext();
        }
        System.out.println();
    }
}
