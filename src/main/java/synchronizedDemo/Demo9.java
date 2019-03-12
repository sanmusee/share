package synchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * 锁的是堆内存中的对象，而不是栈中的引用*/
public class Demo9 {

    Object o = new Object();

    public void func1() {
        synchronized (o) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "线程执行中......");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final Demo9 demo9 = new Demo9();

        new Thread(new Runnable() {
            public void run() {
                demo9.func1();
            }
        }, "Thread1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo9.o = new Object();

        new Thread(new Runnable() {
            public void run() {
                demo9.func1();
            }
        }, "Thread2").start();
    }
}

/**
 * 线程1当时执行时，因为o引用了object1，所以线程1锁住了object1，
 * 线程1执行3秒之后，主线程修改了o引用的对象，o已经引用了object2
 * 线程2执行需要获得o所引用的那个对象的锁，即object2。object2没有被任何线程锁住，所以线程2可以获得该锁，得以执行
 *
 *
 * 如果线程2在执行第12行代码之后才修改o的引用。那么线程2还是会卡住，因为线程2已经认准了自己需要获得的锁是object1了
 * */
