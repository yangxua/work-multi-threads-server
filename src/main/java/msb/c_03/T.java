package msb.c_03;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:08
 * @Description:
 */
public class T {

    private int count = 10;

    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}