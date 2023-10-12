package classes;

import java.util.Objects;
import java.util.Scanner;

public class HashTable<T> {

    private final List<T> collection = new List<>();
    private final List<MemoryState> memory = new List<>();

    private final float loadFactor = 0.75f;
    private int maxSize = 16;

    public HashTable() {
        this.collection.fill(maxSize, null);
        this.memory.fill(maxSize, MemoryState.Empty);
    }

    private int hash(T element) {

        var preKey = element.hashCode() + element.toString();

        return getChar(preKey) % maxSize;
    }

    private int getChar(String key) {
        int sumChar = 0;
        char[] charArray = key.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int ascii = (int) charArray[i];
            sumChar += (int) Math.pow(i, charArray.length - 1) * ascii;
        }

        return Math.abs(sumChar);
    }

    private int currentPrime() {
        int[] primes = new int[]{17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};

        int numOfDuplication = maxSize / 16 - 1;

        return numOfDuplication >= primes.length
                ? primes[0]
                : primes[numOfDuplication];
    }

    public int add(T element) {
        int index = hash(element);

        try {
            collection.set(element, index);
            memory.set(MemoryState.Filled, index);

            int sizeOfFilledIndex = collection.filter(Objects::nonNull).length;

            if (sizeOfFilledIndex >= maxSize * loadFactor) {
                duplicate();
            }

        } catch (Exception ignored) { }

        return index;
    }

    public int remove(T element) {
        int index = hash(element);

        try {
            collection.set(null, index);
            memory.set(MemoryState.Unfilled, index);
        } catch (Exception ignore) { }

        return index;
    }

    public boolean search(T element) {
        int index = hash(element);

        try {
            T elementSearch = collection.get(index);

            return elementSearch != null;

        } catch (Exception ignore) { }

        return false;
    }

    public void clear() {
        collection.clear();
        maxSize = 16;
    }

    public void print() {
        collection.forEach((el, index) -> {
            System.out.println(index + " " + el);
        });
    }

    private void duplicate() {
        this.maxSize *= 2;
        this.collection.fill(16, null);
        this.memory.fill(16, MemoryState.Empty);
    }

    public static void main(String[] args) {
        HashTable<Integer> hashT = new HashTable<>();
        Scanner scan = new Scanner(System.in);
        int value = 0;

        while (value != 0x999) {
            value = scan.nextInt();
            switch (value) {
                case 0 -> {
                    System.out.println(hashT.add(scan.nextInt()));
                }
                case 1 -> {
                    System.out.println(hashT.remove(scan.nextInt()));
                }
                case 2 -> {
                    System.out.println(hashT.search(scan.nextInt()));
                }
                case 3 -> {
                    hashT.clear();
                }
                case 4 -> {
                    hashT.print();
                }
            }

        }

    }

    public enum MemoryState {
        Filled, Unfilled, Empty, Collision
    }
}
