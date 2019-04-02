package reentrantlock2;

/**
 * @Auther: allanyang
 * @Date: 2019/3/29 17:42
 * @Description:
 */
public class LockTest2 {

    public static void main(String[] args) {
        reentrantlock2.Depot depot = new reentrantlock2.Depot(100);
        reentrantlock2.Producer producer = new reentrantlock2.Producer(depot);
        reentrantlock2.Consomer consomer = new reentrantlock2.Consomer(depot);


        producer.produce(60);
        producer.produce(120);
        consomer.consume(90);
        consomer.consume(150);
        producer.produce(110);
    }
}