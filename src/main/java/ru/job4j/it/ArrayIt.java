package ru.job4j.it;

import java.util.Iterator;

public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    public boolean hasNext() {
        return point < data.length;
    }

    public Integer next() {
        return data[point++];
    }
}
