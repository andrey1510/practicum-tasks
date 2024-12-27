package lessons.sprint1.topic2.lesson3;

import java.util.Collection;
import java.util.List;

class Praktikum2 {

    public static void main(String[] args) {
        printAllItems(List.of(1, 2, 3, 4, 5));
    }

    static <T> void printAllItems(Collection<T> items) {
        items.forEach(System.out::println); // ваш код тут
    }
}
