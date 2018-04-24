import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 2018-04-24.
 */

class WorkerC implements Runnable {
    private ConcurrentHashMap<String, Integer> map;

    public WorkerC(ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    public void run() {
        try {
            Thread.sleep(3000);
            map.put("H", 1);
            map.put("A", 2);
            map.put("M", 4);
            map.put("A", 5);
            map.put("D", 6);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerD implements Runnable {
    private ConcurrentHashMap<String, Integer> map;

    public WorkerD(ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println(map.get("H"));
            Thread.sleep(1000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("M"));
            System.out.println(map.get("D"));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentHashMapApp {


    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // The state of th map will be consistent
        new Thread(new WorkerC(map)).start();
        new Thread(new WorkerD(map)).start();

        // Only use when synchronization needed since it'll be slower
        //Collections.synchronizedList(new ArrayList<Object>());
    }

}
