package tensor;

public interface Vector {
    //11
    void setValue(int index, Scalar val);
    Scalar getValue(int index);
    //13
    int size();
    //14
    String toString();
    //15
    boolean equals(Object obj);
    //17
    Vector clone();
    //20
    Vector add(Vector other);
    //21
    Vector multiply(Scalar scalar);
    //26
    static Vector add(Vector v1, Vector v2){
        Vector result = v1.clone();
        return result.add(v2);
    }
    //27
    static Vector multiply(Vector v, Scalar s){
        Vector result = v.clone();
        return result.multiply(s);
    }
    //30
    Matrix toVerticalMatrix();
    //31
    Matrix toHorizontalMatrix();
}
