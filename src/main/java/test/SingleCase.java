package test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: zls
 * @Date: 2022/2/22 09:58
 * @Description: 单例模式
 */
public class SingleCase {

//    private static volatile SingleCase singleCase;
//
//    private SingleCase() {}
//
//    public static SingleCase getSingleCase() {
//        // 性能优化：多次读取volatile变量singleCase，指令不能重排，读取也会慢一些。可以考虑增加本地引用localSinleCase
//        SingleCase localSinleCase = singleCase;
//        if (singleCase == null) {
//            synchronized (SingleCase.class) {
//                localSinleCase = singleCase;
//                if (localSinleCase == null) {
//                    singleCase = localSinleCase = new SingleCase();
//                }
//            }
//        }
//        return singleCase;
//    }

    private static AtomicReference<SingleCase> reference = new AtomicReference<>();

    private SingleCase() {}

    public static SingleCase getSingleCase() {
        SingleCase singleCase = reference.getAcquire();
        if (singleCase == null) {
            synchronized (SingleCase.class) {
                singleCase = reference.getAcquire();
                if (singleCase == null) {
                    singleCase = new SingleCase();
                    reference.setRelease(singleCase);
                }
            }
        }
        return singleCase;
    }

}
