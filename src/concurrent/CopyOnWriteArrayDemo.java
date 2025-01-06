package concurrent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        List<Integer> li = new CopyOnWriteArrayList<>(List.of(1, 2, 3, 5));
        new Thread(new Writer3(li)).start();
        new Thread(new Writer2(li)).start();

        new Thread(new Reader(li)).start();
    }
}

class Reader implements Runnable {

    List<Integer> li;

    public Reader(List<Integer> li) {
        this.li = li;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(li);
    }
}

class Writer implements Runnable {

    List<Integer> li;

    public Writer(List<Integer> li) {
        this.li = li;
    }
    @Override
    public void run() {
        System.out.println("Here to randomly write on array idx 0");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        li.set(new Random().nextInt(li.size()), new Random().nextInt(100));
    }
}

class Writer2 implements Runnable {

    List<Integer> li;

    public Writer2(List<Integer> li) {
        this.li = li;
    }
    @Override
    public void run() {
        try {
            System.out.println("Here to write on array idx 0 if condition is met");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(li.get(0) == 0) {
            li.set(0,100);
        }
    }
}

class Writer3 implements Runnable {

    List<Integer> li;

    public Writer3(List<Integer> li) {
        this.li = li;
    }
    @Override
    public void run() {
        System.out.println("Here to write on array idx 0");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        li.set(0, 200);
    }
}


