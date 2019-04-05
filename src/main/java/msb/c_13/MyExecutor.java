package msb.c_13;

import java.util.concurrent.Executor;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 13:31
 * @Description:
 */
public class MyExecutor implements Executor {

    public static void main(String[] args) {
        new MyExecutor().execute(() -> {
            System.out.println("hello executor!");
        });
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}