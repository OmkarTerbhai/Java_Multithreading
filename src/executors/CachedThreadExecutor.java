package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadExecutor {
    public static void main(String[] args) {

        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();
        ExecutorService service = Executors.newCachedThreadPool();
            for(int i = 1; i <= 100; i++) {
                service.execute(w1);
                service.execute(w2);
            }

        service.shutdown();
    }
}
