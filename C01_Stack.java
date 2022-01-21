public class Stack {
    int topA = -1;
    int max = 100;
    int[] stack = new int[max];

    Node top;

    void pushA(int x) {
        if (topA != max - 1) {
            topA++;
            stack[topA] = x;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    int popA() {
        if (topA != -1) {
            int item = stack[topA];
            topA--;
            return item;
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    void push(int element) {
        Node temp = new Node(element);
        if (top == null) {
            top = temp;
        } else {
            temp.next = top;
            top = temp;
        }
    }

    int pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        int item = top.data;
        top = top.next;
        return item;
    }
}
