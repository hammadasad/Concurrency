/**
 * Created by admin on 2018-04-23.
 */
public class SynchronizedBlocks {
    private static int count1 = 0;
    private static int count2 = 0;

    private static Object object1 = new Object();
    private static Object object2 = new Object();

    // DO NOT DO
    // private static synchronized void add
    // It puts an intrinsic lock on the class
    // Means that the other thread can't access the other methods of this class while one thread has access
    // to a single method of this class
    // Same thing as doing synchronzied(SynchronizedBlocks.app) {}

    // We create objetcs to use as locks

    private static void add() {

        synchronized(object1) {
            count1++;
        }
    }

    private static void addAgain() {
        synchronized (object2) {
            count2++;
        }
    }

    private static void compute() {
        for(int i = 0 ; i < 100 ; i++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                compute();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                compute();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {

        }

        System.out.println("Count1: " + count1 + " ,Count 2: " + count2);
    }
}
