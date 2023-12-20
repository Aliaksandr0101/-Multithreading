import java.util.Scanner;

public class SubtractingNumberThreadTwo  extends Thread{
    private static int number;

    public synchronized static void decrement(){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), number);
            number--;

    }
    public void run(){

        for (int i = 0; i < 3 ; i++) {
                decrement();
            }
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("pls enter your number");
            number = scanner.nextInt();
            scanner.close();
           SubtractingNumberThreadTwo subtractingNumberThreadTwo = new SubtractingNumberThreadTwo();

           for (int i = 1; i < 4 ; i++) {
                Thread thread = new Thread(subtractingNumberThreadTwo);
                thread.setName("Thread " + i);
                thread.start();
           }
    }
    }


