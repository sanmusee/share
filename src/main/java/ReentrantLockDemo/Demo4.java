package ReentrantLockDemo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    System.out.println("线程1启动了");
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("线程2启动了");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("线程2结束了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        thread2.start();

//        Scanner scanner = new Scanner(System.in);
//        scanner.nextInt();

        thread2.interrupt();
    }
}
