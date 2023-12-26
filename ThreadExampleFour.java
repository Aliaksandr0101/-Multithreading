public class ThreadExampleFour {


    public static void main(String[] args) {
        SubtractionAndSum subtractionAndSum = new SubtractionAndSum(0);
        Sum sumresult = new Sum(subtractionAndSum);
        Sub subresult = new Sub(subtractionAndSum);
        new Thread(subresult).start();
        new Thread(sumresult).start();
    }
}

    class SubtractionAndSum {
        private int number = 0;

        public SubtractionAndSum(int number) {
            this.number = number;
        }

        public synchronized void sub() {
            if (number == 10) {
                try {
                    System.out.println("gtdhsq" + number);
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number -= 5;
            System.out.println("после вычитания" + number);
            notify();
        }

        public synchronized void sum() {
            if (number == 0) {
                System.out.println("fjerjbe" + number);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number += 10;
            System.out.println("после суммирования" + number);
            notify();
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
    class Sub implements Runnable{
        SubtractionAndSum sub1;

        public Sub(SubtractionAndSum sub1) {
            this.sub1 = sub1;
        }

        @Override
        public void run() {
            sub1.sub();

        }

}


