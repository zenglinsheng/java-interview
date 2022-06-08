package designMode.strategy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:41
 * @Description:
 */
public class StrategyTest {

    @Test
    public void Test() {
        /** 在618的时候，我们使用立减的策略 */
        PromotionActivity promotionActivity618 = new PromotionActivity(new Promotion.LiJianPromotion());
        /** 在618的时候，我们使用返现的策略 */
        PromotionActivity promotionActivity1111 = new PromotionActivity(new Promotion.FanXianPromotion());

        promotionActivity618.executePromotion();
        promotionActivity1111.executePromotion();
    }

    @Test
    public void Test02() {
        PromotionActivity promotionActivity = null;

        /** 我们来创建一个promotionKey */
        String promotionKey = "LIJIAN";

        if (StringUtils.equals(promotionKey, "LIJIAN")) {
            promotionActivity = new PromotionActivity(new Promotion.LiJianPromotion());
        } else if (StringUtils.equals(promotionKey, "FANXIAN")) {
            promotionActivity = new PromotionActivity(new Promotion.FanXianPromotion());
        }//......
        promotionActivity.executePromotion();
    }

    @Test
    public void test03() {
        /** 我们来创建一个promotionKey */
        String promotionKey = PromotionStrategyFactory.PromotionKey.FANXIAN;

        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotion(promotionKey));
        promotionActivity.executePromotion();
    }

    /**
     * 暴力破解私有构造函数
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test04() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("designMode.strategy.PromotionStrategyFactory");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        PromotionStrategyFactory instance = (PromotionStrategyFactory) constructor.newInstance();
        System.out.println(instance);
    }

}
