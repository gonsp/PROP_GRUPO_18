package common.domain;

import javafx.util.Pair;

import java.util.ArrayList;

public class Matrix {

    private ArrayList<Cell>[] rows;
    private double[] euclidean;
    private int n;
    private int m;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        rows = new ArrayList[n];
        for(int i = 0; i < n; ++i) {
            rows[i] = new ArrayList<Cell>();
        }
        euclidean = new double[m];
        for(int j = 0; j < m; ++j) {
            euclidean[j] = 0;
        }
    }

    public double get(int i, int j) {
        if(checkPos(i, j)) {
            Pair<Boolean, Integer> res = binarySearch(rows[i], j);
            if(!res.getKey()) {
                return 0;
            } else {
                return rows[i].get(res.getValue()).value;
            }
        } else {
            return -1;
        }
    }

    public void put(int i, int j, double value) {
        if(checkPos(i, j) && value != 0) {
            double prev = get(i, j);
            if(prev != 0) {
                euclidean[j] -= prev * prev;
            }
            ArrayList<Cell> row = rows[i];
            Pair<Boolean, Integer> res = binarySearch(row, j);
            if(!res.getKey()) {
                row.add(res.getValue(), new Cell(j, value));
            } else {
                row.get(res.getValue()).value = value;
            }
            if(value != 0) {
                euclidean[j] += value*value;
            }
        }
    }

    private Matrix i_mul(Matrix mat, boolean normalize) {
        if(this.m != mat.n) {
            return null;
        }
        Matrix res = new Matrix(this.n, mat.m);
        for(int i = 0; i < this.n; ++i) {
            for(int j = 0; j < mat.m; ++j) {
                double value = 0;
                double euclideanA = 0;
                for(int k = 0; k < this.rows[i].size(); ++k) {
                    Cell aux = this.rows[i].get(k);
                    value += aux.value * mat.get(aux.column, j);
                    euclideanA += aux.value * aux.value;
                }
                euclideanA = Math.sqrt(euclideanA);
                double euclideanB = Math.sqrt(mat.getEuclidean(j));
                if(normalize) {
                    value = value/(euclideanA * euclideanB);
                }
                res.put(i, j, value);
            }
        }
        return res;
    }

    private double getEuclidean(int j) {
        return euclidean[j];
    }

    public Matrix mul(Matrix mat) {
        return i_mul(mat, false);
    }

    public Matrix mul_norm(Matrix mat) {
        return i_mul(mat, true);
    }

    public int getRows() {
        return n;
    }

    public int getColumns() {
        return m;
    }

    private Pair<Boolean, Integer> i_binarySearch(ArrayList<Cell> row, int l, int r, int j) {
        if(l > r) {
            return new Pair<Boolean, Integer>(false, l);
        } else {
            int m = (l+r)/2;
            if(row.get(m).column < j) {
                return i_binarySearch(row, l, m-1, j);
            } else if(row.get(m).column > j) {
                return i_binarySearch(row, m+1, r, j);
            } else {
                return new Pair<Boolean, Integer>(true, m);
            }
        }
    }

    private Pair<Boolean, Integer> binarySearch(ArrayList<Cell> row, int j) {
        return i_binarySearch(row, 0, row.size()-1, j);
    }

    private boolean checkPos(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
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
