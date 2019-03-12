package synchronizedDemo;

import java.util.concurrent.TimeUnit;
/**
 * 重入锁的另一个模型*/
public class Demo5 {
    synchronized void parent() {
        System.out.println(Thread.currentThread().getName() + "正在调用parent方法");
        try {
            //任意一个线程进来之后会对锁资源抢占5秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束调用parent方法");
    }

    public static void main(String[] args) {
        new SubDemo5().parent();
    }

}

class SubDemo5 extends Demo5 {
    @Override
    synchronized void parent() {
        System.out.println(Thread.currentThread().getName() + "正在调用子类中重写的parent方法");
        super.parent();
        System.out.println(Thread.currentThread().getName() + "结束调用子类中重写的parent方法");
    }
}

/**
 * 子类中的parent方法执行需要获得子类对象的锁
 * 在其中调用了父类的parent方法，需要获得父类对象的锁
 * 即：执行26行时需要获得子类对象的锁，当执行到28行时又要申请父类对象的锁
 * 创建子类对象的时候会先创建父类对象，且这个父类对象是匿名的，不会被其他线程锁住
 * 因此，这是重入锁的另一个模型
 * */