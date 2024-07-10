package deque;

import java.util.*;

public class MaxArrayDeque<T> extends ArrayDeque {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        return Collections.max((List<T>)Arrays.asList(this.getItems()), this.comparator);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        return Collections.max((List<T>) Arrays.asList(this.getItems()), c);
    }


}
