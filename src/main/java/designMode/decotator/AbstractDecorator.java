package designMode.decotator;

/**
 * @Auther: zls
 * @Date: 2022/6/9 09:30
 * @Description: 抽象的装饰者类继承抽象的实体
 */
public abstract class AbstractDecorator extends ABattercake {
    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake) {
        this.aBattercake = aBattercake;
    }

    @Override
    protected String getDesc() {
        return aBattercake.getDesc();
    }

    @Override
    protected int cost() {
        return aBattercake.cost();
    }
}

/**
 * 煎饼添加鸡蛋的装饰类继承于抽象的装饰类
 */
class EggDecorator extends AbstractDecorator {

    public EggDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + "加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost() + 1;
    }
}

/**
 * 煎饼添加香肠的装饰类，继承于抽象的装饰类
 */
class SausageDecorator extends AbstractDecorator{

    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + "加一根香肠";
    }

    @Override
    protected int cost() {
        return super.cost() + 2;
    }
}
