package msb.c_01;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:05
 * @Description:
 */
public class T {

    private int count = 10;

    Object lock = new Object();

    public void m() {
        synchronized (lock) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}