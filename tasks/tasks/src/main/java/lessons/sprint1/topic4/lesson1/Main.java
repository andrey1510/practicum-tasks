package lessons.sprint1.topic4.lesson1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        var start = LocalDateTime.now();

// Пул для используемых потоков
        try (var executor = Executors.newCachedThreadPool()) {    // Заменить на Executors.newVirtualThreadPerTaskExecutor()
            var random = new Random();
            for (int i = 0; i < 100_000; i++) {
                int taskId = i;
                executor.submit(() -> {
                    // Имитация сетевого запроса с задержкой
                    long delay = 500 + random.nextInt(1500);  // от 500 до 2000 мс
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Задача #" + taskId + " прервана.");
                        return;
                    }
                    System.out.printf("Задача #%d завершена за %d мс%n", taskId, delay);
                });
            }
        }

        System.out.println("Все задачи выполнены за " + start.until(LocalDateTime.now(), ChronoUnit.SECONDS));
    }

}
