package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int TOURISTS = 5;
    private static final int SPOTS = 3;

    public static CyclicBarrier barrier = new CyclicBarrier(TOURISTS, () -> {
        System.out.println("Tour Leader starts speaking....");
    });

    public static void main(String[] args) {
        for (int i = 1; i <= TOURISTS; i++) {
            new Thread(new Tourist(i, barrier)).start();
        }
    }

    static class Tourist implements Runnable{

         int id = 0;
         CyclicBarrier  barrier;

        public Tourist(int id,CyclicBarrier  barrier) {
            this.id = id;
            this.barrier = barrier;
        }
        @Override
        public void run() {
            for (int i = 1; i <= SPOTS ; i++) {
                System.out.println("Tourist " + id + " is exploring spot " + i);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ie) {}

                System.out.println("Tourist " + id + " has reached spot " + i);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
