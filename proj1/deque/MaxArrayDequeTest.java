package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void test() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>((i1, i2) -> i1 - i2);
        for (int i = 0; i < 5; i++) {
            mad.addLast(i);
        }
        assertEquals(4, (int) mad.max());
        System.out.println();
    }
}
