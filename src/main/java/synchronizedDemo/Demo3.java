package synchronizedDemo;

/**
 * 同步方法、非同步方法是否可以同时调用？
 * 匿名局部类、匿名对象
 * 具名局部类、具名对象
 * */
public class Demo3 {
    //定义同步方法
    public synchronized void func1() {
        System.out.println(Thread.currentThread().getName() + "  func1 开始执行");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "  func1 结束执行");
    }

    //定义非同步方法
    public void func2() {
        System.out.println(Thread.currentThread().getName() + "  func2 开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "  func2 结束执行");
    }


    public static void main(String[] args) {
        final Demo3 d3 = new Demo3();
        new Thread(new Runnable() {
            public void run() {
                d3.func1();
            }
        }, "Thread1").start();



        //定义局部类
        class MyRunnable implements Runnable {
            public void run() {
                d3.func2();
            }
        }
        Thread thread2 = new Thread(new MyRunnable(), "Thread2");
        thread2.start();
    }
}


/**
 * 线程1先启动，在线程1中调用func1，线程1把this（即d3引用的那个对象）锁住。
 * 线程2启动执行func2，但是func2不需要获得锁就能执行。就像线程1把问给锁起来，不让别人进。但是线程2只是在门外工作而已
 * */