import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;

    private class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node (T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }


    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        Node p = sentinel;
        for (int i = size; i > 0; i--) {
            returnlist.add(p.next.item);
            p = p.next;
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            T returnValue = sentinel.next.item;
            sentinel.next.next.prev = sentinel.next.prev;
            sentinel.next = sentinel.next.next;
            size--;
            return returnValue;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size > 0) {
            T returnValue = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel.prev.next;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return returnValue;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }

        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node p, int index){
        if (index > size - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }
}
