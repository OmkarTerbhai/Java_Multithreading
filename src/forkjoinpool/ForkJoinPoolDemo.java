package forkjoinpool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {
    public static void main(String[] args) {
        int[] arr = new int[100];
        final int CORE_COUNT = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10);
        }

        ForkJoinPool pool =  new ForkJoinPool(CORE_COUNT);
        int target = new Random().nextInt(10);
        int idx = pool.invoke(new BinarySearchOnSteroids(0, arr.length-1, arr, target));
        System.out.println(Arrays.toString(arr));
        System.out.println(target + " found at " + idx);


    }
}

class BinarySearchOnSteroids extends RecursiveTask<Integer> {

    int start;
    int end;
    int[] arr;
    int target;

    public BinarySearchOnSteroids(int start, int end, int[] arr, int target) {
        this.arr = arr;
        this.target = target;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int mid = start + (end - start) / 2;

        if(start > end) return -1;
        if(arr[mid] == target) {
            return mid;
        }
        else if(arr[mid] > target) {
            BinarySearchOnSteroids bs = new BinarySearchOnSteroids(start, mid - 1, arr, target);
            bs.fork();
            return bs.join();
        }
        else {
            BinarySearchOnSteroids bs = new BinarySearchOnSteroids(mid, end, arr, target);
            bs.fork();
            return bs.join();
        }
    }
}
