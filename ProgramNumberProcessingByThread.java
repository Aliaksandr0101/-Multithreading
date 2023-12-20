import java.util.Scanner;

public class ProgramNumberProcessingByThread {
    public static void main(String[] args) {
     SharedResource commonResource = new SharedResource();
        System.out.println("enter number");
        Scanner scanner = new Scanner(System.in);
        commonResource.numberForWork = scanner.nextInt();
        scanner.close();
        for (int i = 1; i < 4; i++) {
            Thread newThread = new Thread(new ThreadForWork(commonResource));
            newThread.setName("Thread №" + i);
            newThread.start();
        }
}
}
class SharedResource{
    int numberForWork;
}
class ThreadForWork implements Runnable{
    SharedResource sharedResource;

    public ThreadForWork(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {

        for (int i = 1; i < 11  ; i++) {
            System.out.printf("%s %d \n", Thread.currentThread().getName(), sharedResource.numberForWork);
            sharedResource.numberForWork--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}