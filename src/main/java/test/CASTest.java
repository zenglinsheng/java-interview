package test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zls
 * @Date: 2022/2/16 16:55
 * @Description:
 */
public class CASTest {

    AtomicInteger i = new AtomicInteger(0);

    @Test
    public void test() throws InterruptedException {
        var thread01 = new Thread(new A());
        var thread02 = new Thread(new A());
        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println("i = " + i);
    }

    class A implements Runnable {

        @Override
        public void run() {
            for (int j = 0;j < 1000000;j ++) {
                i.incrementAndGet();
            }
        }
    }

}
