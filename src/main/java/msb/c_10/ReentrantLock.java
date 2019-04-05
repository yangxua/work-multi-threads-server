package msb.c_10;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:48
 * @Description:
 */
public class ReentrantLock {

    synchronized void m1 () throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);

            Thread.sleep(500);
        }
    }

    synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

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