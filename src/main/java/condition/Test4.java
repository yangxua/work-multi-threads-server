package condition;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 20:05
 * @Description:
 */
public class Test4 {

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");

        try {
            synchronized (ta) {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();
                System.out.println(Thread.currentThread().getName()+" block");
                ta.wait();
                System.out.println(Thread.currentThread().getName()+" continue");
            }
        } catch (InterruptedException  e) {
            e.printStackTrace();
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
                 notify();    // 唤醒“当前对象上的等待线程”
            }
        }
    }
}