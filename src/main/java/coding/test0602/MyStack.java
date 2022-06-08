package coding.test0602;

import java.util.LinkedList;

/**
 * @Auther: zls
 * @Date: 2022/6/8 10:23
 * @Description:
 */
public class MyStack {

    private Integer size = 0;

    private LinkedList queue = new LinkedList();

    public void push(Integer val) {
        queue.add(val);
        size ++;
    }

    public Integer pop() {
        if (size == 0)
            return null;

        for (int i = 0;i < size - 1;i ++)
            queue.add(queue.remove());


        size --;
        return (Integer) queue.remove();
    }

    public Integer peek() {
        return (Integer) queue.getLast();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.peek());
        for (int i = 0;i < 3;i ++) {
            System.out.print(myStack.pop() + " ");
        }
    }

}
