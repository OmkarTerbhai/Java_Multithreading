package concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new One(exchanger)).start();
        new Thread(new Two(exchanger)).start();
    }
}

class One implements Runnable {

    Exchanger<String> exchanger;

    public One(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        System.out.println("Sending value " + 10);
        try {
            String recvd = exchanger.exchange("10");

            System.out.println("Received " + recvd + " in One");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Two implements Runnable {

    Exchanger<String> exchanger;

    public Two(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
//        System.out.println("Sending value " + 20);
//        try {
//            Thread.sleep(2000);
//            String recvd = exchanger.exchange("20");
//
//            System.out.println("Received " + recvd + " in Two");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
