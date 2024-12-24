package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadExecutor {
    public static void main(String[] args) {

        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
                service.scheduleAtFixedRate(w1, 2000, 2000, TimeUnit.MILLISECONDS);
                service.scheduleAtFixedRate(w2, 3000, 3000, TimeUnit.MILLISECONDS);


    }
}
