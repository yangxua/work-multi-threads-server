package reentrantlock;

/**
 * @Auther: allanyang
 * @Date: 2019/3/28 17:33
 * @Description:
 */
public class LockTest1 {


    public static void main(String[] args) {
        Depot depot = new Depot();
        Producer producer = new Producer(depot);
        Consomer consomer = new Consomer(depot);

        producer.produce(60);
        producer.produce(120);
        consomer.consume(90);
        consomer.consume(150);
        producer.produce(110);
    }
}