package msb.c_09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:19
 * @Description:
 */
public class MyContainer3 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MyContainer3 myContainer3 = new MyContainer3();

        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                if (myContainer3.size() != 5) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("end....");
            }
        }).start();


        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0;i < 10; i++) {
                    myContainer3.add(i);
                    System.out.println("add" + i);

                    if (myContainer3.size() == 5) {
                        o.notify();
                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }
}