package designMode.builder;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/7 10:44
 * @Description:
 */
public class BuilderTest {

    @Test
    public void test() {
        Course course = new Course.CourseBuilder().
                buildName("语文").
                buildPpt("语文ppt").
                buildArticle("好吃的东西").
                buildVideo("语文讲解视频").
                build();
        System.out.println(course);
    }

    @Test
    public void test02() {
        House house = new Contractor(new Worker()).buildHouse();
        System.out.println(house);
    }

}
