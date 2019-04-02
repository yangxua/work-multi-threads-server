package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/4/1 21:52
 * @Description:
 */
public class Test2 {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" start ta");
            ta.start();
            System.out.println(Thread.currentThread().getName()+" block");
            condition.await();
            System.out.println(Thread.currentThread().getName()+" continue");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" wakup others");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}