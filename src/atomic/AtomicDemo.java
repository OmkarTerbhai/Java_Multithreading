package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger int1 = new AtomicInteger();

        boolean val1 = int1.compareAndSet(0, 6);
        boolean val2 = int1.compareAndSet(0, 8);

        System.out.println("Val 1 : " + val1);
        System.out.println("Val 2 : " + val2);

        System.out.println(int1);
    }
}
