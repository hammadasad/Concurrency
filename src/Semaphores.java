/**
 * Created by admin on 2018-04-24.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * Semaphores use a list of "Permits"
 * Actions are acquire() & release()
 *
 * They keep a count of the number of resources available
 * Semaphores (int number_permits, boolean fair)
 */

// Singleton Pattern to make this thread safe
enum Download {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Semaphores {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0 ; i < 15 ; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Download.INSTANCE.downloadData();
                }
            });
        }

    }
}
