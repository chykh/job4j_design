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
         for (int k = i; k < data.length; k++) {
            if (data[k].length != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            if (j < data[i].length - 1) {
                j++;
            } else {
                j = 0;
                i++;
            }
            while (data[i].length == 0) {
                i++;
            }
            return data[i][j];
        } else {
            throw new NoSuchElementException();
        }
    }

}