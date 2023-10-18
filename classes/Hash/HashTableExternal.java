package classes.Hash;

import classes.LinkedList;
import classes.List;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class HashTableExternal<K, V> extends AbstractHash<K, V, List<HashNode<K, V>>> {

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

            int sizeOfFilledIndex = this.collection.filter(Objects::nonNull).size();

            if (sizeOfFilledIndex >= this.maxSize * this.loadFactor) {
                duplicate();
            }

        } catch (Exception ignored) { }

        return index;
    }

    public V remove(K key) {
        int index = this.hash(getMap(key));

        try {

            List<HashNode<K, V>> position = this.collection.get(index);

            if (position != null && !position.isEmpty()) {
                short indexEl  = position.findIndex(x -> x.key.equals(key));

                if (indexEl >= 0) {
                    return position.remove(indexEl).value;
                }
            }

        } catch (Exception ignore) { }

        return null;
    }

    public V search(K key) {
        int index = this.hash(getMap(key));

        try {
            return this.collection.get(index).find(x -> x.key.equals(key)).value;
        } catch (Exception ignore) { }

        return null;
    }

    //#endregion

    //#region 'Private Methods'
    
    private void duplicate() {
        this.maxSize *= 2;
        LinkedList<List<HashNode<K, V>>> oldListKeys = collection.filter(Objects::nonNull);

        collection.clear();

        initCollection();

        oldListKeys.forEach(x -> {
            if (x != null) {
                x.forEach(y -> this.add(y.key, y.value));
            }
        });
    }

    //#endregion

    public static class Person {
        public int id;
        public String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        HashTableExternal<Integer, Person> hashT = new HashTableExternal<>();
        Scanner scan = new Scanner(System.in);
        int value = 0;

        while (value != 0x999) {
            value = scan.nextInt();
            switch (value) {
                case 0 -> {
                    int i = scan.nextInt();
                    System.out.println(hashT.add(i, new Person(i, UUID.randomUUID().toString())));
                }
                case 1 -> System.out.println(hashT.remove(scan.nextInt()));
                case 2 -> System.out.println(hashT.search(scan.nextInt()));
                case 3 -> hashT.clear();
                case 4 -> hashT.print();
            }

        }

        scan.close();

    }

}
