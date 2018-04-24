import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2018-04-23.
 */

    /**

        Re Entrant Lock has the same behavior as synchronized approach & more
        Takes a fairness parameter upon construction
        - which means that the longest waiting thread will get the lock
        - We have to use a try and catch during the critical section
        - Call unlock in finally block

     */
public class ReEntrantLockUse {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();



    public static void increment() {

        lock.lock();

        try {
            for(int i = 0 ; i < 1000 ; i++)
                counter++;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The value of the counter is : " + counter);
    }
}
