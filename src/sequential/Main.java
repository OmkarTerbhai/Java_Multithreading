package sequential;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Demo1());
        Thread t2 = new Thread(new Demo2());

        t1.start();
        t2.start();
    }
}

class Demo1 implements Runnable {
    public void print() {
        for (int i = 0; i < 13; i++) {
            System.out.println("Demo 1 : val = " + i);
        }
    }

    @Override
    public void run() {
        print();
    }
}

class Demo2 implements Runnable {
    public void print() {
        for (int i = 0; i < 13; i++) {
            System.out.println("Demo 2 : val = " + i);
        }
    }

    @Override
    public void run() {
        print();
    }
}
