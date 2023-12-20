import java.util.Scanner;

public class SubtractingNumberThread  extends Thread{
    int number;

    public SubtractingNumberThread(int number) {
        this.number = number;
    }
    public void run(){

        for (int i = 0; i < 3 ; i++) {
            System.out.printf("%s %d \n", Thread.currentThread().getName(), number);
            number--;
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("pls enter your number");
        int numberUser = scanner.nextInt();
        SubtractingNumberThread subtractingNumberThread = new SubtractingNumberThread(numberUser);
        for (int i = 1; i < 4 ; i++) {
        Thread thread = new Thread(subtractingNumberThread);
        thread.setName("Thread " + i);
        thread.start();

        }
    }
}
