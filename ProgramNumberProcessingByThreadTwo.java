import java.util.Scanner;

public class ProgramNumberProcessingByThreadTwo {
    public static void main(String[] args) {
        SharedResourceTwo commonResourceTwo = new SharedResourceTwo();
        System.out.println("enter number");
        Scanner scanner = new Scanner(System.in);
        commonResourceTwo.numberForWork = scanner.nextInt();
        scanner.close();
        for (int i = 1; i < 4; i++) {
            Thread newThread = new Thread(new ThreadForWorkTwo(commonResourceTwo));
            newThread.setName("Thread №" + i);
            newThread.start();
        }
    }
}
class SharedResourceTwo{
    int numberForWork;
}
class ThreadForWorkTwo implements Runnable{
    SharedResourceTwo sharedResourceTwo;

    public ThreadForWorkTwo(SharedResourceTwo sharedResourceTwo) {
        this.sharedResourceTwo = sharedResourceTwo;
    }

    @Override
    public void run() {
        synchronized (sharedResourceTwo) {

            for (int i = 1; i < 11; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), sharedResourceTwo.numberForWork);
                sharedResourceTwo.numberForWork--;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
