package msb.c_06;

/**
 * @Auther: allanyang
 * @Date: 2019/4/2 21:25
 * @Description:
 */
public class Account{

    private String name;
    private double account;

    public synchronized void set(String name, double account) {
        this.name = name;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.account = account;
    }

    public double get(String name) {
        return this.account;
    }

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(() -> account.set("zs", 100)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.get("zs"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.get("zs"));
    }
}