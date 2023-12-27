public class ThreadExampleFour {


    public static void main(String[] args) {
        SubtractionAndSum subtractionAndSum = new SubtractionAndSum(0);
        Sum sumResult = new Sum(subtractionAndSum);
        Subtraction subtractionResult = new Subtraction(subtractionAndSum);
        new Thread(subtractionResult).start();
        new Thread(sumResult).start();
    }
}

    class SubtractionAndSum {
        private int i;

        public SubtractionAndSum(int i) {

            this.i = i;
        }

        public synchronized void sum() {
            if (i == 10) {
                try {
                    System.out.println("Жду пока выполнит работу другой поток i = " + i);
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i += 10;
            System.out.println("Это поток Sum. Суммирование выполняется первым i = " + i);
            notify();

        }

        public synchronized void subtract() {
            if (i == 0) {
                System.out.println("Жду, когда выполнит работу поток Sum. Поток Sub зашел первым, а i = " + i);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                i -= 5;
                System.out.println("Выполнение работы потока Subtract после notify от потока Sum. i = " + i);
            }

    }

    class Sum implements Runnable{
        SubtractionAndSum sum1;

        public Sum(SubtractionAndSum sum1) {
            this.sum1 = sum1;
        }

        @Override
        public void run() {
            sum1.sum();
        }
    }
    class Subtraction implements Runnable{
        SubtractionAndSum sub1;

        public Subtraction(SubtractionAndSum sub1) {
            this.sub1 = sub1;
        }

        @Override
        public void run() {
            sub1.subtract();

        }

}


