public class TheFirstLessonJavaMultithreading {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        resource.setI(5);
        MyThreadTwo myThreadTwo = new MyThreadTwo();
        myThreadTwo.setName("one");
        MyThreadTwo myThreadTwo1 = new MyThreadTwo();
        myThreadTwo.setResource(resource);
        myThreadTwo1.setResource(resource);
        myThreadTwo.start();
        myThreadTwo1.start();
        myThreadTwo.join();
        myThreadTwo1.join();
        System.out.println(resource.getI());

    }
}
class MyThreadTwo extends Thread{
    Resource resource;
    public void setResource(Resource resource) {
        this.resource = resource;
    }
 @Override
    public void run(){
        resource.changeI();
 }
}
class Resource {
    private int i;

    public synchronized void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public synchronized void changeI(){
        int i = this.i;
        if (Thread.currentThread().getName().equals("one")){
            Thread.yield();
        }
        i++;
        this.i = i;
    }
}