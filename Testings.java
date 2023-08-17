import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Testings {
    public static Scanner scanner = new Scanner(System.in);

    public static int input(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static void a(Integer X) {}

    public static void main(String[] args) {

        Stack<Float> stack = new Stack<>(5);

        stack.add(new Random().nextFloat());
        stack.add(new Random().nextFloat());
        stack.add(new Random().nextFloat());


        stack.forEach((x, i) -> System.out.printf("indice %d: %f \n", i, x));

        stack.remove();
        stack.remove();
        
        stack.forEach((x, i) -> System.out.printf("indice %d: %f \n", i, x));

        
        Queue<Integer> queue = new Queue<>(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.forEach((x, i) -> System.out.printf("indice %d: %d\n", i, x));

        queue.dequeue();
        queue.enqueue(6);
        queue.dequeue();

        queue.forEach((x, i) -> System.out.printf("indice %d: %d\n", i, x));
        
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        System.out.println(queue.first());
        System.out.println(queue.last());


    }
}
