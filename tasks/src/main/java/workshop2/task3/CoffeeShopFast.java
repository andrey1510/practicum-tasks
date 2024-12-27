package workshop2.task3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CoffeeShopFast {
    public static void main(String[] args) throws InterruptedException {

        int orders = 10;

        OrderProducer producer = new OrderProducer();
        OrderConsumer consumer = new OrderConsumer();

        BlockingQueue<String> orderQueue = new ArrayBlockingQueue<>(10);

        Runnable producerRunnable = () -> {
            int i = 0;
            while (i < orders) {
                String order = producer.getOrder();
                try {
                    orderQueue.put(order);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        };

        Runnable consumerRunnable = () -> {
            try {
                while (true) {
                    String order = orderQueue.poll(3, TimeUnit.SECONDS);
                    if (order == null) {
                        break;
                    }
                    consumer.processOrder(order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producerRunnable);
        Thread consumerThread = new Thread(consumerRunnable);
        Thread consumerThread2 = new Thread(consumerRunnable);

        long start = System.currentTimeMillis();

        producerThread.start();
        consumerThread.start();
        consumerThread2.start();

        producerThread.join();
        consumerThread.join();
        consumerThread2.join();

        long end = System.currentTimeMillis();

        System.out.println("Время выполнения: " + (end - start) + " мс");
    }
}
