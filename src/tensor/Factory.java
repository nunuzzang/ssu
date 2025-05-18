package tensor;

import java.io.IOException;

public class Factory {

    // Scalar 생성
    //01
    public static Scalar createScalar(String val) {
        return new ScalarImpl(val);
    }

    //02
    public static Scalar createScalar(double i, double j) {
        return new ScalarImpl(i, j);
    }

    // Vector 생성
    //03
    public static Vector createVector(int n, Scalar val) {
        return new VectorImpl(n, val);
    }

    //04
    public static Vector createVector(double i, double j, int n) {
        return new VectorImpl(i, j, n);
    }

    //05
    public static Vector createVector(Scalar[] arr) {
        return new VectorImpl(arr);
    }

    // Matrix 생성
    //06
    public static Matrix createMatrix(int m, int n, Scalar val) {
        return new MatrixImpl(m, n, val);
    }

    //07
    public static Matrix createMatrix(int i, int j, int m, int n) {
        return new MatrixImpl(i, j, m, n);
    }

    //08
    public static Matrix createMatrix(String csvPath) throws IOException {
        return new MatrixImpl(csvPath);
    }

    //09
    public static Matrix createMatrix(Scalar[][] arr) {
        return new MatrixImpl(arr);
    }

    //10
    public static Matrix createIdentityMatrix(int size) {
        return new MatrixImpl(size);
    }


}