package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: allanyang
 * @Date: 2019/4/4 16:12
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Thread ta = new ThreadA();
        Thread tb = new ThreadA();
        Thread tc = new ThreadA();

        threadPool.execute(ta);
        threadPool.execute(tb);
        threadPool.execute(tc);

        threadPool.shutdown();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ " is running.");
        }
    }
}