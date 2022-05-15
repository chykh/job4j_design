package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    int j = -1;
    int i = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        if (j == data[i].length - 1 && data[i].length != 0) { //шаг
            j = -1;
            i++;
        }

        while (data[i].length == 0 && i < data.length - 1) { //проскок null
            i++;
        }

        return !(i == data.length - 1 && j == data[i].length - 1);

    }

    @Override
    public Integer next() {
        if (hasNext()) {
            j++;
            return data[i][j];
        } else {
            throw new NoSuchElementException();
        }
    }

}
