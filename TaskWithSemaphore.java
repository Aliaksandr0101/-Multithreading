import java.util.concurrent.Semaphore;

public class TaskWithSemaphore {
    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        Semaphore parkingSpace = new Semaphore(n);
        for (int i = 0; i < n + k; i++) {
            RacingCar car = new RacingCar();
            car.parkingSpace = parkingSpace;
            car.start();
        }
    }
}
class RacingCar extends Thread{
    Semaphore parkingSpace;

    @Override
    public void run() {
        try {
            parkingSpace.acquire();
            System.out.println("The car is parked. Free places stay = " + parkingSpace.availablePermits());
            parkingSpace.release();
            System.out.println("The car released place. Free places stay = " + parkingSpace.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

