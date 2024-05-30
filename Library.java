import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    public static void main(String[] args) {
        Semaphore placeInTheLibrary = new Semaphore(7);
        Semaphore numbersBooks = new Semaphore(100);
        int numberOfPerson = 10;
        for (int i = 0; i < numberOfPerson; i++) {
            TakeThePlace personTakeThePlace = new TakeThePlace();
            personTakeThePlace.placeInTheLibrary = placeInTheLibrary;
            personTakeThePlace.numbersBooks = numbersBooks;
            personTakeThePlace.start();
        }


    }

}
class TakeThePlace extends Thread{
    Semaphore placeInTheLibrary;


    @Override
    public void run() {

        synchronized (placeInTheLibrary) {
            try {
                placeInTheLibrary.acquire();
                System.out.println("Занял место " + Thread.currentThread().getName() + "осталось мест " + placeInTheLibrary.availablePermits());
                TakeBooks();
                Thread.sleep(2000);
                placeInTheLibrary.release();
                System.out.println("Освободил место " + Thread.currentThread().getName() + "осталось мест " + placeInTheLibrary.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    Semaphore numbersBooks;
    Lock lock = new ReentrantLock();

    public void TakeBooks() {
        lock.lock();
        for (int i = 0; i < 3; i++){
            try {
                numbersBooks.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "взял три книги" + "осталось" + numbersBooks.availablePermits());
        lock.unlock();
        System.out.println("читает");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("вернул книги");
        numbersBooks.release();

    }
}
/*
class PersonL{
    Semaphore numbersBooks;
    Lock lock = new ReentrantLock();

    public void TakeBooks() {
        lock.lock();
        for (int i = 0; i < 3; i++){
            try {
                numbersBooks.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "взял три книги" + "осталось" + numbersBooks.availablePermits());
        lock.unlock();
        System.out.println("читает");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("вернул книги");
        numbersBooks.release();

    }
}
*/
