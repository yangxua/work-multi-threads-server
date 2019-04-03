package msb.c_07;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:42
 * @Description:
 */
public class T implements Runnable{

    private int flag;

    private static Object o1 = new Object();
    private static Object o2 = new Object();

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(111);
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(222);
                }
            }
        }
    }

    public static void main(String[] args) {
        T t1 = new T();
        T t2 = new T();
        t1.flag = 1;
        t2.flag = 2;

        new Thread(t1).start();
        new Thread(t2).start();
    }
}