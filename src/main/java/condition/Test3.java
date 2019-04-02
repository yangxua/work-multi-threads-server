package condition;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 15:46
 * @Description:
 */
public class Test3 {
    private static BoundedBuffer bb = new BoundedBuffer();

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            new PutThread("p"+i, i).start();
            new TakeThread("t"+i).start();
        }
    }

    static class PutThread extends Thread {
        private int num;
        public PutThread(String name, int num) {
            super(name);
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1);    // 线程休眠1ms
                bb.put(num);        // 向BoundedBuffer中写入数据
            } catch (Exception e) {
            }
        }
    }

    static class TakeThread extends Thread {
        public TakeThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            try {
                Thread.sleep(10);                    // 线程休眠1ms
                Integer num = (Integer)bb.take();    // 从BoundedBuffer中取出数据
            } catch (Exception e) {
            }
        }

    }
}