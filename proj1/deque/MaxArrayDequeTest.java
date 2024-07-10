package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void test() {
        Comparator<Integer> c = Comparator.naturalOrder();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(c);
        for (int i = 0; i < 5; i++) {
            mad.addLast(i);
        }
        assertEquals(4, (int) mad.max());
        System.out.println();
    }
}
