import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ExampleCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new Run());
        SportMan sportManOne = new SportMan(cyclicBarrier, "Egor");
        SportMan sportManTwo = new SportMan(cyclicBarrier, "Genia");
        SportMan sportManThree = new SportMan(cyclicBarrier, "Sasha");

    }
    static class Run extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Все спортсмены собрались. Забег начался");
        }
    }
    static class SportMan extends Thread{
        CyclicBarrier cyclicBarrier;
        String name;

        public SportMan(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " готов к забегу");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
