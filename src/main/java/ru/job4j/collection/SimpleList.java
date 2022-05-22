package ru.job4j.collection;

public interface SimpleList<T> extends Iterable<T> {
    void add(T value);
    T set(int value, T newValue);
    T remove(int index);
    T get(int index);
    int size();
}
