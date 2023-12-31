import java.util.function.IntUnaryOperator;

public class MyThread extends Thread{

    public void run(){
     int i = 0;
        IntUnaryOperator increaseByThree = x -> x + 3;

        for (int j = 0; j < 10  ; j++) {
            i = increaseByThree.applyAsInt(i);
        }
        System.out.println("Result after summation i = " + i);
    }

    public static class MyRunnable implements Runnable{
        @Override
        public void run() {
            int i = 256;

            IntUnaryOperator divideByTwo = x -> x / 2;

            for (int j = 0; j < 5 ; j++) {
                i = divideByTwo.applyAsInt(i);
            }

            System.out.println("Result after division i = " + i);
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyRunnable myRunnable = new MyRunnable();
        myThread.start();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

