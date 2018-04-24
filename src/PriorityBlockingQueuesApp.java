import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by admin on 2018-04-24.
 *
 * Priority Blocking Queues
 * -> They implement the BlockingQueues interface
 * -> Need to implement a comparable interface to determine priority ie. implements Comparable<Dog>
 * -> Unbounded Concurrent Queue
 * -> Priority the same when comparable value is 0
 * -> No null items
 */

class Worker1 implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Worker1(BlockingQueue queue) {
        this.blockingQueue = queue;
    }
    public void run() {
        try {
            blockingQueue.put("H");
            Thread.sleep(1000);
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("M");
            Thread.sleep(1000);
            blockingQueue.put("M");
            Thread.sleep(1000);
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("D");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker2A implements Runnable {

    // The comparable interface would cause the alphabets to be sorted in this priority queue
    private BlockingQueue<String> blockingQueue;

    public Worker2A(BlockingQueue queue) {
        this.blockingQueue = queue;
    }
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Take: " + blockingQueue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + blockingQueue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + blockingQueue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + blockingQueue.take());
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class PriorityBlockingQueuesApp {
    public static void main(String[] args) {
        BlockingQueue<String> priorityQueue = new PriorityBlockingQueue<>();

        new Thread(new Worker1(priorityQueue)).start();
        new Thread(new Worker2A(priorityQueue)).start();
    }
}
