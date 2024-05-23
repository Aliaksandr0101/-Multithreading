import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleWithSynchronizedCollection {
    public static void main(String[] args) {
        int n = 100;

     List<Integer> synchronizedListWithNumbers = Collections.synchronizedList(new ArrayList<>());
        synchronizedListWithNumbers.addAll(IntStream.range(0,n)
                                           .boxed()
                                            .collect(Collectors.toList()));

     synchronizedListWithNumbers.forEach(System.out::println);
    }
}
