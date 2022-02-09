package coding.java8plus;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class TryWithResource {

    @Test
    public void test() throws FileNotFoundException, IOException {
        var fin = new FileInputStream("somefile");
        try(fin) { // AutoClose
            fin.read();
        }


    }
}
