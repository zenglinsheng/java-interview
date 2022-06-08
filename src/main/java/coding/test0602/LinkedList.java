package coding.test0602;

/**
 * @Auther: zls
 * @Date: 2022/6/7 14:33
 * @Description: 构造、增、删、反转、判断环、删除特定节点、合并两个有序链表、查找倒数第几个节点
 */
public class LinkedList {

    /**
     * 节点
     */
    private static class Node {
        private Integer val;
        private Node next;

        public Node(Integer val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.val + " ");

            Node cur = this.next;
            while (cur != null) {
                sb.append(cur.val + " ");
                cur = cur.next;
            }

            return sb.toString();
        }
    }

    private Node head;

    public LinkedList() {}

    public LinkedList(int arr[]) {
        if (arr.length == 0)
            throw new ArithmeticException("The array is empty.");

        head = new Node(arr[0]);
        Node cur = head;
        for (int i = 1;i < arr.length;i ++) {
            Node node = new Node(arr[i]);
            cur.next = node;
            cur = cur.next;
        }
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
            return;
        }

        node.next = head;
        head = node;
    }

    public void remove(Node node) {
        if (head == null)
            return;

        if (head.val == node.val)
            head = head.next;

        Node pre = head;
        Node cur = head.next;
        while (cur != null && cur.val != node.val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null)
            pre.next = cur.next;
    }

    /**
     * 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public Node mergeTwo(LinkedList list1, LinkedList list2) {
        Node node1 = list1.head;
        Node node2 = list2.head;
        if (node1 == null || node2 == null)
            return node1 == null ? node2 : node1;

        Node resNode;
        if (node1.val < node2.val) {
            resNode = node1;
            node1 = node1.next;
        } else {
            resNode = node2;
            node2 = node2.next;
        }

        Node cur = resNode;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                cur = cur.next;
                node1 = node1.next;
            } else {
                cur.next = node2;
                cur = cur.next;
                node2 = node2.next;
            }
        }

        cur.next = node1 == null ? node2 : node1;
        return resNode;
    }

    /**
     * 合并两个有序链表（递归）
     * @param list1
     * @param list2
     * @return
     */
    public Node mergeTwo2(LinkedList list1, LinkedList list2) {
        return mergeTwo2(list1.head, list2.head);
    }

    private Node mergeTwo2(Node node1, Node node2) {
        if (node1 == null || node2 == null)
            return node1 == null ? node2 : node1;

        Node resNode;
        if (node1.val < node2.val) {
            resNode = node1;
            resNode.next = mergeTwo2(node1.next, node2);
        } else {
            resNode = node2;
            resNode.next = mergeTwo2(node1, node2.next);
        }

        return resNode;
    }

    /**
     * 判断是否为环
     * @param list
     * @return
     */
    public boolean isLoop(LinkedList list) {
        Node node = list.head;
        if (node == null || node.next == null)
            return false;

        Node slow = node;
        Node fast = node.next.next;
        while (fast != null && fast.next != null) {
            if (slow == fast)
                return true;

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    /**
     * 反转链表
     */
    public void reverse(LinkedList list) {
        Node cur = list.head;
        if (cur == null || cur.next == null)
            return;

        Node pre = null;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        list.head = pre;
    }

    /**
     * 反转链表（递归）
     */
    public void reverse2(LinkedList list) {
        Node node = reverse2(list.head);
        list.head = node;
    }

    private Node reverse2(Node node) {
        if (node == null || node.next == null)
            return node;

        Node next = reverse2(node.next);
        node.next.next = node;
        node.next = null;

        return next;
    }

    /**
     * 查找倒数第几个节点
     * @param list
     * @param n
     * @return
     */
    public Node inverseFind(LinkedList list, int n) {
        if (n <= 0)
            throw new ArithmeticException("The number must greater than zero.");

        Node head = list.head;
        if (head == null)
            return null;

        Node slow = head;
        Node fast = head;
        for (int i = 0;i < n;i ++) {
            fast = fast.next;
            if (fast == null && i == n - 1)
                return head;
            else if (fast == null)
                return null;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.val);
            cur = cur.next;
            if (cur != null)
                sb.append("->");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 1.测试构造函数和add()
        int arr[] = {1,2,3,4,5};
        LinkedList list = new LinkedList(arr);
        System.out.println("测试构造函数：" + list);
        list.add(new Node(10));
        list.add(new Node(20));
        System.out.println("测试add()：" + list);
        list.remove(new Node(10));
        System.out.println("测试remove()：" + list);

        // 2.测试mergeTwo
        int arr1[] = {1,3,5,7,9};
        LinkedList list1 = new LinkedList(arr1);
        int arr2[] = {2,4,6,8,10};
        LinkedList list2 = new LinkedList(arr2);
        Node mergeNode = list.mergeTwo2(list1, list2);
        System.out.println("测试mergeTwo()：" + mergeNode);

        // 3.判断是否为环
        LinkedList list3 = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        list3.add(node1);
        list3.add(node2);
        list3.add(node3);
        list3.add(node4);
        list3.add(node5);
        node1.next = node5;
        boolean loop = list3.isLoop(list3);
        System.out.println(loop);

        // 4.反转链表
        int arr4[] = {1,2,3,4,5};
        LinkedList list4 = new LinkedList(arr4);
        System.out.println("反转前链表：" + list4);
        list4.reverse2(list4);
        System.out.println("反转后链表：" + list4);

        // 5.查找倒数第几个节点
        int arr5[] = {1,2,3,4,5,6,7,8};
        LinkedList list5 = new LinkedList(arr5);
        Node node = list5.inverseFind(list5, 0);
        System.out.println(node != null ? node.val : null);
    }
}
