package lessons.sprint1.topic2.lesson9;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

class Praktikum {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 9, 17, 12, 43, 54);

        // 1. Получить текущую дату и время
        // и вывести на консоль
        LocalDate currentDate = currentDateTime.toLocalDate();
        System.out.println(currentDate);

        // 2. Подсчитать количество секунд, прошедшие с начала текущего дня
        // и вывести на консоль
        LocalDateTime startOfDay = currentDate.atStartOfDay();
        int daySeconds = (int) Duration.between(startOfDay, currentDateTime).getSeconds();
        // ваш код тут
        System.out.println(daySeconds);
    }
}