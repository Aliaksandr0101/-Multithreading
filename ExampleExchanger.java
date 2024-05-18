import java.util.concurrent.Exchanger;

public class ExampleExchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Person person = new Person(exchanger);
        OutputPerson outputPerson = new OutputPerson(exchanger);

    }
}
class Person extends Thread{
    Exchanger<String> exchanger;

    public Person(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        start();
    }

    @Override
    public void run() {
        try {
            exchanger.exchange("My name is Sasha");
            sleep(3000);
            exchanger.exchange("I am 20 years old");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class OutputPerson extends Thread{
    Exchanger<String> exchanger;

    public OutputPerson(Exchanger exchanger) {
        this.exchanger = exchanger;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println(exchanger.exchange(null));
            System.out.println(exchanger.exchange(null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}