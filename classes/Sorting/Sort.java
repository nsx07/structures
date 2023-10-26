package classes.Sorting;

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
        return Bubble(values, 1);
    }

    public static int[] Insertion(int[] values) {
        int temp;

        for (int i = 1; i < values.length; i++) {
            if (values[i] < values[i - 1]) {
                temp = values[i];
                values[i] = values[i - 1];
                values[i - 1] = temp;
            }
        }
        
        
        return values;
    }


    public static void main(String[] args) {
        int[] a = new int[] {2,8,12,7,1};
        // int[] b = Bubble(a);
        int[] i = Insertion(a);
        
        // for (int bi : b) {
        //     System.out.println(bi);
        // }

        for (int j : i) {
            System.out.println(j);
        }
    }

}
