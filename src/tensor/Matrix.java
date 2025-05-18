package tensor;

public interface Matrix {
    //11
    void setValue(int row, int col, Scalar val);
    Scalar getValue(int row, int col);
    //13
    int rowSize();
    int colSize();
    //14
    String toString();
    //15
    boolean equals(Object obj);
    //17
    Matrix clone();
    //22
    Matrix add(Matrix other);
    //23
    Matrix multiply(Matrix other);
    //28
    static Matrix add(Matrix m1, Matrix m2){
        Matrix result = m1.clone();
        return result.add(m2);
    }
    //29
    static Matrix multiply(Matrix m1, Matrix m2){
        Matrix result = m1.clone();
        return result.multiply(m2);
    }

    //32
    static Matrix attachHMatrix(Matrix m1, Matrix m2){
        if (m1.rowSize() != m2.rowSize()){
            return null;//예외 처리하기
        }
        int newRows = m1.rowSize();
        int newCols = m1.colSize() + m2.colSize();
        Scalar[][] newElements = new Scalar[newRows][newCols];

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < m1.colSize(); j++) {
                Scalar currentScalar = m1.getValue(i, j);
                if (currentScalar != null) {
                    newElements[i][j] = currentScalar.clone();
                } else {
                    newElements[i][j] = null;
                }
            }
        }

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < m2.colSize(); j++) {
                Scalar otherScalar = m2.getValue(i, j);
                if (otherScalar != null) {
                    newElements[i][m1.colSize() + j] = otherScalar.clone();
                } else {
                    newElements[i][m1.colSize() + j] = null;
                }
            }
        }
        return new MatrixImpl(newElements);
    }

    //33
    static Matrix attachVMatrix(Matrix m1, Matrix m2){
        if (m1.colSize() != m2.colSize()) {
            return null;//예외 처리하기
        }
        int newRows = m1.rowSize() + m2.rowSize();
        int newCols = m1.colSize();
        Scalar[][] newElements = new Scalar[newRows][newCols];

        for (int i = 0; i < m1.rowSize(); i++) {
            for (int j = 0; j < newCols; j++) {
                Scalar currentScalar = m1.getValue(i, j);
                if (currentScalar != null) {
                    newElements[i][j] = currentScalar.clone();
                } else {
                    newElements[i][j] = null;
                }
            }
        }

        for (int i = 0; i < m2.rowSize(); i++) {
            for (int j = 0; j < newCols; j++) {
                Scalar otherScalar = m2.getValue(i, j);
                if (otherScalar != null) {
                    newElements[m1.rowSize() + i][j] = otherScalar.clone();
                } else {
                    newElements[m1.rowSize() + i][j] = null;
                }
            }
        }
        return new MatrixImpl(newElements);
    }

    //34
    Vector getRowVector (int index);

    //35
    Vector getColVector(int index);

    //36
    Matrix extractSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd);

    //37
    Matrix minorSubMatrix (int row, int col);

    //38
    Matrix transposeMatrix();

    //39
    Scalar trace();

    //40~44
    boolean isSquare();
    boolean isUpperTriangularMatrix();
    boolean isLowerTriangularMatrix();
    boolean isIdentity();
    boolean isZero();

    //45
    Matrix rowSwap(int row1, int row2);

    //46
    Matrix colSwap(int col1, int col2);

    //47
    Matrix rowMultiply(int index, Scalar val);

    //48
    Matrix colMultiply(int index, Scalar val);
}
