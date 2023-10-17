package classes;

import java.util.Random;

public abstract class AbstractHash<K, V, C> {

    final List<C> collection = new List<>();
    final float loadFactor = 0.75f;
    int maxSize = 16;

    //#region 'Hashing'

    int hash(HashTable.HashNode<K, V> map) {
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
            sumChar += (int) c >>> (sumChar >> 5) + sumChar;
        }

        return Math.abs(sumChar);
    }

    //#endregion

    //#region 'Primes'

    private int nextPrime(int value) {
        for (;;) {
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

    void initCollection() {
        this.collection.fill(maxSize, null);
    }




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

}
