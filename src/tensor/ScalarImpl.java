package tensor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

class ScalarImpl implements Scalar, Comparable<Scalar> {
    private BigDecimal value;

    //01
    ScalarImpl(String val) {
        BigDecimal tempValue;
        try {
            tempValue = new BigDecimal(val);
        } catch (NumberFormatException e) {
            throw new TensorException("스칼라 값이 올바르지 않습니다.");
        }

        try {
            value = tempValue.setScale(5, RoundingMode.HALF_UP);
        } catch (ArithmeticException e) {
            throw new TensorException("스칼라 값의 소수점 처리 중 산술 오류가 발생하였습니다.");
        }
    }
    //02
    ScalarImpl(int i, int j){
        if (i >= j) throw new IllegalArgumentException("i는 j보다 작아야한다.");
        Random random = new Random();
        BigDecimal range = new BigDecimal(j - i);
        BigDecimal randomFactor = new BigDecimal(random.nextDouble());
        BigDecimal calculatedValue = randomFactor.multiply(range).add(new BigDecimal(i));
        value = calculatedValue.setScale(5, RoundingMode.HALF_UP);
    }
    //12 지정, 조회
    @Override
    public void setValue(String val) {
        BigDecimal tempValue;
        try {
            tempValue = new BigDecimal(val);
        } catch (NumberFormatException e) {
            throw new TensorException("올바른 값을 입력하지 않으셨습니다.");
        }
        value = tempValue.setScale(5, RoundingMode.HALF_UP);
    }
    @Override
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
    public int compareTo(Scalar val) {
        BigDecimal bigDecimalValue = new BigDecimal(val.getValue());
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
        value = value.setScale(5, RoundingMode.HALF_UP);
    }
    //19
    @Override
    public void multiply(Scalar other) {
        BigDecimal otherBigDecimalVal = new BigDecimal(other.getValue());
        value = value.multiply(otherBigDecimalVal);
        value = value.setScale(5, RoundingMode.HALF_UP);
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