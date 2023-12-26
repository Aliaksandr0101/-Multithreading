public class Main {
    private static int i = 0;

    public static void main(String[] args) {
        Thread incrementThread = new Thread(new IncrementTask());
        Thread decrementThread = new Thread(new DecrementTask());

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of i: " + i);
    }

    static class IncrementTask implements Runnable {
        @Override
        public void run() {
            synchronized (Main.class) {
                i += 10;
                System.out.println("Incremented by 10, i = " + i);
            }
        }
    }

    static class DecrementTask implements Runnable {
        @Override
        public void run() {
            try {
                // Ждем, пока первый поток выполнит увеличение на 10
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Main.class) {
                i -= 5;
                System.out.println("Decremented by 5, i = " + i);
            }
        }
    }
}
