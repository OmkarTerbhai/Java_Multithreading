package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {

        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();
        ExecutorService service = Executors.newSingleThreadExecutor();
            for(int i = 1; i <= 100; i++) {
                service.execute(w1);
                service.execute(w2);
            }

        service.shutdown();
    }
}

class Worker1 implements Runnable {
    int iCnt;

    @Override
    public void run() {
        iCnt++;
        System.out.println("Worker 1 : Incremented counter : " + iCnt + " with thread " +
                Thread.currentThread().getName());
    }
}

class Worker2 implements Runnable {
    int iCnt;

    @Override
    public void run() {
        iCnt++;
        System.out.println("Worker 2 : Incremented counter : " + iCnt + " with thread " +
                Thread.currentThread().getName());
    }
}
