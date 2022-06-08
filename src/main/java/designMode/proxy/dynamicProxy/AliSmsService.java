package designMode.proxy.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: zls
 * @Date: 2022/6/8 17:03
 * @Description:
 */
public class AliSmsService {

    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

}

class CglibProxyFactory implements MethodInterceptor {

    /**
     * @param o           代理对象（增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object object = methodProxy.invokeSuper(o, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return object;
    }

    public Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(this);
        // 创建代理类
        return enhancer.create();
    }

}


class Test {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) new CglibProxyFactory().getProxy(AliSmsService.class);
        aliSmsService.send("java");
    }
}
