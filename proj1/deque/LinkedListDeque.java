package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node() {}

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentF = new Node();
    private Node sentB = new Node();
    private int size;

    public LinkedListDeque() {
        this.sentF.next = this.sentB;
        this.sentB.prev = this.sentF;
        this.size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node node = new Node(sentF, item, sentF.next);
        sentF.next.prev = node;
        sentF.next = node;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node node = new Node(sentB.prev, item, sentB);
        sentB.prev.next = node;
        sentB.prev = node;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        T item = sentF.next.item;
        sentF.next.next.prev = sentF;
        sentF.next = sentF.next.next;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        size--;
        T item = sentB.prev.item;
        sentB.prev.prev.next = sentB;
        sentB.prev = sentB.prev.prev;
        return item;
    }

    @Override
    public T get(int i) {
//      可以一分为二，从sentF开始遍历到中点，或者从sentB开始遍历到中点
        if (i < 0 || i >= this.size) {
            return null;
        }
        Node p = sentF.next;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public void printDeque() {
        Node p = sentF;
        while (p.next != null && p.next != sentB) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }
}



















