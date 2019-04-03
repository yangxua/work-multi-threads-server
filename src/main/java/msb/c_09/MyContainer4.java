package msb.c_09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:27
 * @Description:
 */
public class MyContainer4 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MyContainer4 myContainer = new MyContainer4();

        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                if (myContainer.size() != 5) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("end....");
                o.notify();
            }
        }).start();


        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0;i < 10; i++) {
                    myContainer.add(i);
                    System.out.println("add" + i);

                    if (myContainer.size() == 5) {
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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