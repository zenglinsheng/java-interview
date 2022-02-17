package test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zls
 * @Date: 2022/2/16 16:55
 * @Description:
 */
public class CASTest {

    Food food = new Food("apple", new AtomicInteger(10_000_000));

    AtomicInteger lock = new AtomicInteger(0);

    int i = 0;

    AtomicBoolean flag = new AtomicBoolean(false);

    int count = 50;

    @Test
    public void test() throws InterruptedException {
        var thread01 = new Thread(new A());
        var thread02 = new Thread(new A());
        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println("i = " + food.count.get());
    }

    @Test
    public void test02() throws InterruptedException {
        var startTime = System.currentTimeMillis();
        var thread01 = new Thread(new B(count));
        var thread02 = new Thread(new B(count));

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println("i = " + i);
        System.out.println("time = " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void test_02_01() throws InterruptedException {
        var startTime = System.currentTimeMillis();
        var thread01 = new Thread(new D(count));
        var thread02 = new Thread(new D(count));

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println("i = " + i);
        System.out.println("time = " + (System.currentTimeMillis() - startTime));
    }

   @Test
    public void test03() throws InterruptedException {
        var thread01 = new Thread(new C());
        var thread02 = new Thread(new C());

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println("i = " + i);
    }

    @Test
    public void test04() throws InterruptedException {
        var startTime = System.currentTimeMillis();

        var count = 20;
        final var latch = new CountDownLatch(count);
        var service = Executors.newFixedThreadPool(count);

        for (int i = 0;i < count;i ++) {
            service.submit(new E(latch, false));
        }

        latch.await();
        System.out.println("i = " + i);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    private void enter() {
        while (!lock.compareAndSet(0, 1)) {}
    }

    private void leave() {
        lock.compareAndSet(1, 0);
    }

    private void enterByFlag() {
        while (!flag.compareAndSet(false, true)) {}
    }

    private void leaveByFlag() {
        flag.compareAndSet(true, false);
    }

    class Food {
        private String name;
        private AtomicInteger count;

        public Food(String name, AtomicInteger count) {
            this.name = name;
            this.count = count;
        }
    }

    class A implements Runnable {
        @Override
        public void run() {
            for (int j = 0;j < 1000000;j ++) {
                food.count.decrementAndGet();
            }
        }
    }

    class B implements Runnable {
        private int count;

        public B(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int j = 0;j < count;j ++) {
                enter();
                i ++;
                leave();
            }
        }
    }

    class C implements Runnable {
        @Override
        public void run() {
            for (int j = 0;j < 1_000_000;j ++) {
                enterByFlag();
                i ++;
                leaveByFlag();
            }
        }
    }

    class D implements Runnable {
        private int count;

        public D(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int j = 0;j < count;j ++) {
                synchronized (D.class) {
                    i ++;
                }
            }
        }
    }

    class E implements Runnable {
        private CountDownLatch latch;
        private boolean isSync;

        public E(CountDownLatch latch, boolean isSync) {
            this.latch = latch;
            this.isSync = isSync;
        }

        @Override
        public void run() {
            if (isSync) {
                for (int j = 0;j < count;j ++) {
                    synchronized (E.class) {
                        i ++;
                    }
                }
            } else {
                for (int j = 0;j < count;j ++) {
                    enter();
                    i ++;
                    leave();
                }
            }
            latch.countDown();
        }
    }

}
