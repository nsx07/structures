import java.util.Random;
import java.util.Scanner;

public class Testings {
    public static Scanner scanner = new Scanner(System.in);

    public static int input(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static void main(String[] args) {

        Stack<Float> stack = new Stack<>(5);

        stack.add(new Random().nextFloat());
        stack.add(new Random().nextFloat());
        stack.add(new Random().nextFloat());


        for (int i = 0; i < stack.stack.length; i++) {
            System.out.println(stack.stack[i]);
        }

        stack.remove();
        stack.remove();

        System.out.println();

        for (int i = 0; i < stack.stack.length; i++) {
            System.out.println(stack.stack[i]);
        }

        Queue<Float> queue = new Queue<>(5);

        queue.enqueue(new Random().nextFloat());
        queue.enqueue(new Random().nextFloat());
        queue.enqueue(new Random().nextFloat());
        queue.enqueue(new Random().nextFloat());


        for (int i = 0; i < queue.queue.length; i++) {
            System.out.println(queue.queue[i]);
        }


        queue.dequeue();
        queue.dequeue();

        queue.enqueue(new Random().nextFloat());

        System.out.println();

        for (int i = 0; i < stack.stack.length; i++) {
            System.out.println(stack.stack[i]);
        }




    }
}
