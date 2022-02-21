package test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Auther: zls
 * @Date: 2022/2/18 16:20
 * @Description:
 */
public class SemaphoreExample {

    static class Semaphore extends AbstractQueuedSynchronizer {

        public Semaphore(int permits) {
            setState(permits);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            var available = getState();
            var left = available - 1;
            if(available == 0) {
                return -1;
            }
            if(compareAndSetState(available, left)) {
                return left;
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            var available = getState();
            return compareAndSetState(available, available + 1);
        }
    }

    public static int i = 0;

    public static void main(String[] args) {
        var semaphore = new Semaphore(3);
        for(int i = 0; i < 1000; i++) {
            new Thread(() -> {
                semaphore.acquireShared(0);
                try {
                    System.out.println("go");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.releaseShared(0);
            }).start();
        }
    }

}
