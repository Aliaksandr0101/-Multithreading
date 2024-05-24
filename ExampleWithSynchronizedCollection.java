import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleWithSynchronizedCollection {
    public static void main(String[] args) {
        int n = 100;

     List<Integer> synchronizedListWithNumbers = Collections.synchronizedList(new ArrayList<>());
        synchronizedListWithNumbers.addAll(IntStream.range(0,n)
                                           .boxed()
                                            .collect(Collectors.toList()));
        System.out.println("List before summation\n");
        synchronizedListWithNumbers.forEach(System.out::println);

        System.out.println("\nList after summation\n");

     ExecutorService poolThreeStreams = Executors.newFixedThreadPool(3);

        try {
            List<Integer> incrementedNumbers = poolThreeStreams.submit(() ->
                                               synchronizedListWithNumbers.parallelStream()
                                               .map(num -> num + 10)
                                               .collect(Collectors.toList()))
                                               .get();
            poolThreeStreams.shutdown();
            incrementedNumbers.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
}
