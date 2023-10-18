package classes.Hash;

import classes.LinkedList;
import classes.List;

import java.util.Objects;

public class HashTableInternal<K, V> extends AbstractHash<K, V, HashNode<K, V>> {

    public int add(K key, V element) {
        return add(new HashNode<>(key, element, NodeState.Filled));
    }

    public V remove(K key) {
        int index = this.hash(getMap(key));

        try {

            HashNode<K, V> position = this.collection.get(index);

            if (position != null) {

                // must validate that the position have the same key
                if (position.key.equals(key)) {
                    position.key = null; position.value = null; position.state = NodeState.Unfilled;
                }

            }

        } catch (Exception ignore) { }

        return null;
    }

    public V search(K key) {
        int index = this.hash(getMap(key));

        try {
            return this.collection.get(index).value;
        } catch (Exception ignore) { }

        return null;
    }

    private int add(HashNode<K, V> map) {
        if (this.search(map.key) != null) {
            this.remove(map.key);
        }

        int index = this.hash(map);

        try {
            HashNode<K, V> position = this.collection.get(index);

            if (position != null) {
                position.state = NodeState.Collision;
                // find next null;
                int getNextIndex = find(index, map.key);

            } else {


                this.collection.set(map, index);
            }

            int sizeOfFilledIndex = this.collection.filter(x -> x.state != NodeState.Empty && x.state != NodeState.Unfilled).size();

            if (sizeOfFilledIndex >= this.maxSize * this.loadFactor) {
                duplicate();
            }

        } catch (Exception ignored) { }

        return index;
    }

    private int find(int index, K key) {

        for (int i = index; i < collection.size(); i++) {
            try {
                if (collection.get(i).key == key || collection.get(i).state == NodeState.Empty) {
                    return i;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

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
