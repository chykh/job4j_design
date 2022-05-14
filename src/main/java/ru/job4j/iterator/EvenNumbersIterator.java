package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;
    boolean flag;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public Integer step() {
        flag = false;

        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                flag = true;
                return i;
            }
        }
        return index;
    }

    @Override
    public boolean hasNext() {
        step();
        return flag;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            index = step() + 1;
            return data[index - 1];
        } else {
            throw new NoSuchElementException();
        }
    }

}