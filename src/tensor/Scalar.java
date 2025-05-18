package tensor;
public interface Scalar extends Comparable<Scalar>{
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
    Scalar add(Scalar other);
    //19
    Scalar multiply(Scalar other);
    //24
    static Scalar add(Scalar s1, Scalar s2) {
        Scalar result = s1.clone();
        return result.add(s2);
    }
    //25
    static Scalar multiply(Scalar s1, Scalar s2){
        Scalar result = s1.clone();
        return result.multiply(s2);
    }

}