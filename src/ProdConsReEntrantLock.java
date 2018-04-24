import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2018-04-23.
 */

class Worker2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer");
        condition.await();
        System.out.println("Producer again");
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer");
        condition.signal();
        lock.unlock();
    }

}
public class ProdConsReEntrantLock {




    public static void main(String[] args) {
        final Worker2 worker = new Worker2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {

        }
    }
}
