public class Program {
    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6 ; i++) {
            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread i = " + i);
            t.start();
        }
    }
}
class CommonResource{
    int x;
    public synchronized void  increment(){
        x = 1;
        for (int i = 1; i < 5 ; i++) {
            System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
            System.out.println();

            x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class CountThread implements Runnable{
   CommonResource res;

    public CountThread(CommonResource res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.increment();
    }
}
