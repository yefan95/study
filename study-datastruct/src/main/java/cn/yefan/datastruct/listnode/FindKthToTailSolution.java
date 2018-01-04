package cn.yefan.datastruct.listnode;



import cn.yefan.datastruct.bean.ListNode;

import java.util.Stack;

/**
 * <p>
 * 链表中倒数第k个结点
 * <p>
 * <p>
 * 题目描述
 * 输入一个链表，输出该链表中倒数第k个结点。
 * <p>
 *
 * @author yefan
 * @date 2018/01/02
 */
public class FindKthToTailSolution {

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode pHead = head;
        int count = 0;
        while (pHead != null) {
            count++;
            pHead = pHead.next;
        }
        //判断k是否大于链表长度
        if (count < k) {
            return null;
        }
        System.out.println("count: " + count);
        int index = count - k + 1;
        ListNode target = head;
        while (index > 1) {
            target = target.next;
            index--;
        }
        return target;
    }

    public ListNode FindKthToTail1(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode pHead = head;
        while (pHead != null) {
            stack.push(pHead);
            pHead = pHead.next;
        }
        ListNode target = null;
        while (k > 1) {
            stack.pop();
            k--;
        }
        target = stack.pop();
        return target;
    }

    public static void main(String[] args) {
        FindKthToTailSolution solution = new FindKthToTailSolution();
        //{1,2,3,4,5}
        ListNode head = new ListNode(1);
        head.addNode(head, 2);
        head.addNode(head, 3);
        head.addNode(head, 4);
        head.addNode(head, 5);
        ListNode target = solution.FindKthToTail1(head, 1);
        System.out.println("value: " + target.val);
    }
}
