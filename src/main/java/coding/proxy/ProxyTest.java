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
        // 第一个和第二个是类加载器和被代理对象实现的所有接口，在生成代理对象之前，会先判断缓存中是否存在代理对象，如果存在就直接返回。
        // 判断缓存中是否存在代理对象的时候就会用到类加载器(loader)和接口(interfaces)，如果缓存中没有代理对象，就会生成一个代理对象，
        // 这个代理对象会继承Proxy类并且会实现被代理对象所实现的所有接口。
        // 这个代理类还会有一个只有InvocationHandler类的构造函数。(Proxy: final Constructor<?> cons = cl.getConstructor(constructorParams);)
        // newProxyInstance内部就是根据反射找到代理类的构造函数，然后把InvocationHandler这个类掺入代理类中，最后利用反射再次生成代理类对象，(Proxy: cons.newInstance(new Object[]{h});)
        // 这样就完成了
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
