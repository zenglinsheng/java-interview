package coding.test0602;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: zls
 * @Date: 2022/6/7 08:57
 * @Description:
 */
public class Proxy implements InvocationHandler {

    private Object object;

    public Proxy(Object object) {
        this.object = object;
    }

    public <T> T getProxy() {
        return (T) java.lang.reflect.Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        method.invoke(object);
        System.out.println("after...");
        return null;
    }

    interface Food {
        void eat();
    }

    static class Apple implements Food {
        @Override
        public void eat() {
            System.out.println("eat apple...");
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Food proxy = new Proxy(apple).getProxy();
        proxy.eat();
    }

}
