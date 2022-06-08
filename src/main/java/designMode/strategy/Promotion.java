package designMode.strategy;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:38
 * @Description:
 */
public interface Promotion {

    void doPromotion();

    class LiJianPromotion implements Promotion {
        @Override
        public void doPromotion() {
            System.out.println("立减促销，课程的价格直接减去配置的价格");
        }
    }

    class ManJianPromotion implements Promotion {
        @Override
        public void doPromotion() {
            System.out.println("满减促销，满200减20元");
        }
    }

    class FanXianPromotion implements Promotion {
        @Override
        public void doPromotion() {
            System.out.println("反现促销，返回的金额存放到网站用户的余额中");
        }
    }

    class EmptyPromotion implements Promotion {
        @Override
        public void doPromotion() {
            System.out.println("无促销");
        }
    }

}
