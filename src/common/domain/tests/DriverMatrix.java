package common.domain.tests;

import java.util.Scanner;
import common.domain.Matrix;

public class DriverMatrix {

    public DriverMatrix() {

    }

    public void main() {
        System.out.println("Matrix class test. This test performs Matrix multiplication.");
        System.out.println("Insert #rows of the first matrix or -1 to leave:");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        while(n >= 0) {
            System.out.println("Insert #cols of the first matrix:");
            int m = s.nextInt();
            Matrix m1 = new Matrix(n, m);
            System.out.println("Insert the values of the first matrix:");
            for(int i = 0; i < n; ++i) for(int j = 0; j < m; ++j) m1.put(i, j, s.nextDouble());
            System.out.println("Insert #rows of the second matrix:");
            n = s.nextInt();
            System.out.println("Insert #cols of the second matrix:");
            m = s.nextInt();
            Matrix m2 = new Matrix(n, m);
            System.out.println("Insert the values of the second matrix:");
            for(int i = 0; i < n; ++i) for(int j = 0; j < m; ++j) m2.put(i, j, s.nextDouble());
            System.out.println("Result:");
            m1.mul(m2);
            for(int i = 0; i < m1.getRows(); ++i) {
                for (int j = 0; j < m1.getColumns(); ++j) System.out.print(m1.get(i, j) + " ");
                System.out.print("\n");
            }
            System.out.println("Insert #rows of the first matrix or -1 to leave:");
            n = s.nextInt();
        }
    }


}
