package tensor;

import java.util.*;

class VectorImpl implements Vector, Cloneable {
    private List<Scalar> elements;

    //03
    VectorImpl(int n, Scalar val) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) elements.add(val.clone()); //clone 객체 복사부분 다시 생각해보기
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
        elements.set(index, val.clone());
    }
    @Override
    public Scalar getValue(int index) {
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

//    @Override
//    public int hashCode() {
//        int result = 1;
//        for (Scalar element : elements) {
//            result = 31 * result + (element == null ? 0 : element.hashCode());
//        }
//        result = 31 * result + Integer.hashCode(elements.length);
//        return result;
//    }

    //17
    public Vector clone() {
//        Scalar[] copyElements = new Scalar[size()];
//        for (int i = 0; i < size(); i++) copyElements[i] = getValue(i).clone();
//        return new VectorImpl(copyElements);
        try{
            VectorImpl clonedVector = (VectorImpl) super.clone();
            if (this.elements != null) {
                clonedVector.elements = new ArrayList<>(this.elements.size()); // 새 List 생성
                for (Scalar originalScalar : this.elements) {
                    if (originalScalar != null) {
                        clonedVector.elements.add(originalScalar.clone());
                    } else {
                        clonedVector.elements.add(null); // null 요소도 그대로 복제
                    }
                }
            }
            return clonedVector;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("복제 실패", e);
        }
    }

    //20
    public Vector add(Vector other) {
        if (size() != other.size()) throw new DimensionMismatchException("Vectors must have the same length for addition.");
        for (int i = 0; i < size(); i++) getValue(i).add(other.getValue(i));
        return this;
    }

    //21
    public Vector multiply(Scalar scalar) {
        for (Scalar s : elements) s.multiply(scalar);
        return this;
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