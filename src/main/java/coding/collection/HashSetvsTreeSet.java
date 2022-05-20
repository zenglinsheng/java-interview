package coding.collection;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashSetvsTreeSet  {

    @Test
    public void test_order() {
        var hashSet = new HashSet<Integer>();
        hashSet.add(3);
        hashSet.add(7);
        hashSet.add(2);
        hashSet.add(81);

        System.out.println(hashSet.stream().map(x -> x.toString()).collect(Collectors.joining(",")));

        var treeSet = new TreeSet<Integer>() {
            {
                add(3);
                add(7);
                add(2);
                add(81);
            }
        };

        System.out.println(treeSet.stream().map(x -> x.toString()).collect(Collectors.joining(",")));

        Integer higher = treeSet.higher(3);
        System.out.println(String.format("higher = %s", higher));
        Integer ceiling = treeSet.ceiling(3);
        System.out.println(String.format("ceiling = %s", ceiling));
    }

    @Test
    public void test_benchmark() {

        var random = new Random();
        LinkedList<String> words = new LinkedList<>();
        for(int i = 0; i < 1_000_000; i ++) {
            var word = random.ints(97, 123)
                    .limit(12)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            words.add(word);
        }

        var hashSet = new HashSet<String>();
        var treeSet = new TreeSet<String>();

        var start = System.currentTimeMillis();
        for(var w : words) {
            hashSet.add(w);
        }
        for(var w : words) {
            hashSet.contains(w);
        }
        System.out.println("hashSet time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for(var w : words) {
            treeSet.add(w);
        }
        for(var w : words) {
            treeSet.contains(w);
        }
        System.out.println("treeSet time:" + (System.currentTimeMillis() - start));

    }

}
