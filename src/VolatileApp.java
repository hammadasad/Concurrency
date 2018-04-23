/**
 * Created by admin on 2018-04-23.
 */

class Worker implements Runnable {

    // Use volatile to run from main memory instead of Thread's cache
    private volatile boolean isTerminated = false;

    public void run() {
        while(!isTerminated) {
            System.out.println("Hello from worker class");

            try {
                Thread.sleep(300);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setTerminated(boolean set) {
        this.isTerminated = set;
    }

    public boolean getTerminated() {
        return this.isTerminated;
    }

}


public class VolatileApp {

    public static void main(String[] args) {
        Worker w1 = new Worker();
        Thread t1 = new Thread(w1);
        t1.start();

        try {
            Thread.sleep(300);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        w1.setTerminated(true);
        System.out.println("Finished");

    }
}
