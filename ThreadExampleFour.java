public class ThreadExampleFour {



    class SubtractionAndSum{
        public void setNumber(int number) {
            this.number = number;
        }
        public int getNumber() {
            return number;
        }

        public SubtractionAndSum(int number) {
            this.number = number;
        }

        private int number = 78;

    }

    class  Sum implements  Runnable{
        private SubtractionAndSum number;

        public Sum(SubtractionAndSum number) {
            this.number = number;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (number){
                int newNumber = number - 10;

                try {
                    System.out.println(name + "ждем вызова метода notify " + System.currentTimeMillis());
                    number.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " был вызов метода" + System.currentTimeMillis());

                System.out.println(name + ":" + number.getNumber());
            }

        }
    }
}
