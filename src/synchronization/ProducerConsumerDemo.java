package synchronization;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Worker w1 = new Worker(5);

        Thread t1 = new Thread(() -> {
            try {
                w1.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                w1.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}

class Worker {
    private final int size;
    private final Queue<Integer> queue;
    private static final Object LOCK = new Object();
    private int counter;

    public Worker(int size) {
        queue = new LinkedList<>();
        this.size = size;
        counter = 0;
    }

    public void produce() throws InterruptedException {
        synchronized (LOCK) {
            while(true) {
                if(queue.size() == size) {
                    System.out.println("Cannot add as queue is full");
                    LOCK.wait();
                }
                else {
                    counter++;
                    System.out.println("Produced to queue : msg : " + counter);
                    queue.add(counter);
                    System.out.println("Queue ready to be consumed...");
                    LOCK.notify();
                }
                Thread.sleep(2000);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if(queue.isEmpty()) {
                    System.out.println("Queue is empty, cannot consume. Waiting for produce");
                    LOCK.wait();
                }
                else {
                    System.out.println("Consumed from queue : " + queue.remove());
                    LOCK.notify();
                }
                Thread.sleep(2000);
            }
        }
    }
}
