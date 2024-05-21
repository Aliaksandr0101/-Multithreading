import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolForThreadTaskTwo {
    public static void main(String[] args) {

        ProcessThread processThreadOne = new ProcessThread("Thread 1");
        ProcessThread processThreadTwo = new ProcessThread("Thread 2");
        ProcessThread processThreadThree = new ProcessThread("Thread 3");
        ProcessThread processThreadFour = new ProcessThread("Thread 4");
        ProcessThread processThreadFive = new ProcessThread("Thread 5");
        ProcessThread processThreadSix = new ProcessThread("Thread 6");
        ProcessThread processThreadSeven = new ProcessThread("Thread 7");
        ProcessThread processThreadEight = new ProcessThread("Thread 8");
        ProcessThread processThreadNine = new ProcessThread("Thread 9");
        ProcessThread processThreadTen = new ProcessThread("Thread 10");

        ExecutorService executorService = Executors.newFixedThreadPool(2);



        executorService.submit(processThreadOne);
        executorService.submit(processThreadTwo);
        executorService.submit(processThreadThree);
        executorService.submit(processThreadFour);
        executorService.submit(processThreadFive);
        executorService.submit(processThreadSix);
        executorService.submit(processThreadSeven);
        executorService.submit(processThreadEight);
        executorService.submit(processThreadNine);
        executorService.submit(processThreadTen);


        executorService.shutdown();

    }

}

class ProcessThread extends Thread{

    String nameThread;

    public ProcessThread(String nameThread) {
        this.nameThread = nameThread;
    }

    @Override
    public void run() {
        for (int i =0; i < 10; i++) {
            System.out.println("The name - " + nameThread + " : " + i);
        }
    }
}
