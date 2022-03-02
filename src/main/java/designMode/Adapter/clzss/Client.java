package designMode.adapter.clzss;

import designMode.adapter.ConcreteTarget;
import designMode.adapter.Target;

/**
 * @Auther: zls
 * @Date: 2022/3/2 11:14
 * @Description: 测试类
 */
public class Client {

    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类
        Target adapter = new Adapter();
        adapter.request();
    }

}
