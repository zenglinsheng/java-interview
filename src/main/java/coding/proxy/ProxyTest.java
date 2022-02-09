package coding.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Auther: zls
 * @Date: 2022/2/9 16:07
 * @Description:
 */
public class ProxyTest implements InvocationHandler {

    private Object object;
    private String[] methods;

    public ProxyTest(Object object, String ... methods) {
        this.object = object;
        this.methods = methods;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var methodList = Arrays.stream(this.methods).collect(Collectors.toList());

        Object result;
        if (methodList.isEmpty() || methodList.contains(method.getName())) {
            System.out.println("before...");
            result = method.invoke(object);
            System.out.println("after...");
        } else {
            result = method.invoke(object);
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        IOrder proxy = new ProxyTest(new Order(), "pay").getProxy();
        proxy.pay();
        proxy.show();

        IOrder proxy02 = new ProxyTest(new Order02(), "show").getProxy();
        proxy02.pay();
        proxy02.show();
    }

}
