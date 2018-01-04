package cn.yefan.datastruct.listnode;

import cn.yefan.datastruct.bean.ListNode;

/**
 * 在O(1)时间内删除结点
 *
 * @author yefan
 * @date 2018/01/04
 */
public class DeleteNodeSolution {

    public void deleteNode(ListNode head, ListNode delNode) {
        if (head == null) {
            return;
        }
        //删除尾节点，采用顺序查找找到尾节点的前一节点
        if (delNode.next == null) {
            while (head.next != delNode) {
                head = head.next;
            }
            head.next = null;
        }
        //要删除的节点是头结点
        else if (head == delNode) {
            head = null;
        }
        //要删除的节点是中间普通节点
        else {
            delNode.val = delNode.next.val;
            delNode.next = delNode.next.next;
        }

    }

    public static void main(String[] args) {
        DeleteNodeSolution solution = new DeleteNodeSolution();
        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode header = new ListNode(1, node1);

        solution.deleteNode(header, node3);

        header.print(header);

    }

}
