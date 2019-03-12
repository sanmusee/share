package synchronizedDemo;

/**
 * volatile*/
public class Demo7 {
    boolean running = true;

    public void func1() {
        boolean tmp = running;
        System.out.println(Thread.currentThread().getName() + "线程中func1 开始执行");
        int i = 0;
        while (running) {
            i++;
            System.out.println(Thread.currentThread().getName() + "线程执行中...... " + i);
        }
        System.out.println(Thread.currentThread().getName() + "线程中func1 结束执行");
    }

    public static void main(String[] args) {
        final Demo7 demo7 = new Demo7();

        new Thread(new Runnable() {
            public void run() {
                demo7.func1();
            }
        }).start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        demo7.running = false;
        System.out.println("已经在" + Thread.currentThread().getName() + "线程中把running的值置为： " + demo7.running);
    }
}
