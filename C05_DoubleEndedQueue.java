package goldmine.easy;

public class DoubleEndedQueue {
    private static class Node{
        final String data;
        Node next;
        Node prev;
        Node(String data){
            this.data=data;
        }
    }
    private Node first;
    private Node last;
    private int size;
    public void addFirst(String data){
        Node oldFirst = first;
        first = new Node(data);
        if (oldFirst == null)
            last= first;
        else{
            first.prev=null;
            first.next=oldFirst;
            oldFirst.prev=first;
        }
        size++;
    }
    public void addLast(String data){
        Node oldLast = last;
        last = new Node(data);
        if(oldLast==null)
            oldLast=last;
        else{
            last.prev=oldLast;
            last.next=null;
            oldLast.next=last;
        }
        size++;
    }
    public String removeFirst(){
        Node oldFirst = first;
        if(oldFirst == null)
            return null;
        else{
            first = oldFirst.next;
            if(first==null)
                last=first;
            else
                first.prev=null;
        }
        size--;
        return oldFirst.data;
    }

    public String removeLast(){
        Node oldLast=last;
        if(oldLast == null)
            return null;
        else{
            last = last.prev;
            if(last==null)
                first=last;
            else
                last.next=null;
        }
        size--;
        return oldLast.data;
    }
    public String peekFirst(){
        return first==null?null:first.data;
    }

    public String peekLast(){
        return last==null?null:last.data;
    }
}
