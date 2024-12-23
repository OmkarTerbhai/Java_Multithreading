package concurrent;

public class JoinThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                System.out.println("T1 : " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                System.out.println("T2 : " + i);
            }
        });

        System.out.println("Before starting threads...");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("After starting threads");
    }
}
