package classes;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class HashTable<K, V> {

    private final List<List<HashNode<K, V>>> collection = new List<>();
    private final float loadFactor = 0.75f;
    private int maxSize = 16;

    public HashTable() {
        initCollection();
    }

    //#region 'Public Methods'

    public int add(K key, V element) {
        HashNode<K, V> map = new HashNode<>(key, element);

        if (this.search(map.key) != null) {
            this.remove(map.key);
        }

        int index = this.hash(map);

        try {
            List<HashNode<K, V>> position = this.collection.get(index);

            if (position != null) {
                position.add(map);
            } else {
                position = new List<>();
                position.add(map);
                this.collection.set(position, index);
            }

            int sizeOfFilledIndex = this.collection.filter(Objects::nonNull).length;

            if (sizeOfFilledIndex >= this.maxSize * this.loadFactor) {
                duplicate();
            }

        } catch (Exception ignored) { }

        return index;
    }

    public int remove(K key) {
        int index = this.hash(getMap(key, null));

        try {

            List<HashNode<K, V>> position = this.collection.get(index);

            if (position != null && !position.isEmpty()) {
                short indexEl  = position.findIndex(x -> x.key.equals(key));

                if (indexEl >= 0) {
                    return position.remove(indexEl).hashCode();
                }
            }

        } catch (Exception ignore) { }

        return index;
    }

    public V search(K key) {
        int index = this.hash(getMap(key, null));

        try {
            return this.collection.get(index).find(x -> x.key.equals(key)).value;
        } catch (Exception ignore) { }

        return null;
    }

    public void clear() {
        this.collection.clear();
        this.maxSize = 16;
    }

    public void print() {
        this.collection.forEach((el, index) -> {
            System.out.println(index + " - " + el);
        });
    }

    //#endregion

    //#region 'Private Methods'

    private HashNode<K, V> getMap(K key, V value) {
        return new HashNode<>(key, value);
    }

    private void initCollection() {
        this.collection.fill(maxSize, null);
    }

    
    private void duplicate() {
        this.maxSize *= 2;
        var oldListKeys = collection.filter(Objects::nonNull);

        collection.clear();

        initCollection();

        oldListKeys.forEach(x -> {
            if (x != null) {
                x.forEach(y -> this.add(y.key, y.value));
            }
        });
    }

    //#endregion

    //#region 'Hashing'

    private int hash(HashNode<K, V> map) {
        String preKey = map.key.hashCode() + map.key.toString();
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
            sumChar += (int) c >> (int) Math.pow(key.indexOf(c) + 1, 2) / (sumChar + 1);
            System.out.printf("%d %d %d \n",(int) c, (int) Math.pow(key.indexOf(c) + 1, 2), (sumChar + 1));
        }

        return Math.abs(sumChar);
    }

    //#endregion

    //#region 'Primes'

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

    //#endregion

    public static class HashNode<K, V> {
        public K key;
        public V value;
        public String hash;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
        return "HashNode{ " + "key = " + key + ", value = " + value + " }";
        }
    }

    public static class Person {
        public int id;
        public String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        HashTable<Integer, Person> hashT = new HashTable<>();
        Scanner scan = new Scanner(System.in);
        int value = 0;

        while (value != 0x999) {
            value = scan.nextInt();
            switch (value) {
                case 0 -> {
                    int i = scan.nextInt();
                    System.out.println(hashT.add(i, new Person(i, UUID.randomUUID().toString())));
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

        scan.close();

    }

}
