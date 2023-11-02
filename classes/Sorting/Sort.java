package classes.Sorting;

import java.util.Arrays;

public class Sort {

    //#region Bubble

    private static int[] Bubble(int[] values, int skip) {
        if (skip == values.length) {
            return values;
        }
        
        for (int i = 0; i < values.length - skip; i++) {
            if (values[i] > values[i + 1]) {
                Swap(values, i, i + 1, SortType.Bubble);
            }
        }
        
        return Bubble(values, ++skip);
    }

    public static int[] Bubble(int[] values) {
        return Bubble(values, 1);
    }

    //#endregion

    //#region Insertion

    public static int[] Insertion(int[] values) {
        for (int i = 1; i < values.length; i++) {

            if (values[i - 1] > values[i]) {
                Swap(values, i - 1, i, SortType.Insertion);

                for (int j = i; j > 0; j--) {

                    if (values[j - 1] >= values[j]) {
                        Swap(values, j - 1, j, SortType.Insertion);
                    }
                }
            }
        }

        return values;
    }

    //#endregion

    //#region QuickSort

    public static int[] Quick(int[] values) {
        return Quick(values, 0, values.length - 1);    
    }

    private static int[] Quick(int[] values, int left, int right) {
        if (left < right) {
            int pivot = Partition(values, left, right);
            Quick(values, left, pivot - 1);
            Quick(values, pivot + 1, right);
        }
        return values;
    }

    private static int Partition(int[] values, int left, int right) {
        
        int i = (left - 1);
        int pivot = values[right];
 
        for (int j = left; j <= right - 1; j++) {
            if (values[j] < pivot) {
                i++;
                Swap(values, i, j, SortType.Quick);
            }
        }

        Swap(values, i + 1, right, SortType.Quick);

        return (i + 1);
    }

    //#endregion

    private static void Swap(int[] v, int from, int to, SortType sortType) {
        int temp = v[from];
        v[from] = v[to];
        v[to] = temp;

        switch (sortType) {
            case Bubble -> Swaps.bubble++;
            case Insertion -> Swaps.insertion++;
            case Quick -> Swaps.quick++;
        }

        int sw = sortType == SortType.Bubble
                ? Swaps.bubble : sortType == SortType.Insertion
                                ? Swaps.insertion : Swaps.quick;

        System.out.printf("Swap %d -> ", sw);
        print(v, from, to);
    }
    public synchronized static void print(int[] values, int ... args) {
        System.out.print("[ ");
        for (int i = 0; i < values.length; i++) {

            if (args.length > 0) {
                if (i == args[0]) {
                    System.out.print(ANSI_BLUE + values[i] + ANSI_RESET + " ");
                    continue;
                }
                if (i == args[1]) {
                    System.out.print(ANSI_YELLOW + values[i] + ANSI_RESET + " ");
                    continue;
                }
            }

            System.out.print(values[i] + " ");
        }
        System.out.println("]");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        int[] a = new int[] { 49, 15, 40, 27, 20, 19, 50, 2, 12, 28, 49, 44, 9, 59, 18, 5, 30, 6, 7, 28 } ;
        System.out.println("Original Array");
        print(a);

        System.out.println("Bubble Sort");
        Bubble(Arrays.copyOf(a, a.length));

        System.out.println("\n\nInsertion Sort");
        Insertion(Arrays.copyOf(a, a.length));

        System.out.println("\n\nQuick Sort");
        Quick(Arrays.copyOf(a, a.length));

    }

    public record Swaps() {
        static int bubble;
        static int insertion;
        static int quick;
    }

    public enum SortType {
        Bubble,
        Insertion,
        Quick
    }

}
