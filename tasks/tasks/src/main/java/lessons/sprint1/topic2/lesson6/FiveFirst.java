package lessons.sprint1.topic2.lesson6;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FiveFirst {
    public static void main(String[] args) {
        String result = Stream.iterate(0, x -> x * 3 + 1)
            .limit(5)
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
