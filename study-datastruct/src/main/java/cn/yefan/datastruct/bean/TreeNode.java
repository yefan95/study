package cn.yefan.datastruct.bean;

/**
 * TreeNode
 *
 * @author yefan
 * @date 2017/12/30
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    /**
     * 重构二叉树
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        TreeNode root = ConstructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);

        return root;
    }


    private TreeNode ConstructCore(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        if (startPre == endPre && startIn == endIn) {
            return root;
        }

        int rootInOrder = startIn;
        while (rootInOrder <= endIn) {
            if (pre[startPre] == in[rootInOrder]) {
                root.left = ConstructCore(pre, startPre + 1, startPre + rootInOrder - startIn, in, startIn, rootInOrder - 1);
                root.right = ConstructCore(pre, rootInOrder - startIn + startPre + 1, endPre, in, rootInOrder + 1, endIn);
                break;
            }
            ++rootInOrder;
        }
        return root;
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    public void printBinaryTreeByPre(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            if (root.left != null) {
                System.out.print(root.left.val + " ");
                printBinaryTreeByPre(root.left.left);
                printBinaryTreeByPre(root.left.right);

            }
            if (root.right != null) {
                System.out.print(root.right.val + " ");
                printBinaryTreeByPre(root.right.left);
                printBinaryTreeByPre(root.right.right);

            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void printBinaryTreeByIn(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                printBinaryTreeByIn(root.left.left);
                System.out.print(root.left.val + " ");
                printBinaryTreeByIn(root.left.right);

            }
            System.out.print(root.val + " ");
            if (root.right != null) {
                printBinaryTreeByIn(root.right.left);
                System.out.print(root.right.val + " ");
                printBinaryTreeByIn(root.right.right);

            }
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void printBinaryTreeByEnd(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                printBinaryTreeByEnd(root.left.left);
                printBinaryTreeByEnd(root.left.right);
                System.out.print(root.left.val + " ");
            }
            if (root.right != null) {
                printBinaryTreeByEnd(root.right.left);
                printBinaryTreeByEnd(root.right.right);
                System.out.print(root.right.val + " ");
            }
            System.out.print(root.val + " ");
        }
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] end = {7, 4, 2, 5, 8, 6, 3, 1};

        TreeNode treeNode = new TreeNode();
        TreeNode root = treeNode.reConstructBinaryTree(pre, in);
        System.out.println("先序遍历");
        treeNode.printBinaryTreeByPre(root);
        System.out.println("\n");
        System.out.println("中序遍历");
        treeNode.printBinaryTreeByIn(root);
        System.out.println("\n");
        System.out.println("后序遍历");
        treeNode.printBinaryTreeByEnd(root);


    }

}
