package designMode.builder;

/**
 * @Auther: zls
 * @Date: 2022/6/7 10:34
 * @Description:
 */
public class Course {

    private String name;
    private String ppt;
    private String article;
    private String video;

    private Course(CourseBuilder courseBuilder) {
        this.name = courseBuilder.name;
        this.ppt = courseBuilder.ppt;
        this.article = courseBuilder.article;
        this.video = courseBuilder.video;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", ppt='" + ppt + '\'' +
                ", article='" + article + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    interface Builder {
        Builder buildName(String name);
        Builder buildPpt(String ppt);
        Builder buildArticle(String article);
        Builder buildVideo(String video);
        Course build();
    }

    static class CourseBuilder implements Builder {
        private String name;
        private String ppt;
        private String article;
        private String video;

        @Override
        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder buildPpt(String ppt) {
            this.ppt = ppt;
            return this;
        }

        @Override
        public Builder buildArticle(String article) {
            this.article = article;
            return this;
        }

        @Override
        public Builder buildVideo(String video) {
            this.video = video;
            return this;
        }

        @Override
        public Course build() {
            return new Course(this);
        }
    }

}
