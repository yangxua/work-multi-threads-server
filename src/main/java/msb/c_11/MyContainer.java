package msb.c_11;

import java.util.LinkedList;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 11:16
 * @Description:
 */
public class MyContainer {

    private LinkedList<Integer> list = new LinkedList<>();
    private int max = 10;
    private int count;

    public synchronized void put(int i) throws InterruptedException {
        while (count >= max) {
            this.wait();
        }
        count++;
        list.add(i);
        this.notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while(count <= 0) {
            this.wait();
        }
        count--;
        Integer integer = list.removeFirst();
        this.notifyAll();
        return integer;
    }

    public static void main(String[] args) throws InterruptedException {
        MyContainer myContainer = new MyContainer();

        for (int j = 0;j < 5;j++) {
            new Thread(() -> {
            for (int i = 0;i < 10;i++) {
                try {
                    System.out.println(myContainer.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }).start();
        }

        Thread.sleep(500);

        for (int i = 0;i < 2;i++) {
            new Thread(() -> {
            for (int j = 0;j < 25;j++) {
                try {
                    myContainer.put(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }).start();
        }
    }
}