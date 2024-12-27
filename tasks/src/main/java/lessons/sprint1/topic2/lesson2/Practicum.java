package lessons.sprint1.topic2.lesson2;

class Practicum {
    interface IntPredicate {
        boolean test(int value);
    }

    public static void main(String[] args) {
        IntPredicate isEven = x -> x%2 == 0; // ваш код тут

        System.out.println(isEven.test(0)); // true
        System.out.println(isEven.test(2)); // true
        System.out.println(isEven.test(3)); // false
    }
}
