package designMode.builder;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:15
 * @Description:
 */
public class Worker extends Builder {

    private House house;

    public Worker() {
        house = new House();
    }

    @Override
    public void buildA() {
        house.setOne("one");
    }

    @Override
    public void buildB() {
        house.setTwo("two");
    }

    @Override
    public void buildC() {
        house.setThree("three");
    }

    @Override
    public void buildD() {
        house.setFour("four");
    }

    public House getHouse() {
        return house;
    }

}
