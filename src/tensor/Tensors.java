package tensor;

public class Tensors {
    // 스칼라의 연산
    //24
    public static Scalar add(Scalar s1, Scalar s2) {
        return ScalarImpl.add(s1, s2);
    }

    //25
    public static Scalar multiply(Scalar s1, Scalar s2) {
        return ScalarImpl.multiply(s1, s2);
    }

    //벡터의 연산
    //26
    public static Vector add(Vector v1, Vector v2) { // 26 [cite: 28]
        return VectorImpl.add(v1, v2);
    }

    //27
    public static Vector multiply(Vector v, Scalar s) { // 27 [cite: 28]
        return VectorImpl.multiply(v, s);
    }

    //행렬의 연산
    //28
    public static Matrix add(Matrix m1, Matrix m2) {
        return MatrixImpl.add(m1, m2);
    }

    //29
    public static Matrix multiply(Matrix m1, Matrix m2) {
        return MatrixImpl.multiply(m1, m2);
    }

    //32
    public static Matrix attachHMatrix(Matrix m1, Matrix m2) {
        return MatrixImpl.attachHMatrix(m1, m2);
    }

    //33
    public static Matrix attachVMatrix(Matrix m1, Matrix m2) {
        return MatrixImpl.attachVMatrix(m1, m2);
    }
}