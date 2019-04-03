package msb.c_09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:30
 * @Description:
 */
public class MyContainer5 {
    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MyContainer5 myContainer = new MyContainer5();

        CountDownLatch o = new CountDownLatch(1);

        new Thread(() -> {
                if (myContainer.size() != 5) {
                    try {
                        o.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("end....");
        }).start();


        Thread.sleep(1000);

        new Thread(() -> {
                for (int i = 0;i < 10; i++) {
                    myContainer.add(i);
                    System.out.println("add" + i);

                    if (myContainer.size() == 5) {
                        o.countDown();
                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
        }).start();
    }
}