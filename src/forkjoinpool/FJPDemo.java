package forkjoinpool;

import java.io.Writer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FJPDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        fjp.invoke(new Worker(80));
    }
}

class Worker extends RecursiveAction {

    int workLoad;

    public Worker(int w) {
        this.workLoad = w;
    }
    @Override
    protected void compute() {
        if(workLoad > 20) {
            System.out.println("Workload too big! Splitting....");
            System.out.println("Total WL:- " + workLoad);
            int firstSplit = workLoad / 2;
            int secondSplit = workLoad - firstSplit;

            Worker split1 = new Worker(firstSplit);
            Worker split2 = new Worker(secondSplit);

            split1.fork();
            split2.fork();
        }
        else {
            System.out.println(" WL:- " + workLoad);
            System.out.println("Work Load within limits... Working....");
        }
    }
}

