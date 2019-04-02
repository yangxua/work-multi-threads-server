package msb.c_02;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:07
 * @Description:
 */
public class T {

    private int count = 10;

    public void m() {
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}