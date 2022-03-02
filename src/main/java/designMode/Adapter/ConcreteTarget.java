package designMode.adapter;

/**
 * @Auther: zls
 * @Date: 2022/3/2 11:13
 * @Description: 具体目标类，只提供普通功能
 */
public class ConcreteTarget implements Target {

    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }

}
