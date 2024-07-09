package deque;

public class ArrayDeque<T> {
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

    public void addFirst(T item) {
        this.resize();
        items[nextFirst] = item;
        this.nextFirst = prev(nextFirst);
        this.size++;
    }

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

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = get(next(nextFirst));
        nextFirst = next(nextFirst);
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T item = this.get(prev(nextLast));
        nextLast = prev(nextLast);
        size--;
        return item;
    }

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
//        if (size == items.length) {
//            T[] array = (T[]) new Object[items.length * 2];
//            arraycopy(items, array, next(nextFirst), size);
//            items = array;
//        } else if (1.0 * size / items.length <= 0.25) {
//            T[] array = (T[]) new Object[(int) (items.length * 0.5)];
//            arraycopy(items, array, next(nextFirst), size);
//            items = array;
//        }
    }

    private void arraycopy(T[] s, T[] d, int firstPos, int n) {
        for (int i = 0; i < n; i++) {
            d[firstPos] = s[firstPos];
            firstPos = next(firstPos);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
