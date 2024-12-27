package workshop2.task1.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {

    private final AtomicInteger count = new AtomicInteger(0);
    @Override
    public void increment() {
        count.incrementAndGet();
    }
    @Override
    public int getValue() {
        return count.get();
    }

}
