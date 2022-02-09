package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LargePage {

    public static void main(String[] argv) throws InterruptedException {
        var array = new ArrayList<>(100000);

        synchronized (array){
            array.wait();
        }

    }
}
