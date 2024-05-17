import java.util.*;
import java.util.concurrent.CountDownLatch;

public class ExampleCountDownLatch {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<Integer> numbers = new ArrayList<>();
        InputThread inputThread = new InputThread(countDownLatch, numbers);
        inputThread.start();
        try {
            countDownLatch.await();
            ProcessingThread processingThread = new ProcessingThread(countDownLatch, numbers);
            processingThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class InputThread extends Thread{
    CountDownLatch countDownLatch;
    List<Integer> numbers;


    public InputThread(CountDownLatch countDownLatch, List<Integer> numbers) {
        this.countDownLatch = countDownLatch;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Now enter 5 numbers");
        while (numbers.size()<5){
            int num = scanner.nextInt();
            numbers.add(num);
        }
        countDownLatch.countDown();
        scanner.close();
        System.out.println("Input is finished");
    }
}
class ProcessingThread extends Thread{
    CountDownLatch countDownLatch;
    List<Integer> numbers;

    public ProcessingThread(CountDownLatch countDownLatch, List<Integer> numbers) {
        this.countDownLatch = countDownLatch;
        this.numbers = numbers;

    }

    @Override
    public void run() {
        System.out.println("Start processing your numbers");
        for (int num : numbers ){
            int newNum = num * 2;
            System.out.println("Number after processing = " + newNum);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
