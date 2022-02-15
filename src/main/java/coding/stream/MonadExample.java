package coding.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class MonadExample {

    @Test
    public void test_optional(){
        var result = Optional.of(100)
                .map(x -> x*x)
                .map(x -> x / 100);
        System.out.println(String.format("result = %s", result.get()));
    }

    @Test
    public void test_stream(){

        // Stream<String>
        var result = Stream.of("Hello", "World")
                .map(String::length);

    }

    @Test
    public void test_udef(){
        Optional<Integer> x= Optional.empty();
        var y = x.map(a -> a * a);
        System.out.println(y);
        System.out.println(y.isEmpty());
        System.out.println(y.isPresent());
    }
}
