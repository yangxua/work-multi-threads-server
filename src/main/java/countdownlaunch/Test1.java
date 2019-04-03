package countdownlaunch;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 20:31
 * @Description:
 */
public class Test1 {

    private static volatile CountDownLatch latch ;

    public static void main(String[] args) {
        latch = new CountDownLatch(5);

        System.out.println("start...");

        for (int i = 0;i < 125;i++) {
            new ThreadA().start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end....");
    }

    static class ThreadA extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            latch.countDown();
        }
    }
}