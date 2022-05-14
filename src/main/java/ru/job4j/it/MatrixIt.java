package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    int row = -1;
    int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
         for (int k = column; k < data.length; k++) {
            if (data[k].length != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            if (row < data[column].length - 1) {
                row++;
            } else {
                row = 0;
                column++;
            }
            while (data[column].length == 0) {
                column++;
            }
            return data[column][row];
        } else {
            throw new NoSuchElementException();
        }
    }

}