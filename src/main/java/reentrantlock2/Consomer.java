package reentrantlock2;

/**
 * @Auther: allanyang
 * @Date: 2019/3/28 17:43
 * @Description:
 */
public class Consomer {

    private Depot depot;

    public Consomer(Depot depot) {
        this.depot = depot;
    }

    public void consume(final int val) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.consume(val);
            }
        }).start();
    }
}