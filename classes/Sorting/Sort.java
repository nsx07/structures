package classes.Sorting;

import java.util.Arrays;

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
        return Bubble(values, 1);
    }

    //#endregion

    //#region Insertion

    public static int[] Insertion(int[] values) {
        int temp;

        for (int i = 1; i < values.length - 1; i++) {

            if (values[i] > values[i + 1]) {
                var a = values[i + 1];
                values[i + 1] = values[i];
                values[i] = a;
            } else {

                for (int j = i; j > 0; j--) {
                    if (values[j - 1] > values[j]) {
                        temp = values[j];
                        values[j] = values[j - 1];
                        values[j - 1] = temp;
                        break;
                    }
                }
                
            }
        }

        return values;
    }

    //#endregion

    //#region 'QuickSort'

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

    private static void Swap(int[] v, int from, int to) {
        int temp = v[from];
        v[from] = v[to];
        v[to] = temp;
    }

    private static int Partition(int[] values, int left, int right) {
        
        int i = (left - 1);
        int pivot = values[right];
 
        for (int j = left; j <= right - 1; j++) {
            if (values[j] < pivot) {
                i++;
                Swap(values, i, j);
            }
        }

        Swap(values, i + 1, right);

        return (i + 1);
    }

    //#endregion

    public static void main(String[] args) {
        int[] a = new int[] { 49, 15, 40, 27, 20, 19, 50, 2, 12, 28, 49, 44, 9, 59, 18, 5, 30, 6, 7, 28 } ;

       int[] insertion = Insertion(Arrays.copyOf(a, a.length));
    //    int[] bubble = Bubble(Arrays.copyOf(a, a.length));
    //    int[] quick = Quick(Arrays.copyOf(a, a.length));


    //    System.out.println("\nBubble Sort\n");
    //    for (int bi : bubble) {
    //         System.out.println(bi);
    //    }

       System.out.println("\nInsertion Sort\n");
       for (int j : insertion) {
           System.out.println(j);
       }


        // System.out.println("\nQuick Sort\n");
        // for (int j : quick) {
        //     System.out.println(j);
        // }
    }

}
