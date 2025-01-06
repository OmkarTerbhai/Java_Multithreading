package locks;

import java.beans.IntrospectionException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private static SharedResource sharedResource = new SharedResource();
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(() -> {
                sharedResource.read();
            });
            t1.setName("Reader Thread");
            t1.start();

            Thread t2 = new Thread(() -> {
                sharedResource.write();
            });
            t2.setName("Writer Thread");
            t2.start();
        }
    }
}

class SharedResource {
    int res;
    private static ReadWriteLock rwlock = new ReentrantReadWriteLock();

     void read() {
        rwlock.readLock().lock();
        try {
            System.out.println("Reading value....." + res);
            Thread.sleep(2000);
        }catch (InterruptedException ie) {}
        finally {
            rwlock.readLock().unlock();
        }
    }

     void write() {
        rwlock.writeLock().lock();
        try {
            System.out.println("Writing value....." + (++res));
            Thread.sleep(2000);
        }catch (InterruptedException ie) {}
        finally {
            rwlock.writeLock().unlock();
        }
    }

}
