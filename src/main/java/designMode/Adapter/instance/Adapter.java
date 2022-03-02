package designMode.adapter.instance;

import designMode.adapter.Adaptee;
import designMode.adapter.Target;

/**
 * @Auther: zls
 * @Date: 2022/3/2 13:05
 * @Description: 适配器类，直接关联被适配类，同时实现标准接口
 */
public class Adapter implements Target {

    // 直接关联被适配类
    private Adaptee adaptee;

    // 可以通过构造函数传入具体需要适配的被适配类对象
    public Adapter (Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 这里是使用委托的方式完成特殊功能
        this.adaptee.specificRequest();
    }

}
