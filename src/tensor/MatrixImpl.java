package tensor;

import java.io.*;
import java.util.*;

class MatrixImpl implements Matrix, Cloneable {
    private List<List<Scalar>> elements;

    //06
    MatrixImpl(int m, int n, Scalar val) {
        if (m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid dimensions.");
        elements = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(val.clone());
            }
            elements.add(row);
        }
    }

    //07
    MatrixImpl(int i, int j, int m, int n) {
        if (i >= j || m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid parameters.");
        elements = new ArrayList<>();
        for (int k = 0; k < m; k++) {
            List<Scalar> row = new ArrayList<>();
            for (int l = 0; l < n; l++) {
                row.add(new ScalarImpl(i, j));
            }
            elements.add(row);
        }
    }

    //08
    MatrixImpl(String csvPath) throws IOException {
        elements = new ArrayList<>();
        try (FileReader fr = new FileReader(csvPath);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                List<Scalar> row = new ArrayList<>();
                String[] stringValues = line.split(", ");

                for (String strVal : stringValues) {
                    row.add(new ScalarImpl(strVal.trim()));
                }
                elements.add(row);
            }
        }
    }

    //09
    MatrixImpl(Scalar[][] arr) {
        elements = new ArrayList<>();
        for (Scalar[] rw : arr) {
            List<Scalar> row = new ArrayList<>();
            for (Scalar s : rw) {
                row.add(s.clone());
            }
            elements.add(row);
        }
    }

    //10
    MatrixImpl(int size) {
        if (size <= 0) throw new IllegalArgumentException("Invalid dimension.");

        Scalar one = new ScalarImpl("1");
        Scalar zero = new ScalarImpl("0");

        elements = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    row.add(one.clone());
                } else {
                    row.add(zero.clone());
                }
            }
            elements.add(row);
        }
    }

    //11 지정, 조회
    @Override
    public void setValue(int row, int col, Scalar val){
        elements.get(row).set(col, val.clone());
    }
    @Override
    public Scalar getValue(int row, int col) {
        return elements.get(row).get(col);
    }

    //13 행개수, 열개수 조회
    @Override
    public int rowSize() {
        return elements.size();
    }
    @Override
    public int colSize() {
        if (elements.isEmpty()) return 0;
        return elements.get(0).size();
    }

    //14
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Scalar> row : elements) {
            for (Scalar s : row) {
                sb.append(s.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //15
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize()) return false;

        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                if (!this.getValue(i, j).equals(other.getValue(i, j))) return false;
            }
        }
        return true;
    }

    //해쉬코드 오버라이드

    //17
    @Override
    public Matrix clone() {
//        Scalar[][] copyElements = new Scalar[this.rowSize()][this.colSize()];
//        for (int i = 0; i < this.rowSize(); i++) {
//            for (int j = 0; j < this.colSize(); j++) {
//                copyElements[i][j] = this.getValue(i, j).clone();
//            }
//        }
//        return new MatrixImpl(copyElements);
        try {
            MatrixImpl clonedMatrix = (MatrixImpl) super.clone();

            if (this.elements != null) {
                clonedMatrix.elements = new ArrayList<>(rowSize());
                for (List<Scalar> originalRow : this.elements) {
                    if (originalRow != null) {
                        List<Scalar> clonedRowList = new ArrayList<>(colSize());
                        for (Scalar originalScalar : originalRow) {
                            if (originalScalar != null) {
                                clonedRowList.add(originalScalar.clone());
                            } else {
                                clonedRowList.add(null);
                            }
                        }
                        clonedMatrix.elements.add(clonedRowList);
                    } else {
                        clonedMatrix.elements.add(null);
                    }
                }
            }
            return clonedMatrix;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("복제 실패", e);
        }
    }

    //22
    @Override
    public Matrix add(Matrix other) {
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize())
            throw new DimensionMismatchException("Matrix size mismatch.");
        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                this.getValue(i, j).add(other.getValue(i, j));
            }
        }
        return this;
    }

    //23
    @Override
    public Matrix multiply(Matrix other) {
        if (this.colSize() != other.rowSize()) throw new DimensionMismatchException("Matrix multiplication dimension mismatch.");
        int m = this.rowSize();
        int n = other.colSize();
        Scalar[][] result = new Scalar[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Scalar sum = new ScalarImpl("0");
                for (int k = 0; k < this.colSize(); k++) {
                    Scalar temp = this.getValue(i, k).clone();
                    temp.multiply(other.getValue(k, j));
                    sum.add(temp);
                }
                result[i][j] = sum;
            }
        }
        this.elements.clear();
        for (int i = 0; i < m; i++) {
            List<Scalar> newRow = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                newRow.add(result[i][j]);
            }
            this.elements.add(newRow);
        }
        return this;
    }

    //34
    @Override
    public Vector getRowVector(int index) {
        if(index > rowSize()) return null;//예외 처리하기

        Scalar[] result = new Scalar[colSize()];
        for(int i = 0; i < colSize(); i++){
            result[i] = elements.get(index).get(i);
        }

        return new VectorImpl(result);
    }

    //35
    @Override
    public Vector getColVector(int index) {
        if(index > colSize()) return null;  //예외 처리하기

        Scalar[] result = new Scalar[rowSize()];
        for(int i = 0; i < rowSize(); i++){
            result[i] = elements.get(i).get(index);
        }

        return new VectorImpl(result);
    }

    //36
    @Override
    public Matrix extractSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart < 0 || rowStart >= rowSize() ||
                rowEnd < 0 || rowEnd >= rowSize() ||
                colStart < 0 || colStart >= colSize() ||
                colEnd < 0 || colEnd >= colSize()) {
            throw new IndexOutOfBoundsException("부분 행렬 추출을 위한 인덱스 범위가 원본 행렬의 크기를 벗어났습니다.");
        }
        if (rowStart > rowEnd) {
            throw new IllegalArgumentException("시작 행 인덱스(" + rowStart + ")는 끝 행 인덱스(" + rowEnd + ")보다 클 수 없습니다.");
        }
        if (colStart > colEnd) {
            throw new IllegalArgumentException("시작 열 인덱스(" + colStart + ")는 끝 열 인덱스(" + colEnd + ")보다 클 수 없습니다.");
        }

        int subMatrixRows = rowEnd - rowStart + 1;
        int subMatrixCols = colEnd - colStart + 1;
        Scalar[][] subMatrixElements = new Scalar[subMatrixRows][subMatrixCols];

        for (int i = 0; i < subMatrixRows; i++) {
            for (int j = 0; j < subMatrixCols; j++) {
                Scalar originalScalar = elements.get(rowStart + i).get(colStart + j);
                if (originalScalar != null) {
                    subMatrixElements[i][j] = originalScalar.clone();
                } else {
                    subMatrixElements[i][j] = null;
                }
            }
        }

        return new MatrixImpl(subMatrixElements);
    }

    //37
    @Override
    public Matrix minorSubMatrix(int row, int col) {
        if(row > rowSize() || col > colSize() || row < 0 || col < 0){
            //에러처리
        }
        int minorRows = rowSize() - 1;
        int minorCols = colSize() - 1;
        Scalar[][] newElements = new Scalar[minorRows][minorCols];

        int currentRowInMinor = 0;
        for (int i = 0; i < rowSize(); i++) {
            if (i == row) {
                continue;
            }
            int currentColMinor = 0;
            for (int j = 0; j < colSize(); j++) {
                if (j == col) {
                    continue;
                }
                Scalar originalScalar = this.elements.get(i).get(j);
                if (minorRows > 0 && minorCols > 0) {
                    if (originalScalar != null) {
                        newElements[currentRowInMinor][currentColMinor] = originalScalar.clone();
                    } else {
                        newElements[currentRowInMinor][currentColMinor] = null;
                    }
                }
                currentColMinor++;
            }
        }
        return new MatrixImpl(newElements);
    }

    //38
    @Override
    public Matrix transposeMatrix() {
        int newRow = colSize();
        int newCol = rowSize();
        Scalar[][] newElements = new Scalar[newRow][newCol];

        for (int i = 0; i < rowSize(); i++) {
            for(int j = 0; j <colSize(); j++) {
                Scalar originalScalar = elements.get(i).get(j);
                if(originalScalar != null){
                    newElements[j][i] = originalScalar.clone();
                } else {
                    newElements[j][i] = null;
                }
            }
        }
        return new MatrixImpl(newElements);
    }

    //39
    @Override
    public Scalar trace() {
        if (!isSquare()) {
            //에러처리
        }

        Scalar sumElements = Factory.createScalar("0");

        for (int i =0; i < rowSize(); i++) {
            Scalar diagonalElement = elements.get(i).get(i);

            if (diagonalElement != null) {
                sumElements.add(diagonalElement);
            } else {
                //null일때 에러처리
            }

        }
        return sumElements;
    }

    //40
    @Override
    public boolean isSquare() {

        return false;
    }

    //41
    @Override
    public boolean isUpperTriangularMatrix() {

        return false;
    }

    //42
    @Override
    public boolean isLowerTriangularMatrix() {

        return false;
    }

    //43
    @Override
    public boolean isIdentity() {

        return false;
    }

    //44
    @Override
    public boolean isZero() {

        return false;
    }

    //45
    @Override
    public Matrix rowSwap(int row1, int row2) {

        return null;
    }

    //46
    @Override
    public Matrix colSwap(int col1, int col2) {

        return null;
    }

    //47
    @Override
    public Matrix rowMultiply(int index, Scalar val) {

        return null;
    }

    //48
    @Override
    public Matrix colMultiply(int index, Scalar val) {

        return null;
    }
}