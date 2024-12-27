package lessons.sprint1.topic2.lesson6;

import java.util.stream.IntStream;

class Praktikum {
    public static void main(String[] args) {
        IntStream.rangeClosed(0, 100)
            // ваш код тут
            .filter(e -> e%2==0 && e>30 && e%7==0)
            .limit(5)
            .forEach(it -> System.out.println(it + " "));
    }
}
