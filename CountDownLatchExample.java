import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Set<Integer> numbers = new HashSet<>(5);
        Reader readerOne = new Reader(countDownLatch,numbers);
        new Thread(readerOne).start();

        /*new Thread(readerTwo).start();
        new Thread(readerThree).start();*/
try {
    countDownLatch.await();
    System.out.println("Завершена работа");
    for (int num : numbers) {
        // Например, уменьшаем число в 2 раза
        int processedNum = num / 2;
        System.out.println(processedNum);

    }

} catch (InterruptedException e) {
    e.printStackTrace();
}
}
}
class Reader implements Runnable {
    Scanner scanner = new Scanner(System.in);
    CountDownLatch countDownLatch = new CountDownLatch(5);
    Set<Integer> numbers;

    public Reader(CountDownLatch countDownLatch, Set<Integer> numbers) {
        this.countDownLatch = countDownLatch;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println("Введите 5 различных чисел:");
            int num = scanner.nextInt();
            numbers.add(num);
            countDownLatch.countDown();
    }

}