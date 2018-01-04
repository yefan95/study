package cn.yefan.datastruct.listnode;


import cn.yefan.datastruct.bean.ListNode;

/**
 * <p>
 * 合并两个排序的链表
 * <p>
 * <p>
 * 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 * <p>
 *
 * @author yefan
 * @date 2018/01/02
 */
public class ListNodeMergeSolution {

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 != null) {
            return list2;
        }
        if (list1 != null && list2 == null) {
            return list1;
        }
        if (list1 == null && list2 == null) {
            return null;
        }
        ListNode head;
        if (list1.val >= list2.val) {
            head = new ListNode(list2.val);
            list2 = list2.next;
        } else {
            head = new ListNode(list1.val);
            list1 = list1.next;
        }
        ListNode pHead = head;
        ListNode newNode;
        while (true) {
            if (list1 == null && list2 == null) {
                break;
            } else if (list1 == null) {
                newNode = new ListNode(list2.val);
                list2 = list2.next;
            } else if (list2 == null) {
                newNode = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                if (list1.val >= list2.val) {
                    newNode = new ListNode(list2.val);
                    list2 = list2.next;
                } else {
                    newNode = new ListNode(list1.val);
                    list1 = list1.next;
                }
            }
            pHead.next = newNode;
            pHead = newNode;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNodeMergeSolution solution = new ListNodeMergeSolution();
        //{1,2,3,4,5}
        ListNode head1 = new ListNode(1);
        head1.addNode(head1, 2);
        head1.addNode(head1, 3);
        head1.addNode(head1, 4);
        head1.addNode(head1, 5);
        ListNode head2 = new ListNode(1);
        head1.addNode(head2, 4);
        head1.addNode(head2, 5);
        head1.addNode(head2, 7);
        ListNode target = solution.Merge(head1, head2);
        while (target != null) {
            System.out.println("" + target.val);
            target = target.next;
        }
    }

}
