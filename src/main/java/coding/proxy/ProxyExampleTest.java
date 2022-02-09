package coding.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ProxyExampleTest {

    @Test
    public void test_proxy() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        IOrder order = Aspect.getProxy(Order.class, "coding.proxy.TimeUsageAspect");
        order.pay();
        order.show();
    }

}
