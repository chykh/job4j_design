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
            MapEntry<K, V>[] temp = new MapEntry[capacity];
            System.arraycopy(table, 0, temp, 0, temp.length);
            capacity *= 1.5;
            table = new MapEntry[capacity];
            int tempCount = count;
            int tempModCount = modCount;
            for (MapEntry<K, V> entry : temp) {
                put(entry.key, entry.value);
            }
            count = tempCount;
            modCount = tempModCount;
        }
    }

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = indexFor(hash(key.hashCode()));
        boolean valid = table[index] == null;
        if (valid) {
            count++;
            modCount++;
            table[index] = new MapEntry<>(key, value);
        }
        return valid;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return  key.equals(table[index].key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean valid = table[index] != null && table[index].key == key;
        if (valid) {
            table[index] = null;
            count--;
            modCount++;
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
                while (i < capacity && table[i] == null) {
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
