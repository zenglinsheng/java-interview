package test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedKeyWords {

    public static void main(String[] argv) {
        var lock = new ReentrantLock();

        var t = new Thread(() -> {
            try {
                lock.tryLock(1000, TimeUnit.MICROSECONDS);
                Thread.sleep(2000);
                System.out.println("lock...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finished...");
            }
        });
        t.start();
        t.interrupt();
    }

    @Test
    public void test() throws InterruptedException {
        var lock = new ReentrantLock();

        var t = new Thread(() -> {
            int k = 0;
            try {
                lock.lockInterruptibly();
                for(int i = 0; i < 1000000000; i ++) {
                    k += 1;
                }
                System.out.println("finished:" + k);
            } catch (InterruptedException e) {
                System.out.println("intterrupt:" + k);
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t.start();
        t.interrupt();
        t.join();
    }

}
