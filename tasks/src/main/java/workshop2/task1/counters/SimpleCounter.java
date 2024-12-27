package workshop2.task1.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter implements Counter {

    private int count = 0;

    public void increment() {
        count++;
    }

    public int getValue() {
        return count;
    }
}


