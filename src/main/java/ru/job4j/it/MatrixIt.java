package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    int column = -1;
    int row = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        if (column == data[row].length - 1 && data[row].length != 0) {
            column = -1;
            row++;
        }

        while (data[row].length == 0 && row < data.length - 1) {
            row++;
        }

        return !(row == data.length - 1 && column == data[row].length - 1);

    }

    @Override
    public Integer next() {
        if (hasNext()) {
            column++;
            return data[row][column];
        } else {
            throw new NoSuchElementException();
        }
    }

}
