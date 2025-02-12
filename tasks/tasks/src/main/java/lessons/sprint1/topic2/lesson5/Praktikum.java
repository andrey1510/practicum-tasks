package lessons.sprint1.topic2.lesson5;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

class Praktikum {
    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3, 4, 5);

        findFirstElement(ints, x -> x % 2 == 0).ifPresent(System.out::println);
        findFirstElement(ints, x -> x > 10).ifPresent(System.out::println);
    }

    static Optional<Integer> findFirstElement(List<Integer> values, Predicate<Integer> tester) {
        // ваш код тут
        return values.stream().filter(tester).findFirst();
    }
}
