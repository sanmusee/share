package ReentrantLockDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }
    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final Demo2 container = new Demo2();
        final Lock lock = new ReentrantLock();

        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    System.out.println("1号线程把 ：" + i + " 添加到容器中");

                    if (container.size() == 5) {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("2号线程启动");
                lock.lock();
                if (container.size() == 5) {
                    System.out.println("container容器中元素已经到达5个，线程2即将结束");
                }
                lock.unlock();
                System.out.println("2号线程结束");
            }
        }).start();
    }

}
