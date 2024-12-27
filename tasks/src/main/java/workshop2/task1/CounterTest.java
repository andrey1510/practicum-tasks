package workshop2.task1;

import workshop2.task1.counters.AtomicCounter;
import workshop2.task1.counters.Counter;
import workshop2.task1.counters.ReentrantLockCounter;
import workshop2.task1.counters.SimpleCounter;
import workshop2.task1.counters.SynchronizedCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CounterTest {

    public static void main(String[] args) {

        CounterType type = CounterType.ATOMIC;

        Counter counter = switch (type) {
            case SIMPLE -> new SimpleCounter();
            case ATOMIC -> new AtomicCounter();
            case REENTRANT -> new ReentrantLockCounter();
            case SYNCHRONIZED -> new SynchronizedCounter();
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);

        int expectedLikes = 100_000_000; //100 млн взято специально, чтобы долго выполнялся код и мы видели результаты
        int runnableThreads = 10;

        Runnable likeTask = () -> {
            for (int i = 0; i < expectedLikes; i++) {
                counter.increment();
            }
        };
        long start = System.currentTimeMillis();
        // Запускаем 10 потоков
        IntStream.range(0, runnableThreads).forEach(i -> executor.submit(likeTask));
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        // Выводим итоговое количество лайков
        System.out.println("Время выполнения: " + (end - start) + " мс");
        System.out.println("Итоговое количество лайков: " + counter.getValue());
        System.out.println("Ожидаемое количество лайков: " + expectedLikes * runnableThreads);

    }
}
