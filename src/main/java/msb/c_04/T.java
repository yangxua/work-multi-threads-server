package msb.c_04;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:12
 * @Description:
 */
public class T {

    private static int count = 10;

    public static synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void m1() {
        synchronized (T.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}