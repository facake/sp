package deque;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void test() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad.addLast(i);
        }
        System.out.println(ad.size());

        for (int i = 0; i < 6; i++) {
            System.out.print(ad.removeLast() + " ");
        }
        System.out.println();

        ad.printDeque();
        System.out.println(ad.size());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(0);
        ad.addLast(1);
        System.out.println(ad.removeFirst());
        System.out.println(ad.removeFirst());
        ad.removeFirst();
        ad.addLast(0);
        System.out.println(ad.get(0));
    }
}
