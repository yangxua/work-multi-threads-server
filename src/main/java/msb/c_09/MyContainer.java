package msb.c_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 20:49
 * @Description:
 */
public class MyContainer {

    static List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        MyContainer myContainer = new MyContainer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 10;i++) {
                    myContainer.add(i);
                    System.out.println(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(() -> {
            while(true) {
                if (myContainer.size() == 5) {
                    break;
                }
            }
            System.out.println("55555555555555555");
        }).start();
    }
}