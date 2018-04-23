/**
 * Created by admin on 2018-04-23.
 */

// If we didn't have the Synchronized
// The counter incrementing wouldn't be atomic
    // Thread 1 will read the value and increment, and before setting, thread 2 will acquire the value
    // of the counter which will be 0 and will increment it. Thread 1 will put back the value of 1
    // Thread 2 will put back the value of 1
public class SynchronizedApp {

    private static int counter = 0;

    private static void increment() {
        counter++;
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                for(int i = 0 ; i < 100 ; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                for(int i = 0 ; i < 100 ; i++) {
                    increment();
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
        System.out.println(counter);

    }

    public static void main(String[] args) {
        process();
    }
}
