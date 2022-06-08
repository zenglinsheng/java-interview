package coding.test0602;

import java.util.LinkedList;

/**
 * @Auther: zls
 * @Date: 2022/6/8 11:23
 * @Description:
 */
public class BSTree {

    private static class Node {
        private Integer val;
        private Node left;
        private Node right;

        public Node(Integer val) {
            this.val = val;
        }
    }

    private Node root;

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        root = add(root, node);
    }

    private Node add(Node root, Node node) {
        if (root == null)
            return node;

        if (root.val > node.val)
            root.left = add(root.left, node);
         else
            root.right = add(root.right, node);

        return root;
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    /**
     * 层序遍历
     */
    public void bfs(Node node) {
        if (node == null)
            return;

        LinkedList queue = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node removeNode = (Node) queue.poll();
            System.out.print(removeNode.val + " ");

            if (removeNode.left != null)
                queue.offer(removeNode.left);
            if (removeNode.right != null)
                queue.offer(removeNode.right);
        }
    }

    /**
     * 树反转
     */
    public void reverse(Node node) {
        if (node == null)
            return;

        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        reverse(node.left);
        reverse(node.right);
    }

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.add(new Node(6));
        tree.add(new Node(2));
        tree.add(new Node(5));
        tree.add(new Node(8));
        tree.add(new Node(7));
        tree.add(new Node(1));
        tree.add(new Node(10));
        tree.inOrder(tree.root);
        System.out.println();
//        tree.preOrder(tree.root);
//        System.out.println();
//        tree.postOrder(tree.root);
//        System.out.println();
//        tree.bfs(tree.root);
        tree.reverse(tree.root);
        tree.inOrder(tree.root);
    }

}
