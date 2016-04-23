package common.domain;

import java.util.ArrayList;

public class Matrix {
    ArrayList<Double>[] rows;

    public Matrix(int n, int m) {
        rows = new ArrayList[n];
    }
}
