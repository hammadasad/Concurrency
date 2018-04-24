import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by admin on 2018-04-24.
 *
 * Callable lets us return an object from the thread
 * Need to use Future wrapper object to retrieve result, through .get() method on future
 */

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        return "ID is: " + id;
    }
}
public class CallableFutureApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<String>> list = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++) {
            list.add(executorService.submit(new Processor(i)));
        }

        for(Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }

}
