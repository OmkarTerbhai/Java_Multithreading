package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExecutor {
    public static void main(String[] args) {

        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores : " + cores);
        ExecutorService service = Executors.newFixedThreadPool(cores);
            for(int i = 1; i <= 100; i++) {
                service.execute(w1);
                service.execute(w2);
            }

        service.shutdown();
    }
}

