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
    VectorImpl(int i, int j, int n) {
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
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            sb.append(elements.get(i).getValue());
            if (i < size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    //15
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof Vector compareObj)) throw new ClassCastException("주어진 객체가 벡터가 아닙니다.");
        if (this.size() != compareObj.size()) return false;
        for (int i = 0; i < size(); i++) {
            if(!(elements.get(i).equals(compareObj.getValue(i)))) return false;
        }
        return true;
    }
    //17
    @Override
    public Vector clone() {
        Scalar[] copyElements = new Scalar[size()];
        for (int i = 0; i < size(); i++) {
            copyElements[i] = getValue(i).clone();
        }
        return new VectorImpl(copyElements);
    }
    //20
    @Override
    public void add(Vector other) {
        if (size() != other.size()) throw new SizeMismatchException("벡터 덧셈 조건에 맞지않습니다. (조건: 벡터의 길이가 같아야 함)");
        for (int i = 0; i < size(); i++) {
            getValue(i).add(other.getValue(i));
        }
    }
    //21
    @Override
    public void multiply(Scalar scalar) {
        for (Scalar s : elements) s.multiply(scalar);
    }
    //30
    @Override
    public Matrix toVerticalMatrix() {
        Scalar[][] newElements = new Scalar[size()][1];
        for (int i = 0; i < size(); i++) {
            newElements[i][0] = getValue(i).clone();
        }
        return new MatrixImpl(newElements);
    }
    //31
    @Override
    public Matrix toHorizontalMatrix() {
        Scalar[][] newElements = new Scalar[1][size()];
        for (int i = 0; i < size(); i++) {
            newElements[0][i] = getValue(i).clone();
        }
        return new MatrixImpl(newElements);
    }
    //26
    static Vector add(Vector v1, Vector v2){
        if(v1.size() != v2.size()) throw new SizeMismatchException("벡터 덧셈 조건에 맞지않습니다. (조건: 벡터의 길이가 같아야 함)");
        Vector result = v1.clone();
        result.add(v2);
        return result;
    }
    //27
    static Vector multiply(Vector v, Scalar s){
        Vector result = v.clone();
        result.multiply(s);
        return result;
    }
}