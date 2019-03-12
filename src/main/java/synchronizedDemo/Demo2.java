package synchronizedDemo;

public class Demo2 implements Runnable {

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        Demo2 d2 = new Demo2();
        for (int i = 0; i < 5; i++) {
            new Thread(d2, "Thread" + i).start();
        }
    }
}

/**
 * 1、run方法上加了synchronized相当于5个线程串行执行，因为一个时刻只有一个线程获得锁
 * 2、synchronized关键字加在方法定义上，那么执行该方法需要获得的锁的对象是“this”，即调用方法的那个对象，此处就是d1所引用的那个堆内存中的对象
 *
 * 3、情景：不管class是否继承自Runnable，都是要用class去创建一个object的，且object只有一个，多个线程中通过调用这个object的一些方法改变object的状态。
 * 这个对象一般是计数器、同步容器等。
 * */