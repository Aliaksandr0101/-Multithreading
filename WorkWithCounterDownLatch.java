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
}
