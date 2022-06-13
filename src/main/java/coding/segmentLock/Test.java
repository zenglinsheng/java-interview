package coding.segmentLock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zls
 * @Date: 2022/6/13 15:27
 * @Description:
 */
public class Test {

    private static final Integer threadCount = 10;
    private static Long startTime = 0l;

    private static final Lock lock = new ReentrantLock();

    @org.junit.Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Integer sum = 0;
        List<Future> futureList = new LinkedList<>();

        startWork();
        for (int i = 0;i < threadCount; i ++) {
            Future future = executor.submit(new Worker(i));
//            Future future = executor.submit(new Worker2());
            futureList.add(future);
        }

        for (Future future: futureList) {
            Integer number = (Integer) future.get();
            sum += number;
        }
        completeWork();
        System.out.println(sum);
    }

    class Worker implements Callable {

        private Integer key;

        public Worker(Integer key) {
            this.key = key;
        }

        @Override
        public Object call() throws Exception {
            Integer number = 0;

            SegmentLock.lock(key);
            for (int i = 0;i < 100_000_000;i ++) {
                number ++;
//                TimeUnit.MILLISECONDS.sleep(1);
            }
            SegmentLock.unlock(key);

            return number;
        }
    }

    class Worker2 implements Callable {

        @Override
        public Object call() throws Exception {
            Integer number = 0;

            lock.lock();
            for (int i = 0;i < 100_000_000;i ++) {
                number ++;
//                TimeUnit.MILLISECONDS.sleep(1);
            }
            lock.unlock();

            return number;
        }
    }

    private static void startWork() {
        startTime = System.currentTimeMillis();
    }

    private static void completeWork() {
        System.out.println((System.currentTimeMillis() - startTime) + "ms");
    }

}
