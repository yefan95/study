package cn.yefan.datastruct.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈的功能
 *
 * @author
 * @date 2017/12/29
 */
public class QueueSolution {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();


    public int pop() {
        if (queue1.size() < 1) {
            return 0;
        }
        while (queue1.size() != 1) {
            queue2.add(queue1.poll());
        }
        int top = queue1.poll();
        while (queue2.size() > 0) {
            queue1.add(queue2.poll());
        }
        return top;

    }

    public void push(Integer value) {
        queue1.add(value);
    }

    public static void main(String[] args) {
        QueueSolution queueSolution = new QueueSolution();
        queueSolution.push(1);
        queueSolution.push(2);
        queueSolution.push(3);
        System.out.println(queueSolution.pop());
        System.out.println(queueSolution.pop());
        System.out.println(queueSolution.pop());
    }

}
