package tensor;

import java.math.BigDecimal;
import java.util.Random;

class ScalarImpl implements Scalar, Cloneable {
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

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    //16
    @Override
    public int compareTo(Scalar val) {
        BigDecimal bigDecimalValue = new BigDecimal(val.getValue());
        return value.compareTo(bigDecimalValue);
    }

    //17
    @Override
    public Scalar clone() {
//        return new ScalarImpl(value.toPlainString());
        try {
            ScalarImpl cloned = (ScalarImpl) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("복제 실패", e);
        }
    }

    //18
    @Override
    public Scalar add(Scalar other) {
        BigDecimal bigDecimalVal = new BigDecimal(other.getValue());
        value = value.add(bigDecimalVal);
        return this;
    }

    //19
    @Override
    public Scalar multiply(Scalar other) {
        BigDecimal bigDecimalVal = new BigDecimal(other.getValue());
        value = value.multiply(bigDecimalVal);
        return this;
    }
}