package deque;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void test() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 6; i++) {
            ad.addLast(i);
        }
        System.out.println(ad.size());

        for (int i = 0; i < 4; i++) {
            System.out.print(ad.removeLast() + " ");
        }
        System.out.println();

        for (int i = 2; i <= 7; i++) {
            ad.addLast(i);
        }

        ad.printDeque();
        System.out.println(ad.get(0));
        System.out.println(ad.size());
    }
}
