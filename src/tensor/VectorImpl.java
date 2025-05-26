package tensor;

import java.util.*;

class VectorImpl implements Vector {
    private List<Scalar> elements;

    //03
    VectorImpl(int n, Scalar val) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) elements.add(val.clone());
    }

    //04
    VectorImpl(double i, double j, int n) {
        elements = new ArrayList<>();
        for (int k = 0; k < n; k++) elements.add(new ScalarImpl(i, j));
    }

    //05
    VectorImpl(Scalar[] arr) {
        elements = new ArrayList<>();
        for (Scalar s : arr) elements.add(s.clone());
    }

    //11 지정, 조회
    @Override
    public void setValue(int index, Scalar val) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다.");
        }
        elements.set(index, val.clone());
    }
    @Override
    public Scalar getValue(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다.");
        }
        return elements.get(index);
    }

    //13
    @Override
    public int size() {
        return elements.size();
    }

    //14
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (Scalar s : elements) sb.append(s.toString()).append(" ");
        sb.append("]");
        return sb.toString();
    }

    //15
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof Vector compareObj)) return false;
        if (this.size() != compareObj.size()) return false;
        for (int i = 0; i < size(); i++) {
            if(!(elements.get(i).equals(compareObj.getValue(i)))) return false;
        }
        return true;
    }

    //17
    public Vector clone() {
        Scalar[] copyElements = new Scalar[size()];
        for (int i = 0; i < size(); i++) copyElements[i] = getValue(i).clone();
        return new VectorImpl(copyElements);
    }

    //20
    public void add(Vector other) {
        if (size() != other.size()) throw new SizeMismatchException("벡터 덧셈 조건에 맞지않습니다.");
        for (int i = 0; i < size(); i++) getValue(i).add(other.getValue(i));
    }

    //21
    public void multiply(Scalar scalar) {
        for (Scalar s : elements) s.multiply(scalar);
    }

    //30
    @Override
    public Matrix toVerticalMatrix() {
        Scalar[][] arr = new Scalar[size()][1];
        for (int i = 0; i < size(); i++) {
            arr[i][0] = getValue(i).clone();
        }
        return new MatrixImpl(arr);
    }

    //31
    @Override
    public Matrix toHorizontalMatrix() {
        Scalar[][] arr = new Scalar[1][size()];
        for (int i = 0; i < size(); i++) {
            arr[0][i] = getValue(i).clone();
        }
        return new MatrixImpl(arr);
    }

}