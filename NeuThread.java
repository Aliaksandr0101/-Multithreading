import java.util.stream.IntStream;

public class NeuThread  extends Thread{
    private String symbol;

    public NeuThread(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        IntStream.range(0,100).forEach(i -> System.out.println(i + " " + symbol));
    }

    public static void main(String[] args) {
        NeuThread neuThread = new NeuThread("*G");
        neuThread.start();

    }
}
