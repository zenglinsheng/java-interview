package coding.stream;

import java.util.Optional;
import java.util.stream.Stream;

public class Event<T> {

    T data;

    public Event(T data) {
        this.data = data;
    }

    static class EventData {

        Integer id;
        String msg;

        public EventData(Integer id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "EventData{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    static class Transforms {
        static EventData transform(Integer id) {
            switch(id) {
                case 0:
                    return new EventData(id, "Start");
                case 1:
                    return new EventData(id, "Running");
                case 2:
                    return new EventData(id, "Done");
                case 3:
                    return new EventData(id, "Fail");
                default:
                    return new EventData(id, "Error");
            }
        }
    }

    static class Transforms2 {
        static String transform(Integer id) {
            switch(id) {
                case 0:
                    return "test_chinese";
                case 1:
                    return "111";
                case 2:
                    return "222";
                default:
                    return "error";
            }
        }
    }

    @FunctionalInterface
    interface FN<A, B> {
        B apply(A a);
    }

    <B> Event<?> map(FN<T, B> f) {
        return new Event<>(f.apply(this.data));
    }

    public static void main(String[] args) {
        Stream<Event<Integer>> s = Stream.of(
                new Event<>(1),
                new Event<>(2),
                new Event<>(0),
                new Event<>(10)
        );

//        s.map(event -> event.map(Transforms::transform))
//                .forEach(e ->
//                    System.out.println(e.data)
//                );

        Stream<? extends Event<?>> eventStream = s.map(event -> event.map(Transforms::transform));
        eventStream.forEach(e -> System.out.println(e.data));

        Optional<Event<Integer>> o = Optional.of(new Event<>(1));
        Optional<? extends Event<?>> event1 = o.map(event -> event.map(Transforms::transform));
        System.out.println(event1.get().data);

        Optional<Event<Integer>> o2 = Optional.of(new Event<>(1));
        Optional<? extends Event<?>> event2 = o2.map(event -> event.map(e -> Transforms2.transform(e)));
        System.out.println(event2.get().data);

        Event<Integer> e = new Event<>(1);
        Event<?> eventData = e.map(Transforms::transform);
        System.out.println(eventData.data);
    }
}