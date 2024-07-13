package deque;

import java.util.Comparator;

public class Dog {
    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    private static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    private static class SizeComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.getSize() - d2.getSize();
        }
    }

    public static Comparator<Dog> getSizeComparator() {
        return new SizeComparator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
