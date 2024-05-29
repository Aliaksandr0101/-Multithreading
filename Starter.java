import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Starter {
    public static void main(String[] args) {
        final int MAX = 16;
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MyAction(MAX));
        System.out.println("FINISH");
    }
}
class MyAction extends RecursiveAction{
 private int value;

    public MyAction(int value) {
        this.value = value;
    }

    @Override
    protected void compute() {
      if(value < 4) {
     System.out.println("Hello before: " + Thread.currentThread().getId() + "  " + value);
     try {
         Thread.sleep(2000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
     System.out.println("Hello after: " + Thread.currentThread().getId() + " " + value);
 } else {
     MyAction m1 = new MyAction(value/2);
     MyAction m2 = new MyAction(value/2);
     invokeAll(m1,m2);
 }
    }
}
