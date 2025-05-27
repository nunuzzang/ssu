package tensor;

import java.io.*;
import java.util.*;

class MatrixImpl implements Matrix {
    private List<List<Scalar>> elements;

    //06
    MatrixImpl(int m, int n, Scalar val) {
        if (m <= 0 || n <= 0) throw new IllegalArgumentException("잘못된 인자를 입력하였습니다.");
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
        if (i >= j || m <= 0 || n <= 0) throw new IllegalArgumentException("잘못된 인자를 입력하였습니다.");
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
        if (size <= 0) throw new IllegalArgumentException("잘못된 인자를 입력하였습니다.");

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
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다.");
        }
        elements.get(row).set(col, val.clone());
    }
    @Override
    public Scalar getValue(int row, int col) {
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다.");
        }
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
    //17
    @Override
    public Matrix clone() {
        Scalar[][] copyElements = new Scalar[this.rowSize()][this.colSize()];
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                copyElements[i][j] = this.getValue(i, j).clone();
            }
        }
        return new MatrixImpl(copyElements);
    }
    //22
    @Override
    public void add(Matrix other) {
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize())
            throw new SizeMismatchException("행렬 덧셈 조건이 맞지 않습니다.");
        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                this.getValue(i, j).add(other.getValue(i, j));
            }
        }
    }
    //23
    @Override
    public void multiply(Matrix other) {
        if (this.colSize() != other.rowSize()) {
            throw new SizeMismatchException("행렬 곱셈 조건이 맞지 않습니다.");
        }
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
    }
    //32
    @Override
    public Matrix attachHMatrix(Matrix other){
        if (rowSize() != other.rowSize()){
            throw new SizeMismatchException("두 행렬의 행수가 같지 않습니다.");
        }
        int newRows = rowSize();
        int newCols = colSize() + other.colSize();
        Scalar[][] newElements = new Scalar[newRows][newCols];

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < colSize(); j++) {
                Scalar currentScalar = getValue(i, j);
                if (currentScalar != null) {
                    newElements[i][j] = currentScalar.clone();
                } else {
                    newElements[i][j] = null;
                }
            }
        }

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < other.colSize(); j++) {
                Scalar otherScalar = other.getValue(i, j);
                if (otherScalar != null) {
                    newElements[i][colSize() + j] = otherScalar.clone();
                } else {
                    newElements[i][colSize() + j] = null;
                }
            }
        }
        return new MatrixImpl(newElements);
    }

    //33
    @Override
    public Matrix attachVMatrix(Matrix other){
        if (colSize() != other.colSize()) {
            throw new SizeMismatchException("두 행렬의 열 수가 같지 않습니다.");
        }
        int newRows = rowSize() + other.rowSize();
        int newCols = colSize();
        Scalar[][] newElements = new Scalar[newRows][newCols];

        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < newCols; j++) {
                Scalar currentScalar = getValue(i, j);
                if (currentScalar != null) {
                    newElements[i][j] = currentScalar.clone();
                } else {
                    newElements[i][j] = null;
                }
            }
        }

        for (int i = 0; i < other.rowSize(); i++) {
            for (int j = 0; j < newCols; j++) {
                Scalar otherScalar = other.getValue(i, j);
                if (otherScalar != null) {
                    newElements[rowSize() + i][j] = otherScalar.clone();
                } else {
                    newElements[rowSize() + i][j] = null;
                }
            }
        }
        return new MatrixImpl(newElements);
    }
    //34
    @Override
    public Vector getRowVector(int row) {
        if(row < 0 || row >= this.rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        Scalar[] result = new Scalar[colSize()];
        for(int i = 0; i < colSize(); i++){
            result[i] = elements.get(row).get(i);
        }

        return new VectorImpl(result);
    }
    //35
    @Override
    public Vector getColVector(int col) {
        if(col < 0 || col >= this.colSize()) {
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }

        Scalar[] result = new Scalar[rowSize()];
        for(int i = 0; i < rowSize(); i++){
            result[i] = elements.get(i).get(col);
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
            throw new IndexOutOfBoundsException("부분 행렬 인덱스가 범위를 벗어났습니다.");
        }
        if (rowStart > rowEnd) {
            throw new SizeMismatchException("시작 행 인덱스가 끝 행 인덱스보다 클 수 없다.");
        }
        if (colStart > colEnd) {
            throw new SizeMismatchException("시작 열 인덱스가 끝 열 인덱스보다 클 수 없다.");
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
            throw new IndexOutOfBoundsException("minor 인덱스가 범위를 벗어났습니다.");
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
    //38 전치행렬 구하기
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
    //39 대각 요소의 합
    @Override
    public Scalar trace() {
        if (!isSquare()) {
            throw new NotSquareMatrixException("정사각 행렬이 아닙니다.");
        }

        Scalar sumElements = Factory.createScalar("0");

        for (int i =0; i < rowSize(); i++) {
            Scalar diagonalElement = elements.get(i).get(i);
            if (diagonalElement != null) {
                sumElements.add(diagonalElement);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
        return sumElements;
    }
    //40. 정사각 행렬 판단
    @Override
    public boolean isSquare() {
        if(rowSize() > 0 && rowSize() == colSize()) return true;
        else return false;
    }
    //41. 상삼각 행렬 판단
    @Override
    public boolean isUpperTriangular() {
        if(!isSquare()) {
            throw new NotSquareMatrixException("정사각 행렬이 아닙니다.");
        }

        Scalar zeroScalar = new ScalarImpl("0");

        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                if(i > j) {
                    Scalar currentElement = elements.get(i).get(j);
                    if(currentElement == null || !currentElement.equals(zeroScalar)) return false;
                }
            }
        }

        return true;
    }
    //42. 하삼각 행렬 판단
    @Override
    public boolean isLowerTriangular() {
        if(!isSquare()) {
            throw new NotSquareMatrixException("정사각 행렬이 아닙니다.");
        }

        Scalar zeroScalar = new ScalarImpl("0");

        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                if(i < j) {
                    Scalar currentElement = elements.get(i).get(j);
                    if(currentElement == null || !currentElement.equals(zeroScalar)) return false;
                }
            }
        }

        return true;
    }
    //43 단위 행렬 판단
    @Override
    public boolean isIdentity() {
        if(!isSquare()){
            throw new NotSquareMatrixException("정사각 행렬이 아닙니다.");
        }

        Scalar oneScalar = new ScalarImpl("1");
        Scalar zeroScalar = new ScalarImpl("0");

        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                Scalar currentElement = elements.get(i).get(j);

                if(currentElement == null) return false;
                if(i == j) {
                    if(!currentElement.equals(oneScalar)) return false;
                } else {
                    if(!currentElement.equals(zeroScalar)) return false;
                }
            }
        }

        return true;
    }
    //44 영행렬 판단
    @Override
    public boolean isZero() {
        Scalar zeroScalar = new ScalarImpl("0");

        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                Scalar currentElement = elements.get(i).get(j);
                if(currentElement == null || !currentElement.equals(zeroScalar)) return false;
            }
        }
        return true;
    }
    //45 두 행의 위치 맞교환
    @Override
    public void rowSwap(int row1, int row2) {
        if (row1 < 0 || row1 >= rowSize() || row2 < 0 || row2 >= rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        if (row1 == row2) return;

        List<Scalar> temp = elements.get(row1);
        elements.set(row1, elements.get(row2));
        elements.set(row2, temp);
    }
    //46 두 열의 위치 맞교환
    @Override
    public void colSwap(int col1, int col2) {
        if (col1 < 0 || col1 >= colSize() || col2 < 0 || col2 >= colSize()){
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        if (col1 == col2) return;

        for (int i = 0; i < rowSize(); i++) {
            List<Scalar> currentRow = elements.get(i);

            Scalar temp = currentRow.get(col1);
            currentRow.set(col1, currentRow.get(col2));
            currentRow.set(col2, temp);
        }
    }
    //47 특정 행에 상수배(스칼라)
    @Override
    public void rowMultiply(int row, Scalar val) {
        if (row < 0 || row >= rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("null을 참조하였습니다.");
        }

        List<Scalar> rowElements = this.elements.get(row);

        for (int i = 0; i < colSize(); i++) {
            Scalar currentElement = rowElements.get(i);
            
            if (currentElement != null) {
                currentElement.multiply(val);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
    }
    //48 특정 열에 상수배(스칼라)
    @Override
    public void colMultiply(int col, Scalar val) {
        if (col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("null을 참조하였습니다.");
        }
        for (int i = 0; i < rowSize(); i++) {
            Scalar currentElement = elements.get(i).get(col);

            if (currentElement != null) {
                currentElement.multiply(val);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
    }
    //49
    @Override
    public void rowAddOtherRow(int targetRow, int sourceRow, Scalar val) {
        if (targetRow < 0 || targetRow >= rowSize()) {
            throw new IndexOutOfBoundsException("target 행 인덱스가 범위를 벗어났습니다.");
        }
        if (sourceRow < 0 || sourceRow >= rowSize()) {
            throw new IndexOutOfBoundsException("source 행 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("곱할 스칼라 값은 null일 수 없습니다.");
        }
        List<Scalar> targetRowElements = elements.get(targetRow);
        List<Scalar> sourceRowElements = elements.get(sourceRow);

        for (int j = 0; j < colSize(); j++) {
            Scalar targetElement = targetRowElements.get(j);
            Scalar sourceElement = sourceRowElements.get(j);

            if (targetElement == null || sourceElement == null) {
                if (sourceElement == null)
                    throw new NullPointerException("source 행의 요소가 null입니다.");
                if (targetElement == null)
                    throw new NullPointerException("target 행의 요소가 null입니다.");
            }

            Scalar term = sourceElement.clone();
            term.multiply(val);
            targetElement.add(term);
        }
    }
    //50
    @Override
    public void colAddOtherCol(int targetCol, int sourceCol, Scalar val) {
        if (targetCol < 0 || targetCol >= colSize()) {
            throw new IndexOutOfBoundsException("target 열 인덱스가 범위를 벗어났습니다.");
        }
        if (sourceCol < 0 || sourceCol >= colSize()) {
            throw new IndexOutOfBoundsException("source 열 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("곱할 스칼라 값은 null일 수 없습니다.");
        }
        for (int i = 0; i < rowSize(); i++) {
            List<Scalar> currentRow = this.elements.get(i);

            Scalar targetElement = currentRow.get(targetCol);
            Scalar sourceElement = currentRow.get(sourceCol);

            if (targetElement == null || sourceElement == null) {
                if (sourceElement == null) throw new NullPointerException("source 열의 요소가 null입니다.");
                if (targetElement == null) throw new NullPointerException("target 열의 요소가 null입니다.");
            }

            Scalar term = sourceElement.clone();
            term.multiply(val);
            targetElement.add(term);
        }
    }
    //51
    @Override
    public Matrix getRREF() {
        Matrix copy = clone();
        int lead = 0;
        int rowCount = copy.rowSize();
        int colCount = copy.colSize();
        for (int r = 0; r < rowCount; r++) {
            if (lead >= colCount) break;
            int i = r;
            while (i < rowCount && copy.getValue(i, lead).equals(new ScalarImpl("0"))) {
                i++;
            }
            if (i == rowCount) {
                lead++;
                if (lead >= colCount) break;
                r--;
                continue;
            }
            if (i != r) {
                for (int k = 0; k < colCount; k++) {
                    Scalar temp = copy.getValue(r, k);
                    copy.setValue(r, k, copy.getValue(i, k));
                    copy.setValue(i, k, temp);
                }
            }
            Scalar lv = copy.getValue(r, lead).clone();
            for (int k = 0; k < colCount; k++) {
                Scalar val = copy.getValue(r, k).clone();
                if (!lv.equals(new ScalarImpl("0"))) {
                    val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).divide(new java.math.BigDecimal(lv.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                }
                copy.setValue(r, k, val);
            }
            for (int i2 = 0; i2 < rowCount; i2++) {
                if (i2 != r) {
                    Scalar lv2 = copy.getValue(i2, lead).clone();
                    for (int k = 0; k < colCount; k++) {
                        Scalar val = copy.getValue(i2, k).clone();
                        Scalar sub = copy.getValue(r, k).clone();
                        sub.multiply(lv2);
                        val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).subtract(new java.math.BigDecimal(sub.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                        copy.setValue(i2, k, val);
                    }
                }
            }
            lead++;
        }
        return copy;
    }
    //52
    @Override
    public boolean isRREF() {
        Scalar zeroScalar = new ScalarImpl("0");
        Scalar oneScalar = new ScalarImpl("1");
        int previousLeadCol = -1;
        boolean encounteredZeroRow = false;

        for (int r = 0; r < rowSize(); r++) {
            int currentLeadCol = -1;

            for (int c = 0; c < colSize(); c++) {
                if (!getValue(r, c).equals(zeroScalar)) {
                    currentLeadCol = c;
                    break;
                }
            }
            if (currentLeadCol != -1) {
                if (encounteredZeroRow) {
                    return false;
                }
                if (!getValue(r, currentLeadCol).equals(oneScalar)) {
                    return false;
                }
                if (currentLeadCol <= previousLeadCol) {
                    return false;
                }
                for (int k = 0; k < rowSize(); k++) {
                    if (k != r) {
                        if (!getValue(k, currentLeadCol).equals(zeroScalar)) {
                            return false;
                        }
                    }
                }
                previousLeadCol = currentLeadCol;
            } else {
                encounteredZeroRow = true;
            }
        }
        return true;
    }
    //53
    @Override
    public Scalar getDeterminant() {
        if (!isSquare()) {
            throw new NotSquareMatrixException("행렬식은 정사각 행렬에서만 구할 수 있습니다.");
        }
        if (rowSize() == 0) {
            throw new SizeMismatchException("0x0 행렬의 행렬식은 정의되지 않습니다.");
        }

        if (rowSize() == 1) {
            Scalar element = elements.get(0).get(0);
            if(element == null) throw new NullPointerException("null을 참조하였습니다.");
            return element.clone();
        }

        if (rowSize() == 2) {
            Scalar a = elements.get(0).get(0);
            Scalar b = elements.get(0).get(1);
            Scalar c = elements.get(1).get(0);
            Scalar d = elements.get(1).get(1);

            if (a == null || b == null || c == null || d == null) {
                throw new NullPointerException("null을 참조하였습니다.");
            }

            Scalar ad = a.clone();
            ad.multiply(d);
            Scalar bc = b.clone();
            bc.multiply(c);

            Scalar negOne = Factory.createScalar("-1");
            Scalar termBcNegated = bc.clone();
            termBcNegated.multiply(negOne);
            ad.add(termBcNegated);
            return ad;
        }

        Scalar totalDeterminant = Factory.createScalar("0");
        Scalar sign = Factory.createScalar("1");

        for (int j = 0; j < colSize(); j++) {
            Scalar elementA0j = elements.get(0).get(j);
            if (elementA0j == null) {
                throw new NullPointerException("null을 참조하였습니다.");
            }

            Matrix minorMatrix = minorSubMatrix(0, j);
            Scalar minorDeterminant = minorMatrix.getDeterminant();

            Scalar term = elementA0j.clone();
            term.multiply(minorDeterminant);
            term.multiply(sign);

            totalDeterminant.add(term);

            sign.multiply(Factory.createScalar("-1"));
        }
        return totalDeterminant;
    }
    //54
    @Override
    public Matrix getInverseMatrix() {
        if(!isSquare()) {
            throw new NotSquareMatrixException("정사각 행렬만 역행렬을 구할 수 있습니다.");
        }
        int n = rowSize();
        Scalar[][] identity = new Scalar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = new ScalarImpl(i == j ? "1" : "0");
            }
        }
        Matrix aug = new MatrixImpl(n, 2 * n, new ScalarImpl("0"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aug.setValue(i, j, getValue(i, j).clone());
                aug.setValue(i, j + n, identity[i][j].clone());
            }
        }
        for (int i = 0; i < n; i++) {
            Scalar diag = aug.getValue(i, i).clone();
            if (diag.equals(new ScalarImpl("0"))) throw new ArithmeticException("역행렬이 존재하지 않습니다.");
            for (int j = 0; j < 2 * n; j++) {
                Scalar val = aug.getValue(i, j).clone();
                val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).divide(new java.math.BigDecimal(diag.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                aug.setValue(i, j, val);
            }
            for (int k = 0; k < n; k++) {
                if (k == i) continue;
                Scalar factor = aug.getValue(k, i).clone();
                for (int j = 0; j < 2 * n; j++) {
                    Scalar val = aug.getValue(k, j).clone();
                    Scalar sub = aug.getValue(i, j).clone();
                    sub.multiply(factor);
                    val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).subtract(new java.math.BigDecimal(sub.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                    aug.setValue(k, j, val);
                }
            }
        }
        Scalar[][] inv = new Scalar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inv[i][j] = aug.getValue(i, j + n).clone();
            }
        }
        return new MatrixImpl(inv);
    }
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
        return m1.attachHMatrix(m2);
    }
    //33
    static Matrix attachVMatrix(Matrix m1, Matrix m2){
        return m1.attachVMatrix(m2);
    }
}