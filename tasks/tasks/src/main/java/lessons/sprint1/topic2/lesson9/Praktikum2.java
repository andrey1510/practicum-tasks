package lessons.sprint1.topic2.lesson9;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Praktikum2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 9, 19);
        LocalTime time = LocalTime.of(15, 44, 28);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(dateTime));
    }
}