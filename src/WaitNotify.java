/**
 * Created by admin on 2018-04-23.
 */
public class WaitNotify {

    // Here the producer will print in produce through thread 1
    // It will then wait, for  default time out of 3 seconds
    // By waiting, it will release in the intrinsic lock on the class
    // It will signal to Thread 2 that the lock on the class is freee
    // Thread 2 will do its work, then call notify to let Thread 1 know to rertrieve the lock
    // Before Thread 2 ends, it will sleep for a second
    // Thread 1 will wake up and do its work -> printing "In Produce Again"
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("In Produce");
            wait(3000);
            System.out.println("In Produce Again");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("In Consumer");
            notify();
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        final WaitNotify process = new WaitNotify();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch(InterruptedException e) {
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
            e.printStackTrace();
        }
    }
}
