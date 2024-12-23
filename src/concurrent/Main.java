package concurrent;

public class Main {
    public static void main(String[] args) {
        Demo1 t1 = new Demo1();
        Demo2 t2 = new Demo2();
        t1.start();
        t2.start();
    }
}

class Demo1 extends Thread {
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

class Demo2 extends Thread {
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

