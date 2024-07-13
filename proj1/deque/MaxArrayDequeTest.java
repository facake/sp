package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void testMaxName() {
        Comparator<Dog> nc = Dog.getNameComparator();
        MaxArrayDeque<Dog> mad = new MaxArrayDeque<>(nc);
        mad.addLast(new Dog("Bob", 10));
        mad.addLast(new Dog("Alice", 5));
        mad.addLast(new Dog("Cola", 3));
        assertEquals("Cola", mad.max().getName());
        System.out.println(mad.max().getName());
    }

    @Test
    public void testMaxSize() {
        Comparator<Dog> sc = Dog.getSizeComparator();
        MaxArrayDeque<Dog> mad = new MaxArrayDeque<>(sc);
        mad.addLast(new Dog("Bob", 10));
        mad.addLast(new Dog("Alice", 5));
        mad.addLast(new Dog("Cola", 3));
        assertEquals(10, mad.max().getSize());
        System.out.println(mad.max().getName());
    }

    @Test
    public void testPrimitiveMax() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        assertEquals(9, (int) mad.max());
        System.out.println(mad.max());
    }
}
