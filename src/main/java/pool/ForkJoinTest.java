package pool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random random = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
    }

    public static void main(String[] args) throws Exception{

        ForkJoinPool threadPool = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        threadPool.execute(task);
        long startTime = System.currentTimeMillis();
        task.join();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        System.in.read();

    }

    static class AddTask extends RecursiveAction {

        int start;
        int end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("从" + start + "到" + end + "的和为：" + sum);
            } else {
                int middle = start + (end - start)/2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }
}
