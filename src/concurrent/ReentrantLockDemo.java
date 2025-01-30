package concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    static ReentrantLock lock = new ReentrantLock();
    static int shared = 0;

    public static void main(String[] args) {

        for (int i = 1; i <= 10 ; i++) {
           new Thread(() -> {
               increment();
               System.out.println(shared);
               decrement();
               System.out.println(shared);
           }).start();
        }
    }

    public static void increment() {
        lock.lock();
        System.out.println("Lock acquired by increment");
        try {
            shared++;
        }
        finally {
            lock.unlock();
            System.out.println("Lock released by increment");
        }
    }

    public static void decrement() {
        lock.lock();
        System.out.println("Lock acquired by decrement");
        try {
            shared--;
        }
        finally {
            lock.unlock();
            System.out.println("Lock released by decrement");
        }
    }


}
