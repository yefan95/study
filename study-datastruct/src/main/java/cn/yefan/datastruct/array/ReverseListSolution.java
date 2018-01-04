package cn.yefan.datastruct.array;


import cn.yefan.datastruct.bean.ListNode;

import java.util.Stack;

/**
 * <p>
 * 反转链表
 * <p>
 * <p>
 * 题目描述
 * 输入一个链表，反转链表后，输出链表的所有元素。
 * <p>
 *
 * @author yefan
 * @date 2018/01/02
 */
public class ReverseListSolution {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode pHead = head;
        while (pHead != null) {
            stack.push(pHead.val);
            pHead = pHead.next;
        }
        while (head != null) {
            head.val = stack.pop();
            System.out.println("" + head.val);
            head = head.next;
        }
        return head;
    }

    public ListNode ReverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        ListNode pHead = pre;
        while (pHead != null) {
            System.out.println("" + pHead.val);
            pHead = pHead.next;
        }
        return pre;
    }

    public ListNode ReverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = ReverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }

    public static void main(String[] args) {
        ReverseListSolution solution = new ReverseListSolution();
        //{1,2,3,4,5}
        ListNode head = new ListNode(1);
        head.addNode(head, 2);
        head.addNode(head, 3);
        head.addNode(head, 4);
        head.addNode(head, 5);
        ListNode target = solution.ReverseList2(head);
        while (target != null) {
            System.out.println("" + target.val);
            target = target.next;
        }
    }

}
