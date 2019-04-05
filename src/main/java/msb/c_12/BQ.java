package msb.c_12;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 12:54
 * @Description:
 */
public class BQ {

    private static LinkedBlockingQueue queue = new LinkedBlockingQueue();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0;i < 100;i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0;i < 5;i++) {
                while(true) {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}