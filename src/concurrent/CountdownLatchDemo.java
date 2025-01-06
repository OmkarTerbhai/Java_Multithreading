package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        int coreCnt = 3;

        System.out.println(coreCnt);
        CountDownLatch latch = new CountDownLatch(coreCnt);

        Thread t1 = new Thread(new ThreadWorker(1, "ONE", latch));
        Thread t2 = new Thread(new ThreadWorker(2, "TWO", latch));
        Thread t3 = new Thread(new ThreadWorker(3, "THREE", latch));

        t1.start();
        t2.start();
        t3.start();

        latch.await();
    }
}


class ThreadWorker implements Runnable {
    int id;
    String name;
    CountDownLatch latch;

    public ThreadWorker(int id, String name, CountDownLatch latch) {
        this.id = id;
        this.name = name;
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Thread " + this.id + " named " + this.name + " is working");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + this.id + " named " + this.name + " has finished working");
        latch.countDown();
    }
}
