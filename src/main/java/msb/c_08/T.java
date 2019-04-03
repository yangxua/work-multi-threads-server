package msb.c_08;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:49
 * @Description:
 */
public class T {

    volatile boolean flag = true;

    void m() {
        System.out.println(111);

        while(flag) {

        }

        System.out.println(222);
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m,"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       t.flag = false;
    }
}