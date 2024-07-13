package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    @Override
    public void addFirst(T item) {
        this.resize();
        items[nextFirst] = item;
        this.nextFirst = prev(nextFirst);
        this.size++;
    }

    @Override
    public void addLast(T item) {
        this.resize();
        items[nextLast] = item;
        this.nextLast = next(nextLast);
        this.size++;
    }

    private int prev(int i) {
        if (i == 0) {
            return items.length - 1;
        }
        return i - 1;
    }

    private int next(int i) {
        if (i == items.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = this.items[next(nextFirst)];
        nextFirst = next(nextFirst);
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T item = this.items[prev(nextLast)];
        nextLast = prev(nextLast);
        size--;
        return item;
    }

    @Override
    public T get(int i) {
        if (i >= 0 && i < this.items.length) {
            int targetPos = next(nextFirst);
            for (int j = 0; j < i; j++) {
                targetPos = next(targetPos);
            }
            return this.items[targetPos];
        }
        return null;
    }

    private void resize() {
        if (size == items.length) {
            T[] array = (T[]) new Object[items.length * 2];
            arraycopy(array, size);
        } else if (items.length > 8 && 1.0 * size / items.length <= 0.25) {
            T[] array = (T[]) new Object[(int) (items.length * 0.5)];
            arraycopy(array, size);
        }
    }

    private void arraycopy(T[] d, int n) {
        int srcPos = next(nextFirst);
        for (int i = 0; i < n; i++) {
            d[i] = items[srcPos];
            srcPos = next(srcPos);
        }
        items = d;
        nextFirst = prev(0);
        nextLast = size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int p;

        public ArrayDequeIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return p < size;
        }

        @Override
        public T next() {
            T returnItem = items[p];
            p++;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            if (this.size != ((Deque<?>) o).size()) {
                return false;
            }
            for (T item : this) {
                for (int i = 0; i < ((Deque<?>) o).size(); i++) {
                    if (item.equals(((Deque<?>) o).get(i))) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean contains(T item) {
        return false;
    }
}
