package deque;

import java.util.*;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(this.get(i), max) > 0) {
                max = this.get(i);
            }
        }
        return max;
    }


}
