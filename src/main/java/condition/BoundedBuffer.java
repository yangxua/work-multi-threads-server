package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 15:47
 * @Description:
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr,takeptr,count;

    public void put(Object o) throws Exception {
        lock.lock();
        try {
            while(count == items.length) {
                notFull.await();
            }
            items[putptr] = o;
            if (++putptr == items.length) {
                putptr = 0;
            }
            count++;
            notEmpty.signal();
            System.out.println(Thread.currentThread().getName() + " put  "+ (Integer)o);
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws Exception {
        lock.lock();
        try {
            while(count == 0) {
                notEmpty.await();
            }
            Object o = items[takeptr];
            if(++takeptr == count) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            System.out.println(Thread.currentThread().getName() + " take "+ o);
            return o;
        } finally {
            lock.unlock();
        }
    }
}