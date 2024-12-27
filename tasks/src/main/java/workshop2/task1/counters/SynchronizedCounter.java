package workshop2.task1.counters;

public class SynchronizedCounter implements Counter {

    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }
    @Override
    public synchronized int getValue() {
        return count;
    }

}
