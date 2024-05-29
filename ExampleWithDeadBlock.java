import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class ExampleWithDeadBlock {
        private static String sharedString;
        private static final Lock lock = new ReentrantLock();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите строку:");
            sharedString = scanner.nextLine();
            scanner.close();

            Thread thread1 = new Thread(new CharacterReplacer('а',  'о'));
            Thread thread2 = new Thread(new CharacterReplacer('а',  'н'));

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class CharacterReplacer implements Runnable {
            private final char targetLower;
            private final char replacement;

            public CharacterReplacer(char targetLower, char replacement) {
                this.targetLower = targetLower;
                this.replacement = replacement;
            }

            @Override
            public void run() {
                String textForWork = sharedString;
                lock.lock();
                try {
                    textForWork = textForWork.replace(targetLower, replacement);
                    System.out.println(Thread.currentThread().getName() + " изменил строку на: " + textForWork);
                } finally {
                    lock.unlock();
                }
            }
        }
    }




