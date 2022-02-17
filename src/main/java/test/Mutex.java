package test;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

    private int count = 50_000_000;
    private int sum = 0;

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(0);
    }

    public void unlock() {
        sync.release(0);
    }

   @Test
   public void test() throws InterruptedException {
        var startTime = System.currentTimeMillis();

        var latch = new CountDownLatch(10);
        var pool = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 10;i ++) {
           pool.submit(new Worker(latch, false));
        }

        latch.await();
        System.out.println("sum = " + sum);
        System.out.println("time = " + (System.currentTimeMillis() - startTime));
   }


    static class Sync extends AbstractQueuedSynchronizer {
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }
        protected boolean tryRelease(int arg) {
            return compareAndSetState(1, 0);
        }
    }

    class Worker implements Runnable {
        private CountDownLatch latch;
        private boolean isSync;

        public Worker(CountDownLatch latch, boolean isSync) {
            this.latch = latch;
            this.isSync = isSync;
        }

        @Override
        public void run() {
            if (isSync) {
                for (int i = 0;i < count;i ++) {
                    synchronized (Worker.class) {
                        sum ++;
                    }
                }
            } else {
                for (int i = 0;i < count;i ++) {
                    lock();
                    sum ++;
                    unlock();
                }
            }
            latch.countDown();
        }
    }

}
