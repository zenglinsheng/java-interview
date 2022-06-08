package designMode.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:46
 * @Description:
 */
public class PromotionStrategyFactory {

    private PromotionStrategyFactory() {}

    private static Map<String, Promotion> PROMOTION_STRATEGY_MAP = new HashMap<>();

    private static final Promotion NON_PROMOTION = new Promotion.EmptyPromotion();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.LIJIAN, new Promotion.LiJianPromotion());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.FANXIAN, new Promotion.FanXianPromotion());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.MANJIAN, new Promotion.ManJianPromotion());
    }

    public static Promotion getPromotion(String promotionKey) {
        Promotion promotion = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotion == null ? NON_PROMOTION : promotion;
    }

    interface PromotionKey{
        String LIJIAN = "LIJIAN";
        String FANXIAN = "FANXIAN";
        String MANJIAN = "MANJIAN";
    }

}
