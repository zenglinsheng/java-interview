package designMode.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: zls
 * @Date: 2022/6/8 16:42
 * @Description:
 */
public class JdkProxyFactory  implements InvocationHandler {

    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }
}


interface SmsService {
    String send(String message);
}

class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

class Main {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) new JdkProxyFactory(new SmsServiceImpl()).getProxy();
        smsService.send("java");
    }
}

