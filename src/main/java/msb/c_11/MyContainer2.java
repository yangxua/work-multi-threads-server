package msb.c_11;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 11:26
 * @Description:
 */
public class MyContainer2<String> {

    private LinkedList<String> list = new LinkedList<>();
    private int count = 0;
    private int max = 10;
    private Lock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    public void put(String s) throws InterruptedException {
        lock.lock();
        try {
            while (count >= max) {
                fullCondition.await();
            }
            count++;
            list.add(s);
            emptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String get() throws InterruptedException {
        lock.lock();
        try {
            while (count <= 0) {
                emptyCondition.await();
            }
            count--;
            String s = list.removeFirst();
            fullCondition.signalAll();
            return s;
        } finally {
            lock.unlock();
        }
    }

    public static void main(java.lang.String[] args)throws Exception {
        MyContainer2<java.lang.String> c = new MyContainer2<>();
        //�����������߳�
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) {
                    try {
                        System.out.println(c.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //�����������߳�
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) {
                    try {
                        c.put(Thread.currentThread().getName() + " " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "p" + i).start();
        }
    }
}