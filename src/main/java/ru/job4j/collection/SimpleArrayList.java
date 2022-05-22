package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size > container.length - 1) {
            container = grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        modCount++;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] grow() {
        T[] newContainer = container.length == 0
                ? (T[]) new Object[1] : (T[]) new Object[container.length * 2];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        return newContainer;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int step;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return step < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[step++];
            }
        };

    }
}