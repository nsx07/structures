package classes.Sorting;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Sort {

    //#region Bubble
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

    //#endregion

    //#region Insertion

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

    //#endregion

    //#region 'QuickSort'

    public static int[] Quicksort(int[] values) {
        Timer timer = new Timer();
        timer.start();
        int[] quick = Quicksort(values, 0, values.length - 1);
        System.out.println("Quicksort: " + timer.elapsed());
        return quick;
    }

    private static int[] Quicksort(int[] values, int left, int right) {
        if (left < right) {
            int pivot = Partition(values, left, right);
            Quicksort(values, left, pivot - 1);
            Quicksort(values, pivot + 1, right);
        }
        return values;
    }

    private static int Partition(int[] values, int left, int right) {
        int pivot = values[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (values[i] < pivot) {
                i++;
            }
            while (values[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = values[i];
                values[i] = values[j];
                values[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    //#endregion

    //#region Timer

    private static class Timer {
        private Instant start;

        public void start() {
            this.start = Instant.now();

        }

        public long elapsed() {
            return Duration.between(start, Instant.now()).getNano();
        }
    }

    //#endregion


    public static int[] randomArray(int size) {
        int[] largeArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(100000);
        }
        return largeArray;
    }

    public static void main(String[] args) {
        int[] a = randomArray(12);
//
//        int[] insertion = Insertion(Arrays.copyOf(a, a.length));
//        int[] bubble = Bubble(Arrays.copyOf(a, a.length));
        int[] quick = Quicksort(Arrays.copyOf(a, a.length));


//        System.out.println("\n Bubble Sort");
//        for (int bi : bubble) {
//             System.out.println(bi);
//        }
//
//        System.out.println("\n Insertion Sort");
//        for (int j : insertion) {
//            System.out.println(j);
//        }
//
//
        System.out.println("\n Quick Sort");
        for (int j : quick) {
            System.out.println(j);
        }
    }

}
