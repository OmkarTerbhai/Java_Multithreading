package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(Deadlock::reader).start();
        new Thread(Deadlock::writer).start();
    }

    private static void reader() {
        lock1.lock();
        System.out.println("Writer acq lock 1");
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ie) {}
        finally {
            lock2.lock();
            System.out.println("Writer acq lock 2");
            lock1.unlock();
            System.out.println("Writer released lock 1");
        }

    }

    private static void writer() {
        lock2.lock();
        System.out.println("Writer acq lock 2");
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ie) {}
        finally {
            lock1.lock();
            System.out.println("Writer acq lock 1");
            lock2.unlock();
            System.out.println("Writer released lock 2");
        }

    }
}
