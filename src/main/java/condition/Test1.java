package condition;

/**
 * @Auther: allanyang
 * @Date: 2019/4/1 21:46
 * @Description:
 */
public class Test1 {

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");

        synchronized (ta) {
            try {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" block");
                ta.wait();
                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()+" wakup others");
                notify();
            }
        }
    }
}