package callable;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<String> fs = es.submit(new CallableWorker());

        System.out.println(fs.get(1000, TimeUnit.MILLISECONDS));
        System.out.println("Done");
    }
}

class CallableWorker implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Hello";
    }
}
