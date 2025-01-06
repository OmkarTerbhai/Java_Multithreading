package synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> li = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10000 ; i++) {
                li.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10000 ; i++) {
                li.add(i);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(li.size());
    }
}
