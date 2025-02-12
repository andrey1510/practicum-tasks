package lessons.sprint1.topic2.lesson6;

import java.util.Comparator;
import java.util.stream.Stream;

//Распечатайте на консоль уникальные числа в порядке убывания.

class Praktikum2 {
    public static void main(String[] args) {
        Stream.of(1, 1, 8, 5, 2, 9, 7, 7, 2, 9, 8, 9, 8, 6, 9)
            // ваш код тут
            .distinct()
            .sorted(Comparator.reverseOrder())
            .forEach(it -> System.out.print(it + " "));
    }
}