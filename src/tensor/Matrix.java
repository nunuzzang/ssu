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
    void add(Matrix other);
    //23
    void multiply(Matrix other);
    //28
    static Matrix add(Matrix m1, Matrix m2){
        Matrix result = m1.clone();
        result.add(m2);
        return result;
    }
    //29
    static Matrix multiply(Matrix m1, Matrix m2){
        Matrix result = m1.clone();
        result.multiply(m2);
        return result;
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
    Vector getRowVector (int row);

    //35
    Vector getColVector(int col);

    //36
    Matrix extractSubMatrix(int startRow, int endRow, int startCol, int endCol);

    //37
    Matrix minorSubMatrix (int row, int col);

    //38
    Matrix transposeMatrix();

    //39
    Scalar trace();

    //40~44
    boolean isSquare();
    boolean isUpperTriangular();
    boolean isLowerTriangular();
    boolean isIdentity();
    boolean isZero();

    //45
    void rowSwap(int row1, int row2);

    //46
    void colSwap(int col1, int col2);

    //47
    void rowMultiply(int row, Scalar val);

    //48
    void colMultiply(int col, Scalar val);

    //49
    void rowAddOtherRow(int targetRow, int sourceRow, Scalar val);

    //50
    void colAddOtherCol(int targetCol, int sourceCol, Scalar val);

    //51
    Matrix getRREF();

    //52
    Matrix isRREF();

    //53
    Scalar getMatrix();

    //54
    Matrix getInverseMatrix();
}
