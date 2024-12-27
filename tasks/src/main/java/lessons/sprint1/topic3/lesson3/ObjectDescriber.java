package lessons.sprint1.topic3.lesson3;

import java.util.Objects;

public class ObjectDescriber {

    public static void describeObject(Object obj) {
        switch (obj) {
            case String str ->
                System.out.println("String, длина: " + str.length());
            case Integer integer ->
                System.out.println("Integer, удвоенное значение: " + (integer * 2));
            case Double doubleValue ->
                System.out.println("Double, округленное значение: " + Math.round(doubleValue));
            case null, default ->
                System.out.println("Неизвестный тип: " + Objects.requireNonNull(obj).getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        describeObject("Строка");
        describeObject(42);
        describeObject(3.14);
        describeObject(true);
    }
}
