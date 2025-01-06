package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(10);
        
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    bq.put(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Produced task " + i);
            } 
        });

        Thread consumer1 = new Thread(() -> {
            try {
                System.out.println("Consumer ONE consumed : " +bq.take());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread consumer2 = new Thread(() -> {
            try {
                System.out.println("Consumer TWO consumed : " +bq.take());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
