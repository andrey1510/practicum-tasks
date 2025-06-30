package com.kafkapractice.topic4;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Application {

    static class Order {
        private final long price;

        public Order(long price) {
            this.price = price;
        }

        public long getPrice() {
            return price;
        }
    }

    static class MaxOrderPerPartitionMessageListener implements MessageListener<String, Order> {

        private final Map<Integer, Order> stats = new HashMap<>();

        @Override
        public void onMessage(ConsumerRecord<String, Order> data) {
            int partition = data.partition();
            Order currentOrder = data.value();
            Order maxOrder = stats.get(partition);

            if (maxOrder == null || currentOrder.getPrice() > maxOrder.getPrice()) {
                stats.put(partition, currentOrder);
            }
        }

        public Optional<Order> getPartitionMax(int partition) {
            return Optional.ofNullable(stats.get(partition));
        }

    }

    public static void main(String[] args) {
        MaxOrderPerPartitionMessageListener messageListener = new MaxOrderPerPartitionMessageListener();

        messageListener.onMessage(newRecord(1, 0, 100));
        messageListener.onMessage(newRecord(2, 0, 200));
        messageListener.onMessage(newRecord(1, 1, 50));
        messageListener.onMessage(newRecord(3, 0, 150));
        messageListener.onMessage(newRecord(1, 2, 110));
        messageListener.onMessage(newRecord(2, 1, 190));
        messageListener.onMessage(newRecord(2, 2, 210));
        messageListener.onMessage(newRecord(1, 3, 100));
        messageListener.onMessage(newRecord(3, 1, 150));

        messageListener.getPartitionMax(1).ifPresent(it -> System.out.print(it.getPrice() + " "));
        messageListener.getPartitionMax(2).ifPresent(it -> System.out.print(it.getPrice() + " "));
        messageListener.getPartitionMax(3).ifPresent(it -> System.out.print(it.getPrice()));
        messageListener.getPartitionMax(4).ifPresent(it -> {
            throw new IllegalStateException();
        });
    }

    static ConsumerRecord<String, Order> newRecord(int partition, long offset, long orderPrice) {
        return new ConsumerRecord<>("orders", partition, offset, null, new Order(orderPrice));
    }

}
