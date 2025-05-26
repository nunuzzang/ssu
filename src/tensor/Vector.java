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
    void add(Vector other);
    //21
    void multiply(Scalar scalar);
    //26
    static Vector add(Vector v1, Vector v2){
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
    //30
    Matrix toVerticalMatrix();
    //31
    Matrix toHorizontalMatrix();
}
