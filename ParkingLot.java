import java.util.concurrent.Semaphore;

class ParkingLot {
    private Semaphore semaphore;

    public ParkingLot(int capacity) {
        semaphore = new Semaphore(capacity);
    }

    public void park() {
        try {
            semaphore.acquire(); // Занимаем место на парковке
            System.out.println("Машина припарковалась. Свободных мест: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leave() {
        semaphore.release(); // Освобождаем место на парковке
        System.out.println("Машина покинула парковку. Свободных мест: " + semaphore.availablePermits());
    }
}

class Car extends Thread {
    private ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.park(); // Машина приезжает и паркуется
        // Какие-то действия внутри парковки
        try {
            Thread.sleep(2000); // Машина задерживается на парковке (для примера)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parkingLot.leave(); // Машина покидает парковку
    }
}

class Dom {
    public static void main(String[] args) {
        int n = 5; // Количество свободных мест на парковке
        int k = 3; // Количество машин, которые не должны занимать место на парковке

        ParkingLot parkingLot = new ParkingLot(n);

        for (int i = 0; i < n + k; i++) {
            new Car(parkingLot).start(); // Создаем и запускаем машины
        }
    }
}
