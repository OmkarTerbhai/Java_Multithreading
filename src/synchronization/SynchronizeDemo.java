package synchronization;

public class SynchronizeDemo {
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }

    private synchronized static void increment() {
        counter++;
    }
}
