package lessons.sprint1.topic2.lesson6;

import java.util.List;

//Реализуйте оператор reduce таким образом, чтобы элементы стрима склеились в одну строку.

class Praktikum3 {
    public static void main(String[] args) {
        List<String> letters = List.of("H", "e", "l", "l", "o", ",", " ", "w", "o", "r", "l", "d");
        String result = letters.stream()
            .reduce("", (x, y) -> x+y );

        System.out.println(result);
    }
}
