package synchronization;

public class DifferentLocks {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    static int counter1 = 0;
    static int counter2 = 0;
    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment2();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter1);
        System.out.println(counter2);
    }

    private static void increment1() {
        synchronized (LOCK1) {
            System.out.println("Thread : " + Thread.currentThread().getName() + " acquired lock 1");
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (LOCK2) {
            System.out.println("Thread : " + Thread.currentThread().getName() + " acquired lock 2");
            counter2++;
        }
    }
}
