package test;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zls
 * @Date: 2022/2/16 13:56
 * @Description:
 */
public class WaitNotifyCase {

    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        lock.wait();
                        System.out.println("wait after...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    System.out.println("notify after...");
                }
            }
        }).start();
    }

}
