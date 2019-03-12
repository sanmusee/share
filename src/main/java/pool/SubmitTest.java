package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitTest {
    public static void main(String[] args) throws Exception {
        MyCallable task1 = new MyCallable(1, 80000);
        MyCallable task2 = new MyCallable(80001, 140000);
        MyCallable task3 = new MyCallable(140001, 170000);
        MyCallable task4 = new MyCallable(170001, 200000);

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        long startTime = System.currentTimeMillis();
        Future<List<Integer>> future1 = threadPool.submit(task1);
        Future<List<Integer>> future2 = threadPool.submit(task2);
        Future<List<Integer>> future3 = threadPool.submit(task3);
        Future<List<Integer>> future4 = threadPool.submit(task4);
        List<Integer> list1 = future1.get();
        List<Integer> list2 = future2.get();
        List<Integer> list3 = future3.get();
        List<Integer> list4 = future4.get();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

//        MyCallable task5 = new MyCallable(1, 200000);
//        long startTime2 = System.currentTimeMillis();
//        Future<List<Integer>> future5 = threadPool.submit(task5);
//        List<Integer> list5 = future5.get();
//        long endTime2 = System.currentTimeMillis();
//        System.out.println(endTime2 - startTime2);

//        for (Integer i: list1) {
//            System.out.print(" " + i + " ");
//        }
    }

}

class MyCallable implements Callable<List<Integer>> {

    int startPos;
    int endPos;

    public MyCallable(int startPos, int endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    static boolean isPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= num/2; i++) {
            if (num%i == 0) return false;
        }
        return true;
    }

    public List<Integer> call() throws Exception {
        List<Integer> list = new ArrayList();
        for (int i = startPos; i < endPos; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }
}
