package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/3/28 17:35
 * @Description:
 */
public class Depot {

    private Lock lock;
    private int size;

    public Depot() {
        this.lock = new ReentrantLock();
        this.size = 0;
    }

    public void produce(int val) {
        lock.lock();
        try {
            size += val;
            System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            size -= val;
            System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }
}