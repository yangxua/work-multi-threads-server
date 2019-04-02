package reentrantlock2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/3/29 17:55
 * @Description:
 */
public class Depot {

    private int size;
    private Lock lock;
    private int capacity;
    private Condition emptyCondtion;
    private Condition fullConditon;

    public Depot(int capacity) {
        this.size = 0;
        this.lock = new ReentrantLock();
        this.capacity = capacity;
        this.emptyCondtion = lock.newCondition();
        this.fullConditon = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                while (size >= capacity) {
                    fullConditon.await();
                }

                int inc = (size + left) > capacity ? capacity - size : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, inc, size);
                emptyCondtion.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                while (size <= 0) {
                    emptyCondtion.await();
                }
                int dec = (size - left) <= 0 ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
                fullConditon.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}