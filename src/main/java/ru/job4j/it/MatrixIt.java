package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    int column = 0;
    int row = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
         while (data[row].length == 0 || column > data[row].length - 1) {
             if (row == data.length - 1) {
                 break;
             }
             column = 0;
             row++;
        }
        return row <= data.length - 1 && data[row].length != 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

}
