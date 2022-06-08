package designMode.strategy;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:41
 * @Description:
 */
public class PromotionActivity {

    private Promotion promotion;

    public PromotionActivity(Promotion promotion) {
        this.promotion = promotion;
    }

    public void executePromotion() {
        promotion.doPromotion();
    }

}
