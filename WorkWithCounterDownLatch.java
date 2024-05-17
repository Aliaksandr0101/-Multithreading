import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;

public class WorkWithCounterDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Work work = new Work(countDownLatch);
        new Thread(work).start();
        new Thread(work).start();
        new Thread(work).start();

        countDownLatch.await();
        System.out.println("all job done");

    }
}
class Work implements Runnable{
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done work");
        countDownLatch.countDown();

    }
    /*import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Set<Integer> numbers = new HashSet<>();

            System.out.println("Введите 5 различных чисел:");
            while (numbers.size() < 5) {
                int num = scanner.nextInt();
                numbers.add(num);
            }

            System.out.println("Вы ввели следующие числа:");
            for (int num : numbers) {
                // Например, уменьшаем число в 2 раза
                int processedNum = num / 2;
                System.out.println(processedNum);
            }
        }
    }*/

}
