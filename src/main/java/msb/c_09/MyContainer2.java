package msb.c_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 21:12
 * @Description:
 */
public class MyContainer2 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {
        MyContainer2 myContainer = new MyContainer2();

        new Thread(() -> {
            for (int i = 0;i < 10;i++) {
                myContainer.add(i);
                System.out.println(i);
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