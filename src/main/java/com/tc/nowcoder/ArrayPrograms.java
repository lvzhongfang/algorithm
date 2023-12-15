package com.tc.nowcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * desc 数组问题
 *
 * @author lvzf
 * @date 2023/12/14
 */
public class ArrayPrograms {

    public static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverse (ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    public ListNode reverseBetween (ListNode head, int m, int n) {
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode pre = res;
        ListNode cur = head;
        //找到m
        for (int i = 1; i < m; i++) {
            pre = cur;
            cur = cur.next;
        }

        for (int i = m; i < n; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }

        return res.next;
    }

    public ListNode reverseBetween1 (ListNode head, int m, int n) {
        List<Integer> list = new ArrayList<>();
        while (head.next != null) {
            list.add(head.val);
            head = head.next;
        }
        list.add(head.val);
        // write code here
        m = m -1;
        n = n -1;
        while (m < n) {
            int temp = list.get(m);
            int temp1 = list.get(n);
            list.remove(m);
            list.add(m, temp1);
            list.remove(n);
            list.add(n, temp);
            m++;
            n--;
        }
        ListNode pre = null;
        for (int i = 0; i < list.size(); i++) {
            ListNode current = new ListNode(list.get(i));
            if (i == 0) {
                head = current;
                pre = current;
            } else {
                pre.next = current;
                pre = current;
            }
        }

        return head;
    }

    public int search (int[] nums, int target) {
        // write code here
        if (nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length;
        int middle = 0;
        while (low <= high) {
            middle = (low + high) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        ArrayPrograms ap = new ArrayPrograms();

        //head = ap.reverseBetween(head, 2, 4);
        head = ap.reverse(head);
        System.out.print("[");
        while (head.next != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println(head.val + "]");

        int [] nums = {-1,1};

        System.out.println(ap.search(nums, -1));
    }
}
