package synchronizedDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile只保证可见性，不保证原子性。不可代替锁的作用*/
public class Demo8 {
    volatile int count = 0;
//    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void func1() {
        for (int i = 0; i < 10000; i++) {
            count++;

//            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        final Demo8 demo8 = new Demo8();

        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new Runnable() {
                public void run() {
                    demo8.func1();
                }
            }));
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(demo8.count);

//        System.out.println(demo8.atomicInteger);
    }
}


/**
 * 当并发量很大时，多个线程同时从读取，同时赋值，当还没有线程完成回写操作时，所有线程就已经完成赋值操作了。
 * （如果只是完成读取操作，那是没有影响的，由于是volatile类型变量，其中一个线程完成回写后，其他线程是会重新去读取的）
 * java的缓存一致性协议中对八种基本操作的规定中的第二条：不允许一个线程丢弃它的最近assign的操作，即变量在工作内存中改变了之后必须同步到主内存中。
 * */