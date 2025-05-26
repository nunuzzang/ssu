package tensor;

import java.math.BigDecimal;
import java.util.Random;

class ScalarImpl implements Scalar, Comparable<Scalar> {
    private BigDecimal value;

    //01
    ScalarImpl(String val) {
        this.value = new BigDecimal(val);
    }

    //02
    ScalarImpl(double i, double j){
        if (i >= j) throw new IllegalArgumentException("i must be less than j");
        Random random = new Random();
        double randomValue = i + (j - i) * random.nextDouble();
        value = new BigDecimal(String.valueOf(randomValue));
    }

    //12 지정, 조회
    public void setValue(String val) {
        value = new BigDecimal(val);
    }
    public String getValue() {
        return value.toString();
    }

    //14
    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString();
    }

    //15
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Scalar)) return false; // ClassCastException 에러로 보내도될듯
        String compareObj1 = ((Scalar) obj).getValue();
        BigDecimal compareObj2 = new BigDecimal(compareObj1);
        return value.compareTo(compareObj2) == 0;
    }

    //16
    @Override
    public int compareTo(Scalar other) {
        BigDecimal bigDecimalValue = new BigDecimal(other.getValue());
        return value.compareTo(bigDecimalValue);
    }

    //17
    @Override
    public Scalar clone() {
        return new ScalarImpl(value.toPlainString());
    }

    //18
    @Override
    public void add(Scalar other) {
        BigDecimal bigDecimalVal = new BigDecimal(other.getValue());
        value = value.add(bigDecimalVal);
    }

    //19
    @Override
    public void multiply(Scalar other) {
        BigDecimal bigDecimalVal = new BigDecimal(other.getValue());
        value = value.multiply(bigDecimalVal);
    }

    //24
    static Scalar add(Scalar s1, Scalar s2) {
        Scalar result = s1.clone();
        result.add(s2);
        return result;
    }
    //25
    static Scalar multiply(Scalar s1, Scalar s2){
        Scalar result = s1.clone();
        result.multiply(s2);
        return result;
    }
}