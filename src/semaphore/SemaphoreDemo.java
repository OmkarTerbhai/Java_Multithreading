package semaphore;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore sm = new Semaphore(3);
        int[] arr = new int[10];
        Arrays.fill(arr, -1);
        Thread readerThread = new Thread(new Reader(arr, sm));
        Thread writerThread = new Thread(new Writer(arr, sm));

        readerThread.setName("Reader Thread");
        writerThread.setName("Writer Thread");

        readerThread.start();
        writerThread.start();

        readerThread.join();
        writerThread.join();

    }
}

class Reader implements Runnable {

    int[] arr;
    Semaphore sm;

    public Reader(int[] arr, Semaphore sm) {
        this.arr = arr;
        this.sm = sm;
    }
    @Override
    public void run() {
        try {

            sm.acquire();
            System.out.println(Thread.currentThread().getName() + " Acquired sm thread" +
                    " with " + sm.availablePermits() + " available threads");
            System.out.println("Reading arr.....");
            Thread.sleep(2000);
            System.out.println(Arrays.toString(arr));
        }
        catch (InterruptedException ie) {

        }
        finally {
            sm.release();
            System.out.println(Thread.currentThread().getName() + " released sm thread" +
                    " with " + sm.availablePermits() + " available threads");
        }
    }
}

class Writer implements Runnable {

    int[] arr;
    Semaphore sm;

    public Writer(int[] arr, Semaphore sm) {
        this.arr = arr;
        this.sm = sm;
    }
    @Override
    public void run() {
        try {
            sm.acquire();
            System.out.println(Thread.currentThread().getName() + " Acquired sm thread" +
                    " with " + sm.availablePermits() + " available threads");
            System.out.println("Writing arr.....");
            Thread.sleep(2000);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Random(234).nextInt() % arr.length;
            }
        }
        catch (InterruptedException ie) {

        }
        finally {
            sm.release();
            System.out.println(Thread.currentThread().getName() + " released sm thread" +
                    " with " + sm.availablePermits() + " available threads");
        }
    }
}
