package workshop1.task1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

// Задание на новые методы для листов.

public class LogJournal<T> {

    private final LinkedList<T> entries = new LinkedList<>();

    void logEntry(T entry) {
        entries.addLast(entry);
    }

    List<T> getLastEntries(int limit) {
        return entries.reversed().stream().limit(limit).toList();
    }

    public static void main(String[] args) {
        var journal = new LogJournal<String>();
        // Добавляем по очереди числа 1..99 а журнал

        IntStream.range(1, 100).boxed().map(Object::toString).forEach(journal::logEntry);
        // Последние десять записей должны быть - 90..99
        var expectedLastTenEntries = IntStream.range(90, 100).boxed().map(Object::toString).toList().reversed();

        // Получаем фактические 10 последних записей
        var actualLastTenEntries = journal.getLastEntries(10);

        // Проверяем соответствие последниъ 10 записей ожидаемым
        if (!actualLastTenEntries.equals(expectedLastTenEntries)) {
            throw new RuntimeException("Ой, записи не совпали. Ожидаемые - [90..99]. Фактические - " + journal.getLastEntries(10));
        } else System.out.println("Ура-ура-ура");
    }
}