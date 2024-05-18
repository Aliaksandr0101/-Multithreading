import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PoolForThreeThread {
    public static void main(String[] args) {

        NameThread nameThreadOne = new NameThread("Thread 1");
        NameThread processThreadTwo = new NameThread("Thread 2");
        NameThread processThreadThree = new NameThread("Thread 3");

        ExecutorService executorService = Executors.newFixedThreadPool(3);


        executorService.submit(nameThreadOne);
        executorService.submit(processThreadTwo);
        executorService.submit(processThreadThree);


        executorService.shutdown();

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
            System.out.println("The name - " + nameThread);
        }
    }
}
