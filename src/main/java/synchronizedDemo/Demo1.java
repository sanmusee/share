package synchronizedDemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 并发要解决的问题：创建一个对象，很多个线程都在操作这个对象，使得该对象的状态变得不可知*/
public class Demo1 implements Runnable{

    private int count = 10;
    private Object o = new Object();

    public void func1() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public void run() {
        synchronized (o) {
            this.func1();
        }
    }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        for (int i = 0; i < 15; i++) {
            new Thread(d1, "Thread" + i).start();
        }
    }
}

/**
 * synchronized(this)
 * */