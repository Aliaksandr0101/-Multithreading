import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    public static void main(String[] args) {
        ExecutorService numbersOfThread = Executors.newFixedThreadPool(7);
        Semaphore placeInTheLibrary = new Semaphore(7);
        Semaphore numbersBooks = new Semaphore(100);
        int numberOfPerson = 60;
        for (int i = 0; i < numberOfPerson; i++) {
            PersonInLibrary personTakeThePlace = new PersonInLibrary("Visitor №" + (i+1));
            personTakeThePlace.placeInTheLibrary = placeInTheLibrary;
            personTakeThePlace.numbersBooks = numbersBooks;
            numbersOfThread.submit(personTakeThePlace);
        }
        numbersOfThread.shutdown();
    }
}

class PersonInLibrary extends Thread{
    String numberVisitor;

    public PersonInLibrary(String numberVisitor) {
        this.numberVisitor = numberVisitor;
    }

    Semaphore placeInTheLibrary;

    @Override
    public void run() {

            try {
                placeInTheLibrary.acquire();
                System.out.println("Занял место " + numberVisitor + ".");
                TakeBooks();
                Thread.sleep(2000);
                placeInTheLibrary.release();
                System.out.println("Освободил место " + numberVisitor + ".");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    Semaphore numbersBooks;
    Lock lockOneLibrarian = new ReentrantLock();

    public void TakeBooks() {
        lockOneLibrarian.lock();
        for (int i = 0; i < 3; i++){
            try {
                numbersBooks.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(numberVisitor + " взял три книги.");
        System.out.println(numberVisitor + " начал читать.");
        lockOneLibrarian.unlock();
        try {
            Thread.sleep(2000);
            System.out.println(numberVisitor + " закончил чтение.");
            for (int i = 0; i < 3; i++) {
                numbersBooks.release();
            }
            System.out.println(numberVisitor + " вернул книги.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
