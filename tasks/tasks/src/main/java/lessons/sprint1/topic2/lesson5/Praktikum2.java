package lessons.sprint1.topic2.lesson5;

import java.util.Optional;

class Praktikum2 {
    public static void main(String[] args) {
        System.out.println(extractDomain("user@somemail.com")); // somemail.com
        System.out.println(extractDomain("user@yandex.ru")); // yandex.ru
        System.out.println(extractDomain("@somemail.ru")); // yandex.ru
        System.out.println(extractDomain("not email")); // yandex.ru
    }

    static String extractDomain(String email) {
        return Optional.ofNullable(email)
            .filter(e -> e.contains("@") && e.indexOf('@') >= 1)
            .map(e -> e.substring(e.indexOf('@') + 1))
            .orElse("yandex.ru");
    }
}
