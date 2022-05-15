package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;
    private int temp;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;

        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                temp = i;
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            index = temp;
            return data[index++];
    }

}