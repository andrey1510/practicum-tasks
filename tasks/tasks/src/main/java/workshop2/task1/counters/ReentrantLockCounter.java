package workshop2.task1.counters;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {

    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        return count;
    }

}
