package concurrent;

public class UserDaemonThreads {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Daemon thread executing indefinitely");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("User thread executed");
        });

        t1.setDaemon(true);

        t1.start();
        t2.start();
    }
}
