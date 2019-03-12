import java.util.concurrent.Executor;

public class MyExecutor implements Executor {
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        final A a = new A();

        class MyRunnable implements Runnable {
            public void run() {
                a.addCount();
            }
        }
        MyRunnable task = new MyRunnable();

        MyExecutor executor = new MyExecutor();
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        System.out.println(a.getCount());
    }
}

class A {
    int count = 0;
    void addCount() {
        for (int i = 0; i < 10000; i++) {
            count ++;
        }
    }
    int getCount() {
        return count;
    }
}
