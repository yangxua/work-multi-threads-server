package msb.c_14;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 14:07
 * @Description:
 */
public class MyThreadFactory implements ThreadFactory {

    private final AtomicInteger count = new AtomicInteger(1);

    private String flag;

    public MyThreadFactory(String flag) {
        this.flag = flag;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "pool-" + flag + "-thread-" + count.getAndIncrement());
    }
}