package classes;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class HashTable<T> {

    private final List<List<T>> collection = new List<>();
    private final List<MemoryState> memory = new List<>();

    private ChainingType chaining = ChainingType.List;
    private final float loadFactor = 0.75f;
    private int maxSize = 16;

    public HashTable() {
        initCollection();
    }

    public HashTable(ChainingType chaining) {
        this.chaining = chaining;
        initCollection();
    }

    public int add(T element) {
        int index = hash(element);

        try {
            List<T> position = collection.get(index);

            if (position != null) {
                position.add(element);
                memory.set(MemoryState.Collision, index);
            } else {
                position = new List<>();
                position.add(element);
                collection.set(position, index);
                memory.set(MemoryState.Filled, index);
            }

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

            List<T> position = collection.get(index);

            if (position != null && !position.isEmpty()) {
                short indexEl  = position.findIndex(x -> x.equals(element));

                if (indexEl >= 0) {
                    return position.remove(indexEl).hashCode();
                }

            }

            collection.set(null, index);
            memory.set(MemoryState.Unfilled, index);

        } catch (Exception ignore) { }

        return index;
    }

    public T search(T element) {
        int index = hash(element);

        try {
            return collection.get(index).find(x -> x.equals(element));
        } catch (Exception ignore) { }

        return null;
    }

    public void clear() {
        collection.clear();
        memory.clear();
        maxSize = 16;
    }

    public void print() {
        collection.forEach((el, index) -> {
            System.out.println(index + " " + el);
        });
    }



    private int hash(T element) {
        String preKey = element.hashCode() + element.toString();
        Random random = new Random(maxSize);

        int a = random.nextInt(0, nextPrime(maxSize) -1);
        int b = random.nextInt(0, nextPrime(maxSize) -1);
        int p = nextPrime(nextPrime(maxSize));
        int k = preHash(preKey);

        return ((a * k + b) % p) % maxSize;
    }

    private int preHash(String key) {
        int sumChar = 0;
        char[] charArray = key.toCharArray();

        for (char c : charArray) {
            sumChar += (int) c ;
//            >> (int) Math.pow(key.indexOf(c), 2) / (sumChar + 1);
        }

        return Math.abs(sumChar);
    }

    private int nextPrime(int value) {
        while (true) {
            boolean prime = isPrime(++value);

            if (prime) {
                return value;
            }
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1){
            return false;
        }

        if (num == 2) {
            return true;
        }

        if (num % 2 == 0){
            return false;
        }

        int index = 3;
        while (index < num) {
            if (num % index == 0) {
                return false;
            }

            index += 3;
        }

        return true;
    }

    private void duplicate() {
        // TODO REFACTOR
        this.maxSize *= 2;
        var oldListKeys = collection.filter(Objects::nonNull);

        collection.clear();
        memory.clear();

        oldListKeys.forEach(x -> {
            if (x != null) x.forEach(this::add);
        });

        this.collection.fill(this.maxSize, null);
        this.memory.fill(this.maxSize, MemoryState.Empty);
    }

    private void initCollection() {
        this.collection.fill(maxSize, null);
        this.memory.fill(maxSize, MemoryState.Empty);
    }



    private enum MemoryState {
        Filled, Unfilled, Empty, Collision
    }

    public enum ChainingType {
        List, Tree, Hash
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
}
