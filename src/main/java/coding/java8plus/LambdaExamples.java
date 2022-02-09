package coding.java8plus;

import org.junit.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaExamples {

    @Test
    public void test(){
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private <T> boolean process(T t){
        return true;
    }

//    @interface My{}
//    @ProxyTest
//    public void test1(){
//        Predicate<String> predicate = (@My var a) -> true;
//    }
}
