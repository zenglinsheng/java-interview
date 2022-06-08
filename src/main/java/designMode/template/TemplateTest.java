package designMode.template;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/7 16:26
 * @Description:
 */
public class TemplateTest {

    @Test
    public void test() {
        System.out.println("后端设计模式课程start--");
        ACourse designPatternCourse = new DesignPatternCourse();
        designPatternCourse.makeCourse();
        System.out.println("后端设计模式课程end--");

        System.out.println("前端式课程start--");
        ACourse feCourse = new FECourse(true);
        feCourse.makeCourse();
        System.out.println("前端课程end--");
    }

}
