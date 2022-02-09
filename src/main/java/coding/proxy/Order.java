package coding.proxy;

public class Order implements IOrder{

    int state = 0;
    @Override
    public void pay() throws InterruptedException {
        System.out.println("paying...");
        Thread.sleep(50);
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.println("order status:" + this.state);
    }

}
