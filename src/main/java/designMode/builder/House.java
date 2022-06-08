package designMode.builder;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:16
 * @Description:
 */
public class House {

    private String one;

    private String two;

    private String three;

    private String four;

    public void setOne(String one) {
        this.one = one;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public void setFour(String four) {
        this.four = four;
    }

    @Override
    public String toString() {
        return "House{" +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                '}';
    }
}
