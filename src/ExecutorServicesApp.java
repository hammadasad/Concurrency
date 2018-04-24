import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2018-04-24.
 *
 * Executor Service
 *
 * Executors.newCachedThreadPool();
 * -> checks if there are any threads that completed their job and reuses them
 * -> If no waiting threads, create new threads
 * -> dynamically reuses threads
 *
 * Executors.newFixedThreadPool(N);
 * -> maximize & choose the # of threads
 * -> If we want to start a new job, we have to wait for a thread to complete
 *
 * Executors.newSingleThreadExecutor();
 * -> Uses a single thread for the task
 *
 */
public class ExecutorServicesApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for(int i = 0 ; i < 10 ; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0 ; i < 10 ; i++) {
                        System.out.println(i);
                        try {
                            Thread.sleep(200);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
