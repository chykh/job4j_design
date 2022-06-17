package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            capacity *= 1.5;
        }
    }

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        boolean valid = table[index] == null;
        if (valid) {
            count++;
            expand();
            table[index] = new MapEntry<>(key, value);
        }
        return valid;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> mapEntry = table[index];
        return mapEntry.value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean valid = table[index] != null;
        if (valid) {
            table[index] = null;
            count--;
        }
        return valid;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int i;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[i].key == null) {
                    i++;
                }
                return i < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };

    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
