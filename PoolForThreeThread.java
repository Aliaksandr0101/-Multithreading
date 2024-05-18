import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PoolForThreeThread {
    public static void main(String[] args) {

        NameThread nameThreadOne = new NameThread("Thread 1");
        NameThread nameTwo = new NameThread("Thread 2");
        NameThread nameThreadThree = new NameThread("Thread 3");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("Start threads");

        executorService.submit(nameThreadOne);
        executorService.submit(nameTwo);
        executorService.submit(nameThreadThree);


        executorService.shutdown();
        System.out.println("Completion threads");
    }

}
class NameThread extends Thread{


    String nameThread;


    public NameThread(String nameThread) {
        this.nameThread = nameThread;
    }

    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.println("The name - " + nameThread + " : output " + i);
        }
    }
}
