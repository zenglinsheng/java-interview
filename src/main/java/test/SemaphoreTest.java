package test;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @Auther: zls
 * @Date: 2022/2/21 13:11
 * @Description:
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce(semaphore);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce(semaphore);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        int permits = semaphore.availablePermits();
        System.out.println("permits = " + permits);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume(semaphore);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume(semaphore);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void produce(Semaphore semaphore) throws InterruptedException {
        System.out.println("生产...");
        semaphore.release();
    }

    static void consume(Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        System.out.println("消费...");
    }

    @Test
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        semaphore.acquire();
        System.out.println("semaphore...");
        semaphore.release();
    }

}
