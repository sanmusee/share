package synchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法中发生异常
 * 接口类型的引用引用接口实现类的对象*/
public class Demo6 {
    int count = 0;
    synchronized void func1() {
        System.out.println(Thread.currentThread().getName() + "中func1开始执行");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "  count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
//                int i = 1/0;    //此处将发生异常，锁资源被自动释放
            }
        }
    }

    public static void main(String[] args) {
        //创建一个会被多个线程操作的对象
        final Demo6 demo6 = new Demo6();

        Runnable task = new Runnable() {
            public void run() {
                demo6.func1();
            }
        };

        new Thread(task, "Thread1").start();

        //主线程休眠3秒再开启第二个线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(task, "Thread2").start();

    }
}

/**
 * 如果线程1中没有异常，就不会释放锁资源，线程2就永远无法进入临界区。*/