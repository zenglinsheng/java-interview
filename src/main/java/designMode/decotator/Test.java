package designMode.decotator;

/**
 * @Auther: zls
 * @Date: 2022/6/9 09:31
 * @Description:
 */
public class Test {

    public static void main(String[]args){
        ABattercake battercake = new Battercate();
        battercake = new EggDecorator(battercake);
        battercake = new EggDecorator(battercake);
        battercake = new SausageDecorator(battercake);
        System.out.println(battercake.getDesc() + "价格为:" + battercake.cost());
    }

}
