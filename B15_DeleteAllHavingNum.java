package linkedlist;

public class DeleteAllHavingNum {
    Node deleteAllOccurences(Node head, int data) {
        Node curr = head;
        while (curr != null && curr.data == data) {
            curr = head.next;
            head = head.next;
        }
        while (curr != null && curr.next != null) {
            if (curr.next.data == data)
                curr.next = curr.next.next;
            else
                curr = curr.next;
        }
        return head;
    }

}
