package tensor;
public interface Scalar {
    //12
    void setValue(String val);
    String getValue();
    //14
    String toString();
    //15
    boolean equals(Object obj);
    //16
    int compareTo(Scalar val);
    //17
    Scalar clone();
    //18
    void add(Scalar other);
    //19
    void multiply(Scalar other);
}