package condition;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 20:13
 * @Description:
 */
public class Test5 {

    private static Thread mainThread;

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" start ta");
        ta.start();
        System.out.println(Thread.currentThread().getName()+" block");
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakup others");
            LockSupport.unpark(mainThread);
        }
    }
}