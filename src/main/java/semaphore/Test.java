package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: allanyang
 * @Date: 2019/4/4 15:02
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(10);
        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.execute(new ThreadA(semaphore, 5));
        pool.execute(new ThreadA(semaphore, 4));
        pool.execute(new ThreadA(semaphore, 7));

        pool.shutdown();
    }

    static class ThreadA extends Thread {
        private volatile Semaphore semaphore;
        private int count;

        public ThreadA(Semaphore semaphore, int count) {
            this.semaphore = semaphore;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(count);
                Thread.sleep(0b111110100);
                System.out.println(Thread.currentThread().getName() + " acquire count="+count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.println(Thread.currentThread().getName() + " release " + count + "");
            }
        }
    }
}