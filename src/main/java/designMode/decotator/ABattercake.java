package designMode.decotator;

/**
 * @Auther: zls
 * @Date: 2022/6/9 09:27
 * @Description: 抽象的煎饼类
 */
public abstract class ABattercake {

    protected abstract String getDesc();
    protected abstract int cost();

}

/**
 * 煎饼类继承于上面的抽象煎饼类
 */
class Battercate extends ABattercake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
