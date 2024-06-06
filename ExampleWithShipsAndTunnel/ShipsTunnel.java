package ExampleWithShipsAndTunnel;

import java.util.Random;
import java.util.stream.Stream;

public class ShipsTunnel {


    public static void main(String[] args) {

        int[] numbers = {10, 20, 50};
        Random random = new Random();
        System.out.println();
        System.out.println();
        System.out.println();

        // Генерируем случайный индекс для выбора одного из чисел
        int randomIndex = random.nextInt(numbers.length);

        // Получаем случайное число
        int randomNumber = numbers[randomIndex];

        // Выводим случайное число
        System.out.println("Generated number: " + randomNumber);

    }
}
