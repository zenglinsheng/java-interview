package designMode.adapter.instance;

import designMode.adapter.Adaptee;
import designMode.adapter.ConcreteTarget;
import designMode.adapter.Target;

/**
 * @Auther: zls
 * @Date: 2022/3/2 13:06
 * @Description: 测试类
 */
public class Client {

    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类，
        // 需要先创建一个被适配类的对象作为参数
        Target adapter = new Adapter(new Adaptee());
        adapter.request();
    }

}
