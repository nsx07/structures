package classes.Hash;

import classes.LinkedList;
import classes.List;

import java.util.Objects;

public class HashTableInternal<K, V> extends AbstractHash<K, V, HashNode<K, V>> {

    public int add(K key, V element) {
        return add(new HashNode<>(key, element));
    }

    public V remove(K key) {
        int index = this.hash(getMap(key));

        try {

            HashNode<K, V> position = this.collection.get(index);

            if (position.key != null) {

                if (position.key.equals(key)) {
                    V value = position.value;
                    position.key = null; position.value = null; position.state = NodeState.Unfilled;
                    sizeKeys--;

                    return value;
                } else {
                    int nextIndex = find(index, key, true);

                    if (nextIndex > 0) {
                        position = collection.get(nextIndex);
                        position.key = null; position.value = null; position.state = NodeState.Unfilled;
                        sizeKeys--;

                        return this.collection.get(nextIndex).value;
                    }
                }

            }

        } catch (Exception ignore) { }

        return null;
    }

    public V search(K key) {
        int index = this.hash(getMap(key));

        try {

            HashNode<K, V> node = this.collection.get(index);

            if (node.key != null && node.key.equals(key)) {
                return node.value;
            } else {
                int nextIndex = find(index, key, true);

                if (nextIndex > 0) {
                    return this.collection.get(nextIndex).value;
                }
            }

        } catch (Exception ignore) { }

        return null;
    }

    private int add(HashNode<K, V> map) {
        int index = this.hash(map);

        try {
            HashNode<K, V> position = this.collection.get(index);

            if (position.key != null) {

                if (position.key == map.key) {
                    map.state = NodeState.Filled;
                    collection.set(map, index);
                    return index;

                }

                map.state = NodeState.Collision;
                index = find(index, map.key);
            } else {
                map.state = NodeState.Filled;
            }

            this.collection.set(map, index);
            sizeKeys++;

            int sizeOfFilledIndex = this.collection.filter(x -> x.state != NodeState.Empty && x.state != NodeState.Unfilled).size();

            if (sizeOfFilledIndex >= this.maxSize * this.loadFactor) {
                duplicate();
            }

        } catch (Exception ignored) { }

        return index;
    }

    private int find(int index, K key) {
        return find(index, key, false);
    }

    private int find(int index, K key, boolean matchOnlyByKey) {
        int i = index;

        do {
            try {
                if (collection.get(i).key == key || (!matchOnlyByKey && collection.get(i).state == NodeState.Empty)) {
                    return i;
                }
            } catch (Exception ignored) { }

            i = i >= (collection.size() - 1) ? 0 : i + 1;

        } while (i != index);

        return -1;
    }

    protected void initCollection() {
        collection.fill(maxSize, new HashNode<>(null, null, NodeState.Empty));
    }

    private void duplicate() {
        this.maxSize *= 2;
        LinkedList<HashNode<K, V>> oldListKeys = collection.filter(Objects::nonNull);

        collection.clear();

        initCollection();

        oldListKeys.forEach(x -> {
            if (x != null) {
                this.add(new HashNode<>(x.key, x.value, x.state));
            }
        });
    }



}
