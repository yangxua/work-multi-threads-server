package msb.c_10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:53
 * @Description:
 */
public class ReentrantLock2 {

    private Lock lock = new ReentrantLock();

    void m1 () throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
        } finally {
            lock.unlock();
        }
    }

    void m2 () {
        try {
            lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock2 lock = new ReentrantLock2();

        new Thread(() -> {
            try {
                lock.m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(500);

        new Thread(() -> {
            lock.m2();
        }).start();
    }
}