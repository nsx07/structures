package classes.Sorting;

import java.time.Duration;
import java.time.Instant;

public class Sort {
    
    private static int[] Bubble(int[] values, int skip) {
        if (skip == values.length) {
            return values;
        }
        
        int temp;

        for (int i = 0; i < values.length - skip; i++) {
            if (values[i] > values[i + 1]) {
                temp = values[i];
                values[i] = values[i + 1];
                values[i + 1] = temp;
            }
        }
        
        return Bubble(values, ++skip);
    }

    public static int[] Bubble(int[] values) {
        Timer timer = new Timer();
        timer.start();
        int[] bubble = Bubble(values, 1);
        System.out.println("Bubble: " + timer.elapsed());
        return bubble;
    }

    public static int[] Insertion(int[] values) {
        int temp;

        Timer timer = new Timer();
        timer.start();
        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0; j--) {
                if (values[j - 1] > values[j]) {
                    temp = values[j];
                    values[j] = values[j - 1];
                    values[j - 1] = temp;
                    break;
                }
            }
        }

        System.out.println("Insertion: " + timer.elapsed());

        return values;

    }
    private static class Timer {
        private Instant start;

        public void start() {
            this.start = Instant.now();

        }

        public long elapsed() {
            return Duration.between(start, Instant.now()).getNano();
        }
    }


    public static void main(String[] args) {
        int[] a = new int[] {2,8,12,7,1};
        int[] copy = new int[] {2,8,12,7,1};
        int[] bubble = Bubble(a);
        int[] insertion = Insertion(copy);

        System.out.println("\n Bubble Sort");
        for (int bi : bubble) {
             System.out.println(bi);
        }

        System.out.println("\n Insertion Sort");
        for (int j : insertion) {
            System.out.println(j);
        }
    }

}
