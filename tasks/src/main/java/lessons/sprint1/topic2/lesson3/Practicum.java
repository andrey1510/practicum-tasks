package lessons.sprint1.topic2.lesson3;

import java.util.function.Function;

class Practicum {
    public static void main(String[] args) {
        Function<String, Integer> parser = Integer::parseInt; // исправить здесь

        int x = parser.apply("3");
        int y = parser.apply("5");
        System.out.println(x + y);
    }
}
