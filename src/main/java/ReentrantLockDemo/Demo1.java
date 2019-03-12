package ReentrantLockDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 场景：线程1往容器中添加10个元素；线程2监控容器中元素的个数，当容器中达到5个元素时，线程2给出提示并结束*/
public class Demo1 {

    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final Demo1 container = new Demo1();
        final Object lock = new Object();

        //先启动一个Thread2线程，
        new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    System.out.println("2号线程启动");
                    if (container.size() != 5) {
                        try {
                            lock.wait();    //wait()的调用会释放锁资源
                            System.out.println("容器中元素已经达到5个，线程2即将结束");
//                            lock.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("2号线程结束");
                }
            }
        }, "Thread2").start();

        //3秒之后开启1号线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                System.out.println("1号线程启动");
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        container.add(new Object());
                        System.out.println("1号线程把 ：" + i + " 添加到容器中");

                        if (container.size() == 5) {
                            lock.notify();
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread1").start();

    }

}
