import sun.jvm.hotspot.opto.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 2018-04-24.
 *
 *
 * Blocking Queues are interfaces that represent a thread safe queue
 * -> You can take or put items in it
 * -> One thread putting items into the queue, while another thread takes from it at the same time
 * -> put() & take() methods
 */

class FirstWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    // Pass it the queue
    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        int counter = 0;
        while(true) {
            try {
                blockingQueue.put(counter);
                System.out.println("Putting in Queue : " + counter);
                counter++;
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                 e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    // Pass it the queue
    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while(true) {
            // If the blocking queue is empty, it this worker will not be able to take
            try {
                System.out.println("Taking Item from Queue: " + blockingQueue.take());
                Thread.sleep(10000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ProdConsBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(15);
        FirstWorker w1 = new FirstWorker(blockingQueue);
        SecondWorker w2 = new SecondWorker(blockingQueue);

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);

        t1.start();
        t2.start();
    }
}
