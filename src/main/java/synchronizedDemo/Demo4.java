package synchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法是否可以调用另一个同步方法
 * */
public class Demo4 {
    synchronized public void func1() {
        System.out.println("func1 开始执行");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        func2();
        System.out.println("func1 执行结束");
    }

    synchronized public void func2() {
        System.out.println("func2 开始执行");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("func2 执行结束");
    }

    public static void main(String[] args) {
        final Demo4 d4 = new Demo4();

        new Thread(new Runnable() {
            public void run() {
                d4.func1();
            }
        }).start();
    }
}

/**
 * 结论： 一个线程已经拥有了某个对象的锁，这个线程再次申请这把锁时会再次获得，也就说synchronized锁时可重入的，属于重入锁*/