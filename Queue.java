import linkedlist.Node;

public class Queue {
    Node head,tail;
    int headA=-1;int tailA=-1;
    int size=100;int []arr=new int[size];
    void enqueue(int element) {
        Node temp = new Node(element);
        if (head == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = tail.next;
        }
    }
    int dequeue() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        int item = head.data;
        head = head.next;
        return item;
    }
    boolean enqueueA(int e) {
        if (size == arr.length)
            return false;
        headA = (headA + 1) % arr.length;
        arr[headA] = e;
        size++;
        if (tailA == -1) {
            tailA = headA;
        }
        return true;
    }
    boolean dequeueA() {
        if (size == 0) {
            return false;
        }
        arr[tailA] = 0;
        size--;
        tailA = (tailA + 1) % arr.length;
        if (size == 0) {
            headA = -1;
            tailA = -1;
        }
        return true;
    }
}
