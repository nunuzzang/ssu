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
    //34
    Vector getRowVector (int row);
    //35
    Vector getColVector(int col);
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
    boolean isRREF();
    //53
    Scalar getDeterminant();
    //54
    Matrix getInverseMatrix();
}
