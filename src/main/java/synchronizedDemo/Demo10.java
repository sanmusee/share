package synchronizedDemo;

/**
 * 不要以字符串常量作为锁的对象*/
public class Demo10 {
    String str1 = "hello";
    String str2 = "hello";

    public void func1() {
        synchronized (str1) {
            System.out.println(Thread.currentThread().getName() + "线程中执行func1方法");
            //一直抢占锁资源
            while (true){

            }
        }
    }

    public void func2() {
        synchronized (str2) {
            System.out.println(Thread.currentThread().getName() + "线程中执行func2方法");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {

        final Demo10 demo10 = new Demo10();

        new Thread(new Runnable() {
            public void run() {
                demo10.func1();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                demo10.func2();
            }
        }).start();
    }
}

