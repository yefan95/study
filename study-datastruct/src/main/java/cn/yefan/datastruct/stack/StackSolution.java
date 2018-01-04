package cn.yefan.datastruct.stack;

import java.util.Stack;

public class StackSolution {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

        if (!stack2.empty()) {
            return stack2.pop();
        } else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        StackSolution stackSolution = new StackSolution();
        stackSolution.push(1);
        stackSolution.push(2);
        stackSolution.push(3);
        System.out.println(stackSolution.pop());
        stackSolution.push(4);
        System.out.println(stackSolution.pop());
        System.out.println(stackSolution.pop());
        System.out.println(stackSolution.pop());
    }

}
