package classes.Hash;

import classes.List;

import java.util.Random;

public abstract class AbstractHash<K, V, ChainingType> {

    protected final List<ChainingType> collection = new List<>();
    protected final float loadFactor = 0.75f;
    protected int maxSize = 16;

    public AbstractHash() {
        initCollection();
    }

    //#region 'Public & Abstract methods'

    public void print() {
        System.out.println();
        this.collection.forEach((el, index) -> System.out.println(index + " - " + el));
        System.out.println();
    }

    public void clear() {
        this.collection.clear();
        this.maxSize = 16;
        initCollection();
    }

    public abstract int add(K key, V value);

    public abstract V remove(K key);

    public abstract V search(K key);

    //#endregion

    //#region 'Hashing'

    protected int hash(HashNode<K, V> map) {
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
            sumChar += (int) c;
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

    //#region 'Private utils methods'

    protected void initCollection() {
        this.collection.fill(maxSize, null);
    }

    protected HashNode<K, V> getMap(K key) {
        return new HashNode<>(key, null);
    }

    //#endregion

}

enum NodeState {
    Empty, Unfilled, Collision, Filled
}

class HashNode<K, V> {
    public K key;
    public V value;

    public NodeState state;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public HashNode(K key, V value, NodeState state) {
        this.key = key;
        this.value = value;
        this.state = state;
    }

    public String toString() {
        return "HashNode{ " + "key = " + key + ", value = " + value + " }";
    }
}

