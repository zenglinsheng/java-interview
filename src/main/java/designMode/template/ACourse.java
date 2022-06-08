package designMode.template;

/**
 * @Auther: zls
 * @Date: 2022/6/7 16:24
 * @Description:
 */
public abstract class ACourse {

    /** 声明成final就是不希望子类去覆盖这个方法 */
    protected final void makeCourse() {
        this.makePPT();
        this.makeVideo();
        /** 这里是否需要写手记交由勾子方法来决定 */
        if (needWriteArticle()) {
            this.writeArticle();
        }
        this.packageCourse();
    }

    final void makePPT() {
        System.out.println("制作PPT");
    }

    final void makeVideo() {
        System.out.println("制作视频");
    }

    /** 这个编写手记是一个可选项 */
    final void writeArticle() {
        System.out.println("编写手记");
    }

    /** 我们就要给这个编写手记声明一个勾子方法 */
    /** 这个并不是一个final方法，子类是可以进行覆盖的 */
    protected boolean needWriteArticle() {
        return false;
    }

    abstract void packageCourse();

}

class DesignPatternCourse extends ACourse {
    @Override
    void packageCourse() {
        System.out.println("提供课程Java源代码");
    }

    @Override
    protected boolean needWriteArticle() {
        return true;
    }
}

class FECourse extends ACourse {

    private boolean needWriteArticleFlag = false;

    @Override
    void packageCourse() {
        System.out.println("提供课程的前端代码");
        System.out.println("提供课程的图片等多媒体素材");
    }

    @Override
    protected boolean needWriteArticle() {
        return this.needWriteArticleFlag;
    }

    public FECourse(boolean needWriteArticleFlag) {
        this.needWriteArticleFlag = needWriteArticleFlag;
    }

}
