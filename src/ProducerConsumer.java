import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018-04-23.
 */
public class ProducerConsumer {

    static ProducerConsumer producerConsumer = new ProducerConsumer();

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    // Using an object as a lock

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if(list.size() == LIMIT) {
                    System.out.println("List is full");
                    lock.wait();
                } else {
                    System.out.println("Adding value to list");
                    list.add(value);
                    value++;
                    lock.notify();
                }

                Thread.sleep(500);
            }

        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if(list.size() == BOTTOM) {
                    System.out.println("Waiting for list to become full");
                    lock.wait();
                } else {
                    System.out.println("Removing element from list " + list.remove(list.size() - 1));
                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.producer();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consumer();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
