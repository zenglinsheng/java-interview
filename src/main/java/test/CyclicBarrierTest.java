package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
    CyclicBarrier barrier;
    int page = 0;

    public CyclicBarrierTest(){
        barrier = new CyclicBarrier(2, () -> {
            System.out.println("sync...");
            page ++;
        });
    }

    void prepareProducts() {
        while(page < 1000) {

            try {
                System.out.println("fetch product...");
                barrier.await();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    void prepareDeliveryOrders()  {
        while (page < 1000) {
            try {
                System.out.println("fetch delivery order...");
                barrier.await();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    void run() {
        new Thread(this::prepareProducts).start();
        new Thread(this::prepareDeliveryOrders).start();
    }

    public static void main(String[] argv) {
        var test = new CyclicBarrierTest();
        test.run();
    }
}
