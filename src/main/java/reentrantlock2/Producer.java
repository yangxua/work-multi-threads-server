package reentrantlock2;

/**
 * @Auther: allanyang
 * @Date: 2019/3/28 17:40
 * @Description:
 */
public class Producer {

    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    public void produce(final int val) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.produce(val);
            }
        }).start();
    }
}