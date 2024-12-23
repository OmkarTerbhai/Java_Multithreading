package concurrent;

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 13; i++) {
                System.out.println("T1: " + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 13; i++) {
                System.out.println("T2: " + i);
            }
        });


        System.out.println("In Main thread");
        t1.start();
        t2.start();
        t1.setPriority(Thread.MAX_PRIORITY);
        System.out.println("End of main thread");
    }
}
