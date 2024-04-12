package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> l1 = new AListNoResizing<>();
        BuggyAList<Integer> l2 = new BuggyAList<>();

        int x = 4;
        for (int i = 0; i < 3; i++) {
            l1.addLast(x);
            l2.addLast(x);
            x++;
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(l1.removeLast(), l2.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeBL = BL.size();
                System.out.print("sizeL: " + sizeL);
                System.out.println(" sizeBL: " + sizeBL);
            } else if (operationNumber == 2) {
                if (L.size() == 0 || BL.size() == 0) {
                    continue;
                } else {
                    int v1 = L.getLast();
                    int v2 = BL.getLast();
                    System.out.println("L: getLast(" + v1 + ")");
                    System.out.println("BL: getLast(" + v2 + ")");
                    assertEquals(v1, v2);
                }
            } else if (operationNumber == 3) {
                if (L.size() == 0 || BL.size() == 0) {
                    continue;
                } else {
                    int v1 = L.removeLast();
                    int v2 = BL.removeLast();
                    System.out.println("L: removeLast(" + v1 + ")");
                    System.out.println("BL: removeLast(" + v2 + ")");
                    assertEquals(v1, v2);
                }
            }
        }
    }
}
