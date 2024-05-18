import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PoolForThreeThread {
    public static void main(String[] args) {

        CountDownLatch counterFotFirstThread = new CountDownLatch(10);
        CountDownLatch counterFotSecondThread = new CountDownLatch(10);
        CountDownLatch counterFotThirdThread = new CountDownLatch(10);

        NameThread nameThreadOne = new NameThread("Thread 1", counterFotFirstThread);
        NameThread nameTwo = new NameThread("Thread 2", counterFotSecondThread);
        NameThread nameThreadThree = new NameThread("Thread 3", counterFotThirdThread);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("Start threads");

        executorService.submit(nameThreadOne);
        executorService.submit(nameTwo);
        executorService.submit(nameThreadThree);

        try {
            counterFotFirstThread.await();
            counterFotSecondThread.await();
            counterFotThirdThread.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("Completion threads");
    }

}
class NameThread extends Thread{

    CountDownLatch counter;
    String nameThread;


    public NameThread(String nameThread, CountDownLatch counter) {
        this.nameThread = nameThread;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.println("The name thread  - " + nameThread + ". Output number " + i);
        }
    }
}
