package designMode.adapter.clzss;

import designMode.adapter.Adaptee;
import designMode.adapter.Target;

/**
 * @Auther: zls
 * @Date: 2022/3/2 11:13
 * @Description: 适配器类，继承了被适配类，同时实现标准接口
 */
public class Adapter extends Adaptee implements Target {

    public void request() {
        super.specificRequest();
    }

}
