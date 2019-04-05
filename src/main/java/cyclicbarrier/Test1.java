package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: allanyang
 * @Date: 2019/4/4 10:02
 * @Description:
 */
public class Test1 {

    static CyclicBarrier cb;

    public static void main(String[] args) {
        cb = new CyclicBarrier(5, () -> {
            System.out.println("CyclicBarrier's parties is: "+ cb.getParties());
        });

        for (int i = 0;i < 5;i++) {
            new ThreadA().start();
        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
            try {
                cb.await();
                System.out.println(Thread.currentThread().getName() + " continued.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}