package linkedlist;

public class PrintNthFromLast {
    Node printNthFromLast(int n, Node head) {
        int len = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        if (len < n)
            return null;
        temp = head;
        for (int i = 1; i < len - n + 1; i++)
            temp = temp.next;
        return temp;
    }
}
