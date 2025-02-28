package callable;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<String> fs = es.submit(new CallableWorker());
        new CallableWorker().call();
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
