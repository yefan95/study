package cn.yefan.datastruct.bean;


import java.util.Stack;
/**
 * ListNode
 *
 * @author yefan
 * @date 2017/12/29
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 在尾部添加节点
     *
     * @param node
     * @param value
     */
    public void addNode(ListNode node, int value) {
        ListNode newNode = new ListNode(value);
        if (node == null) {
            node = newNode;
        } else {
            ListNode tmp = node;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }

    /**
     * 在指定位置添加节点
     *
     * @param head
     * @param value
     * @param position
     */
    void insertNode(ListNode head, int value, int position) {
        ListNode tmp = head;
        ListNode newNode = new ListNode(value);
        if (position == 1) {
            newNode.next = tmp;
            return;
        }
        while (tmp != null && position > 1) {
            tmp = tmp.next;
            --position;
        }
        newNode.next = tmp.next;
        tmp.next = newNode;
    }

    /**
     * 删除尾部节点
     *
     * @param head
     * @param value
     */
    void delNode(ListNode head, int value) {

        if (head.val == value) {
            head = head.next;
        } else {
            ListNode tmp = head;
            while (tmp.next != null && tmp.next.val != value) {
                tmp = tmp.next;
            }
            if (tmp.next != null && tmp.next.val == value) {
                tmp.next = tmp.next.next;
            }
        }
    }

    /**
     * 查找指定位置节点
     *
     * @param head
     * @param position
     * @return
     */
    int findNode(ListNode head, int position) {
        ListNode tmp = head;
        while (tmp != null && position > 1) {
            tmp = tmp.next;
            --position;
        }
        return tmp.val;
    }

    /**
     * 从头到尾输出节点
     *
     * @param head
     */
    void print(ListNode head) {
        if (head == null) {
            System.out.println("\t");
            return;
        }
        System.out.print(head.val + " ");
        print(head.next);
    }

    /**
     * 从尾到头输出节点
     *
     * @param head
     * @return
     */
    void printReverse(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode tmp = head;
        while (tmp != null) {
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.addNode(head, 2);
        head.addNode(head, 3);
        head.addNode(head, 4);
        head.delNode(head, 3);
        head.insertNode(head, 3, 2);
        head.print(head);
        int value = head.findNode(head, 2);
        System.out.println("value " + value);
    }

}
