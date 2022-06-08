package designMode.builder;

/**
 * @Auther: zls
 * @Date: 2022/6/7 13:20
 * @Description:
 */
public class Contractor {

    private Worker worker;

    public Contractor(Worker worker) {
        this.worker = worker;
    }

    public House buildHouse() {
        worker.buildA();
        worker.buildB();
        worker.buildC();
        worker.buildD();
        return worker.getHouse();
    }

}
