package msb.c_14;

import java.util.concurrent.*;

/**
 * @Auther: allanyang
 * @Date: 2019/4/5 14:20
 * @Description:
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory ,rejectedExecutionHandler);
    }

    public static Executor getExecutor(String name, int thread, int queues) {
        return new MyThreadPoolExecutor(thread, thread, 0, TimeUnit.MILLISECONDS, queues == 0 ? new SynchronousQueue<Runnable>() : (queues < 0 ? new LinkedBlockingQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(queues)), new MyThreadFactory(name), new AbortPolicyWithReport(name));
    }

    public static Executor getExecutor(int thread, int queues) {
        return getExecutor("MyThreadPool", thread, queues);
    }

    public static Executor getExecutor(int thread) {
        return getExecutor(thread,-1);
    }

    public static Executor getExecutor() {
        return getExecutor(Runtime.getRuntime().availableProcessors() * 2);
    }
}