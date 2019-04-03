package readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: allanyang
 * @Date: 2019/4/3 09:50
 * @Description:
 */
public class Test1 {

    public static void main(String[] args) {
        MyCount myCount = new MyCount("10000",100);
        User user = new User("Tom", myCount);

        for (int i=0; i<3; i++) {
            user.getCash();
            user.setCash((i+1)*1000);
        }
    }
}

class User {
    private String name;
    private MyCount myCount;
    private ReadWriteLock myLock;

    public User(String name, MyCount myCount) {
        this.name = name;
        this.myCount = myCount;
        this.myLock = new ReentrantReadWriteLock();
    }

    public void setCash(final int cash) {
        new Thread(){
            @Override
            public void run() {
                myLock.writeLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() +" setCash start");
                    myCount.setCash(cash);
                    Thread.sleep(1);
                    System.out.println(Thread.currentThread().getName() +" setCash end");
                } catch (Exception e) {

                } finally {
                  myLock.writeLock().unlock();
                }
            }
        }.start();
    }

    public void getCash() {
        new Thread(){
            @Override
            public void run() {
                myLock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() +" getCash start");
                    myCount.getCash();
                    Thread.sleep(1);
                    System.out.println(Thread.currentThread().getName() +" getCash end");
                } catch (Exception e) {

                } finally {
                    myLock.readLock().unlock();
                }
            }
        }.start();
    }
}

class MyCount{
    private String id;
    private int cash;

    MyCount(String id, int cash) {
        this.id = id;
        this.cash = cash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCash() {
        System.out.println(Thread.currentThread().getName() +" getCash cash="+ cash);
        return cash;
    }

    public void setCash(int cash) {
        System.out.println(Thread.currentThread().getName() +" setCash cash="+ cash);
        this.cash = cash;
    }
}