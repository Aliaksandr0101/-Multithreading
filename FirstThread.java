import org.w3c.dom.ls.LSOutput;

import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Date;

public class FirstThread extends Thread{
    public void run() {
        for (int i = 0; i < 5; i++) {
            Date currentDate = new Date();
            System.out.println("Date and time now: " + currentDate);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        firstThread.start();
    }

}
