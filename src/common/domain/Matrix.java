package common.domain;

import java.util.ArrayList;

public class Matrix {

    private ArrayList<Cell>[] rows;
    private int n;
    private int m;

    public Matrix(int n, int m) {
        rows = new ArrayList[n];
    }

    public double get(int i, int j) {
        if(i >= 0 && i < n && j > 0 && j < m) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getRows() {
        return n;
    }

    public int getColumns() {
        return m;
    }


    private class Cell {

        private int column;
        private double value;

        Cell(int column, double value) {
            this.column = column;
            this.value = value;
        }
    }
}
