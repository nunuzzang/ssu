package test;
import tensor.*;

public class Test {
    public static void main(String[] args) {
        // 1. 값(String)을 지정하여 스칼라 생성
        Scalar sA = Factory.createScalar("1024");
        Scalar expectedAnswer1 = Factory.createScalar("1024");
        System.out.println("1. 값(String)을 지정하여 스칼라 생성");
        System.out.println("인자값: \"1024\"");
        System.out.println("기댓값: " + expectedAnswer1);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer1) ? "통과\n" : "실패\n");

        // 2. i 이상 j 미만의 무작위 값을 요소로하는 스칼라 생성
        Scalar sB = Factory.createScalar(1,20);
        System.out.println("2. i 이상 j 미만의 무작위 값을 요소로하는 스칼라 생성");
        System.out.println("i = 1, j = 20");
        System.out.println("결과: " + sB + "\n");
        boolean success2 = true;
        double val2 = Double.parseDouble(sB.getValue());
        if(val2 < 1 || val2 >= 20) success2 = false;
        System.out.println(success2 ? "통과\n" : "실패\n");

        // 3. 지정된 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터 생성
        Vector vA = Factory.createVector(3, Factory.createScalar("10"));
        Vector expectedAnswer3 = Factory.createVector(new Scalar[]{
            Factory.createScalar("10"),
            Factory.createScalar("10"),
            Factory.createScalar("10")
        });
        System.out.println("3. 지정된 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터 생성");
        System.out.println("인자값: n=3, val=10");
        System.out.println("기댓값: " + expectedAnswer3);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer3) ? "통과\n" : "실패\n");

        // 4. i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터 생성
        Vector vB = Factory.createVector(2, 8, 6);
        System.out.println("4. i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터 생성");
        System.out.println("인자값: i=2, j=8 사이 무작위, n=6");
        System.out.println("결과: " + vB + "\n");
        boolean success4 = true;
        for (int i = 0; i < vB.size(); i++) {
            double val4 = Double.parseDouble(vB.getValue(i).getValue());
            if (val4 < 1 || val4 >= 8    ) {
                success4 = false;
                break;
            }
        }
        System.out.println(success4 ? "통과\n" : "실패\n");

        // 5. 1차원 배열로부터 n-차원 벡터 생성
        Scalar[] arr = {Factory.createScalar("3"), Factory.createScalar("6"), Factory.createScalar("9")};
        Vector vC = Factory.createVector(arr);
        Vector expectedAnswer5 = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("6"),
            Factory.createScalar("9")
        });
        System.out.println("5. 1차원 배열로부터 n-차원 벡터 생성");
        System.out.println("인자값: [3, 6, 9]");
        System.out.println("기댓값: " + expectedAnswer5);
        System.out.println(vC.equals(expectedAnswer5) ? "통과\n" : "실패\n");

        // 6. 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성
        Matrix mA = Factory.createMatrix(2, 2, Factory.createScalar("24"));
        Matrix expectedAnswer6 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("24"), Factory.createScalar("24")},
            {Factory.createScalar("24"), Factory.createScalar("24")}
        });
        System.out.println("6. 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬 생성");
        System.out.println("인자값: m=2, n=2, val=24");
        System.out.println("기댓값: \n" + expectedAnswer6);
        System.out.println("결과: \n" + mA);
        System.out.println(mA.equals(expectedAnswer6)? "통과\n" : "실패\n");

        // 7. i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬 생성
        Matrix mB = Factory.createMatrix(2, 4, 4, 2);
        System.out.println("7. i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬 생성");
        System.out.println("인자값: i=2, j=4 사이 무작위, m=4, n=2");
        System.out.println("결과: \n" + mB);
        boolean success7 = true;
        for (int i = 0; i < mB.rowSize(); i++) {
            for (int j = 0; j < mB.colSize(); j++) {
                double val7 = Double.parseDouble(mB.getValue(i, j).getValue());
                if (val7 < 2 || val7 >= 4) {
                    success7 = false;
                    break;
                }
            }
            if (!success7) break;
        }
        System.out.println(success7 ? "통과\n" : "실패\n");

        // 8. csv 파일로부터 m x n 행렬을 생성할 수 있다.
        Matrix expectedAnswer8 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("2"), Factory.createScalar("0"), Factory.createScalar("2")},
                {Factory.createScalar("2"), Factory.createScalar("1"), Factory.createScalar("7")},
                {Factory.createScalar("5"), Factory.createScalar("9"), Factory.createScalar("0")},
        });
        try {
            java.io.FileWriter fw = new java.io.FileWriter("matrix.csv");
            fw.write("2, 0, 2\n2, 1, 7\n5, 9, 0\n");
            fw.close();

            System.out.println("8. csv 파일로부터 m x n 행렬을 생성할 수 있다.");
            System.out.println("CSV 파일 내용:");
            System.out.println("2,0,2");
            System.out.println("2,1,7");
            System.out.println("5,9,0\n");
            System.out.println("기댓값:\n" + expectedAnswer8);
            Matrix mCsv = Factory.createMatrix("matrix.csv");
            System.out.println("생성된 행렬:\n" + mCsv);
            System.out.println(mCsv.equals(expectedAnswer8) ? "통과\n" : "실패\n");

            new java.io.File("matrix.csv").delete();
        } catch (Exception e) {
            System.out.println("CSV 파일 처리 중 예외 발생: " + e.getMessage());
        }

        // 9. 2차원 배열로부터 m x n 행렬 생성
        Scalar[][] arr2 = {
            {Factory.createScalar("3"), Factory.createScalar("6")},
            {Factory.createScalar("9"), Factory.createScalar("12")},
            {Factory.createScalar("15"), Factory.createScalar("18")}
        };
        Matrix mC = Factory.createMatrix(arr2);
        Matrix expectedAnswer9 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("3"), Factory.createScalar("6")},
            {Factory.createScalar("9"), Factory.createScalar("12")},
            {Factory.createScalar("15"), Factory.createScalar("18")}
        });
        System.out.println("9. 2차원 배열로부터 m x n 행렬 생성");
        System.out.println("기댓값: \n" + expectedAnswer9);
        System.out.println("결과: \n" + mC);
        System.out.println(mC.equals(expectedAnswer9) ? "통과\n" : "실패\n");

        // 10. 단위행렬 생성
        Matrix mD = Factory.createIdentityMatrix(2);
        Matrix expectedAnswer10 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("0")},
            {Factory.createScalar("0"), Factory.createScalar("1")},
        });
        System.out.println("10. 단위행렬 생성");
        System.out.println("기댓값: \n" + expectedAnswer10);
        System.out.println("결과: \n" + mD);
        System.out.println(mD.equals(expectedAnswer10) ? "통과\n" : "실패\n");

        // 11v. (벡터) 특정 위치의 요소를 지정/조회할 수 있다.
        vA = Factory.createVector(3, Factory.createScalar("5"));
        Scalar[] arr3 = {Factory.createScalar("5"), Factory.createScalar("5"), Factory.createScalar("10")};
        Vector expectedAnswer11va = Factory.createVector(arr3);
        int idx11v = 2;
        System.out.println("11v. (벡터) 특정 위치의 요소를 지정/조회할 수 있다.");
        System.out.println("원본 벡터: " + vA);
        System.out.println("지정 인덱스: " + (int)(idx11v+1) + "에 값을 10으로 지정");
        System.out.println("기댓값: " + expectedAnswer11va);
        vA.setValue(2, Factory.createScalar("10"));
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer11va) ? "통과\n" : "실패\n");

        Scalar expectedAnswer11vb = Factory.createScalar("10");
        System.out.println("조회 인덱스: " + (int)(idx11v+1));
        System.out.println("기댓값: " + expectedAnswer11vb);
        System.out.println("결과: " + vA.getValue(idx11v));
        System.out.println(vA.getValue(idx11v).equals(expectedAnswer11vb) ? "통과\n" : "실패\n");

        // 11m. (행렬) 특정 위치의 요소를 지정/조회할 수 있다.
        mA = Factory.createIdentityMatrix(2);
        Matrix expectedAnswer11ma = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("10"), Factory.createScalar("0")},
                {Factory.createScalar("0"), Factory.createScalar("1")},
        });
        int idx11m = 0;
        System.out.println("11m. (행렬) 특정 위치의 요소를 지정/조회할 수 있다.");
        System.out.println("원본 행렬: \n" + mA);
        System.out.println("지정 인덱스: " + (idx11m) + "," + (idx11m) + "에 10으로 지정");
        mA.setValue(idx11m, idx11m, Factory.createScalar("10"));
        System.out.println("기댓값: \n" + expectedAnswer11ma);
        System.out.println("결과: \n" + mA);
        System.out.println(mA.equals(expectedAnswer11ma) ? "통과\n" : "실패");

        Scalar expectedAnswer11mb = Factory.createScalar("10");
        System.out.println("조회 인덱스: " + (idx11m) + "," + (idx11m));
        System.out.println("기댓값: " + expectedAnswer11mb);
        System.out.println("결과: " + mA.getValue(idx11m, idx11m));
        System.out.println(mA.getValue(idx11m, idx11m).equals(expectedAnswer11mb) ? "통과\n" : "실패\n");

        // 12. (only 스칼라) 값을 지정/조회할 수 있다.
        sA = Factory.createScalar("10.24");
        Scalar expectedAnswer12 = Factory.createScalar("10.24");
        System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
        System.out.println("원본 스칼라: " + sA);
        System.out.println("지정 값: 10.24");
        sA.setValue("10.24");
        System.out.println("기댓값: " + expectedAnswer12);
        System.out.println("조회 결과: " + sA.getValue());
        System.out.println(sA.equals(expectedAnswer12) ? "통과\n" : "실패\n");

        // 13v. (only 벡터) 크기 정보를 조회할 수 있다.
        vA = Factory.createVector(2, Factory.createScalar("10"));
        System.out.println("13v. (only 벡터) 크기 정보를 조회할 수 있다.");
        System.out.println("원본 벡터: " + vA);
        System.out.println("벡터 크기: " + 2);
        System.out.println("기댓값: 벡터 크기=2");
        System.out.println("결과: " + "벡터 크기=" + vA.size());
        System.out.println((vA.size() == 2) ? "통과\n" : "실패\n");

        // 13m. (only 행렬) 크기 정보를 조회할 수 있다.
        mA = Factory.createMatrix(3, 3, Factory.createScalar("24"));
        System.out.println("13m. (only 벡행렬) 크기 정보를 조회할 수 있다.");
        System.out.println("원본 행렬: \n" + mA);
        System.out.println("행렬 행: " + 3 + ", 열: " + 3);
        System.out.println("기댓값: 행렬 행=3, 열=3");
        System.out.println("결과: " + "행렬 행=" + mA.rowSize() + ", 열=" + mA.colSize());
        System.out.println((mA.rowSize() == 3 && mA.colSize() == 3) ? "통과\n" : "실패\n");

        // 14s. toString() 객체를 콘솔에 출력할 수 있다.
        sA = Factory.createScalar("10.24");
        Scalar expectedAnswer14_1 = Factory.createScalar("10.24");
        System.out.println("14s. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("스칼라: " + sA);
        System.out.println("기댓값: " + expectedAnswer14_1);
        try{
            System.out.println("결과: " + sA);
            System.out.println("통과\n");
        }catch (TensorException e){
            System.out.println("실패\n");
        }

        // 14v. toString() 객체를 콘솔에 출력할 수 있다.
        vA = Factory.createVector(3, Factory.createScalar("10"));
        Vector expectedAnswer14_2 = Factory.createVector(new Scalar[]{
                Factory.createScalar("10"),
                Factory.createScalar("10"),
                Factory.createScalar("10")
        });
        System.out.println("14v. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("벡터: " + vA);
        System.out.println("기댓값: " + expectedAnswer14_2);
        try{
            System.out.println("결과: " + vA);
            System.out.println("통과\n");
        }catch (TensorException e){
            System.out.println("실패\n");
        }

        // 14m. toString() 객체를 콘솔에 출력할 수 있다.
        mA = Factory.createMatrix(2, 2, Factory.createScalar("24"));
        Matrix expectedAnswer14_3 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("24"), Factory.createScalar("24")},
                {Factory.createScalar("24"), Factory.createScalar("24")}
        });
        System.out.println("14m. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("행렬: \n" + mA);
        System.out.println("기댓값:\n" + expectedAnswer14_3);
        try{
            System.out.println("결과:\n" + mA);
            System.out.println("통과\n");
        }catch (TensorException e){
            System.out.println("실패\n");
        }

        // 15s. (스칼라) equals() 객체의 동등성 판단
        sA = Factory.createScalar("10.24");
        Scalar s2 = Factory.createScalar("10.24");
        System.out.println("15s. (스칼라) equals() 객체의 동등성 판단");
        System.out.println("비교 대상 1: " + sA);
        System.out.println("비교 대상 2: " + s2);
        System.out.println("기댓값: 같다");
        System.out.println("결과: " + (sA.equals(s2) ? "같다" : "다르다"));
        System.out.println(sA.equals(s2) ? "통과\n" : "실패\n");

        // 15v. (벡터) equals() 객체의 동등성 판단
        vA = Factory.createVector(2, Factory.createScalar("24"));
        Vector v2 = Factory.createVector(new Scalar[]{
                Factory.createScalar("24"),
                Factory.createScalar("24")
        });
        System.out.println("15v. (벡터) equals() 객체의 동등성 판단");
        System.out.println("비교 대상 1: " + vA);
        System.out.println("비교 대상 2: " + v2);
        System.out.println("기댓값: 같다");
        System.out.println("결과: " + (vA.equals(v2) ? "같다" : "다르다"));
        System.out.println(vA.equals(v2) ? "통과\n" : "실패\n");

        // 15m. (행렬) equals() 객체의 동등성 판단
        mA = Factory.createIdentityMatrix(2);
        Matrix m2 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1"), Factory.createScalar("0")},
                {Factory.createScalar("0"), Factory.createScalar("1")},
        });
        System.out.println("15m. (행렬) equals() 객체의 동등성 판단");
        System.out.println("비교 대상 1: \n" + mA);
        System.out.println("비교 대상 2: \n" + m2);
        System.out.println("기댓값: 같다");
        System.out.println("결과: " + (mA.equals(m2) ? "같다" : "다르다"));
        System.out.println(mA.equals(m2) ? "통과\n" : "실패\n");
        
        // 16. Comparable 스칼라 대소 비교
        sA = Factory.createScalar("1024");
        Scalar s3 = Factory.createScalar("1024");
        String expectedAnswer16_1 = "서로 같다";
        System.out.println("16. Comparable 스칼라 대소 비교");
        System.out.println("비교 대상 1: " + sA);
        System.out.println("비교 대상 2: " + s3);
        System.out.println("기댓값: 서로 같다");
        if(sA.compareTo(s3) == 0){
            System.out.println("결과: " + "비교 대상 1: " + sA + "와 비교 대상 2: " + s3 + "서로 같다" );
            String expectedAnswer16_2 = "서로 같다";
            System.out.println(expectedAnswer16_1.equals(expectedAnswer16_2) ? "통과\n" : "실패\n");
        } else {
            System.out.println("결과: " + "비교 대상 1: " + sA + "이 비교 대상 2: " + s3 + "보다 " + (sA.compareTo(s3) > 0 ? "크다" : "작다"));
            String expectedAnswer16_2 = sA.compareTo(s3) > 0 ? "크다" : "작다";
            System.out.println(expectedAnswer16_1.equals(expectedAnswer16_2) ? "통과\n" : "실패\n");
        }

        // 17s. (스칼라) clone() 객체 복제
        sA = Factory.createScalar("10.24");
        Scalar expectedAnswer17s = Factory.createScalar("10.24");
        Scalar s4 = sA.clone();
        System.out.println("17s. (스칼라) clone() 객체 복제");
        System.out.println("기댓값: " + expectedAnswer17s);
        System.out.println("복제된 스칼라: " + s4);
        System.out.println(s4.equals(expectedAnswer17s) && s4 != sA? "통과\n" : "실패\n");

        // 17v. (벡터) clone() 객체 복제
        vA = Factory.createVector(3, Factory.createScalar("24"));
        vA.setValue(1, Factory.createScalar("5"));
        Vector expectedAnswer17v = Factory.createVector(new Scalar[]{
                Factory.createScalar("24"),
                Factory.createScalar("5"),
                Factory.createScalar("24"),
        });
        Vector v3 = vA.clone();
        System.out.println("17v. (벡터) clone() 객체 복제");
        System.out.println("기댓값: " + expectedAnswer17v);
        System.out.println("복제된 벡터: " + v3);
        System.out.println(v3.equals(expectedAnswer17v)  && v3 != vA? "통과\n" : "실패\n");

        // 17m. (행렬) clone() 객체 복제
        mA = Factory.createMatrix(2, 2, Factory.createScalar("10"));
        mA.setValue(1, 1, Factory.createScalar("24"));
        Matrix expectedAnswer17m = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("10"), Factory.createScalar("10")},
                {Factory.createScalar("10"), Factory.createScalar("24")},
        });
        Matrix m3 = mA.clone();
        System.out.println("17m. (행렬) clone() 객체 복제");
        System.out.println("기댓값:\n" + expectedAnswer17m);
        System.out.println("복제된 행렬:\n" + m3);
        System.out.println(m3.equals(expectedAnswer17m) && m3 != mA ? "통과\n" : "실패\n");

        // 18. 스칼라 덧셈
        sA = Factory.createScalar("10");
        Scalar sAdd = Factory.createScalar("24");
        Scalar expectedAnswer18 = Factory.createScalar("34");
        System.out.println("18. 스칼라 덧셈");
        System.out.println("원본: " + sA);
        System.out.println("더할 값: " + sAdd);
        System.out.println("기댓값: " + expectedAnswer18);
        sA.add(sAdd);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer18) ? "통과\n" : "실패\n");

        // 19. 스칼라 곱셈
        sA = Factory.createScalar("10");
        Scalar sMul = Factory.createScalar("2.4");
        Scalar expectedAnswer19 = Factory.createScalar("24");
        System.out.println("19. 스칼라 곱셈");
        System.out.println("원본: " + sA);
        System.out.println("곱할 값: " + sMul);
        System.out.println("기댓값: " + expectedAnswer19);
        sA.multiply(sMul);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer19) ? "통과\n" : "실패\n");


        // 20. 벡터 덧셈
        vA = Factory.createVector(4, Factory.createScalar("2.4"));
        Vector vD = Factory.createVector(new Scalar[]{
            Factory.createScalar("1"),
            Factory.createScalar("2"),
            Factory.createScalar("3"),
            Factory.createScalar("4")
        });
        Vector expectedAnswer20 = Factory.createVector(new Scalar[]{
            Factory.createScalar("3.4"),
            Factory.createScalar("4.4"),
            Factory.createScalar("5.4"),
            Factory.createScalar("6.4")
        });
        System.out.println("20. 벡터 덧셈");
        System.out.println("원본: " + vA);
        System.out.println("더할 벡터: " + vD);
        System.out.println("기댓값: " + expectedAnswer20);
        vA.add(vD);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer20) ? "통과\n" : "실패\n");

        // 21. 벡터 스칼라 곱셈
        vA = Factory.createVector(3, Factory.createScalar("10"));
        Scalar mulS = Factory.createScalar("2.4");
        Vector expectedAnswer21 = Factory.createVector(new Scalar[]{
            Factory.createScalar("24"),
            Factory.createScalar("24"),
            Factory.createScalar("24")
        });
        System.out.println("21. 벡터 스칼라 곱셈");
        System.out.println("원본: " + vA);
        System.out.println("곱할 값: " + mulS);
        System.out.println("기댓값: " + expectedAnswer21);
        vA.multiply(mulS);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer21) ? "통과\n" : "실패\n");

        // 22. 행렬 덧셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("4"));
        Matrix mF = Factory.createMatrix(2, 3, Factory.createScalar("6"));
        Matrix expectedAnswer22 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("10"),  Factory.createScalar("10"), Factory.createScalar("10")},
            {Factory.createScalar("10"), Factory.createScalar("10"), Factory.createScalar("10")}
        });
        System.out.println("22. 행렬 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값: \n" + expectedAnswer22);
        Matrix result22 = Tensors.add(mA, mF);
        System.out.println("결과:\n" + result22);
        System.out.println(result22.equals(expectedAnswer22) ? "통과\n" : "실패\n");

        // 23. 행렬 곱셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("3"));
        Matrix mE = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("4")},
            {Factory.createScalar("2"), Factory.createScalar("5")},
            {Factory.createScalar("3"), Factory.createScalar("6")}
        });
        Matrix expectedAnswer23 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("18"), Factory.createScalar("45")},
            {Factory.createScalar("18"), Factory.createScalar("45")}
        });
        System.out.println("23. 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer23);
        mA.multiply(mE);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer23) ? "통과\n" : "실패\n");

        // 24. 전달받은 두 스칼라의 덧셈
        sA = Factory.createScalar("10");
        sB = Factory.createScalar("2.4");
        Scalar expectedAnswer24 = Factory.createScalar("12.4");
        Scalar result24 = Tensors.add(sA, sB);
        System.out.println("24. 전달받은 두 스칼라의 덧셈");
        System.out.println(sA + " + " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer24);
        System.out.println("결과: " + result24);
        System.out.println(result24.equals(expectedAnswer24) ? "통과\n" : "실패\n");

        // 25. 전달받은 두 스칼라의 곱셈
        sA = Factory.createScalar("2.4");
        sB = Factory.createScalar("10");
        Scalar expectedAnswer25 = Factory.createScalar("24");
        Scalar result25 = Tensors.multiply(sA, sB);
        System.out.println("25. 전달받은 두 스칼라의 곱셈");
        System.out.println(sA + " * " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer25);
        System.out.println("결과: " + result25);
        System.out.println(result25.equals(expectedAnswer25) ? "통과\n" : "실패\n");

        // 26. 전달받은 두 벡터의 덧셈
        vA = Factory.createVector(3, Factory.createScalar("24"));
        vA.setValue(1, Factory.createScalar("10"));
        vD = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("6"),
            Factory.createScalar("9"),
        });
        Vector expectedAnswer26 = Factory.createVector(new Scalar[]{
            Factory.createScalar("27"),
            Factory.createScalar("16"),
            Factory.createScalar("33"),
        });
        Vector result26 = Tensors.add(vA, vD);
        System.out.println("26. 전달받은 두 벡터의 덧셈");
        System.out.println(vA + " + " + vD + " = ");
        System.out.println("기댓값: " + expectedAnswer26);
        System.out.println("결과: " + result26);
        System.out.println(result26.equals(expectedAnswer26) ? "통과\n" : "실패\n");

        // 27. 전달받은 스칼라와 벡터의 곱셈
        vA = Factory.createVector(2, Factory.createScalar("10.24"));
        sA = Factory.createScalar("10");
        Vector expectedAnswer27 = Factory.createVector(new Scalar[]{
            Factory.createScalar("102.4"),
            Factory.createScalar("102.4"),
        });
        Vector result27 = Tensors.multiply(vA, sA);
        System.out.println("27. 전달받은 스칼라와 벡터의 곱셈");
        System.out.println(vA + " * " + sA + " = ");
        System.out.println("기댓값: " + expectedAnswer27);
        System.out.println("결과: " + result27);
        System.out.println(result27.equals(expectedAnswer27) ? "통과\n" : "실패\n");

        // 28. 전달받은 두 행렬의 덧셈
        mA = Factory.createMatrix(2, 2, Factory.createScalar("24"));
        mF = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("9"), Factory.createScalar("8")},
            {Factory.createScalar("7"), Factory.createScalar("6")}
        });
        Matrix expectedAnswer28 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("33"), Factory.createScalar("32")},
            {Factory.createScalar("31"), Factory.createScalar("30")}
        });
        Matrix result28 = Tensors.add(mA, mF);
        System.out.println("28. 전달받은 두 행렬의 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer28);
        System.out.println("결과:\n" + result28);
        System.out.println(result28.equals(expectedAnswer28) ? "통과\n" : "실패\n");

        // 29. static 행렬 곱셈
        mA = Factory.createMatrix(2, 2, Factory.createScalar("12"));
        mE = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("6"), Factory.createScalar("12")},
            {Factory.createScalar("3"), Factory.createScalar("15")},
        });
        Matrix expectedAnswer29 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("108"), Factory.createScalar("324")},
            {Factory.createScalar("108"), Factory.createScalar("324")}
        });
        Matrix result29 = Tensors.multiply(mA, mE);
        System.out.println("29. static 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer29);
        System.out.println("결과:\n" + result29);
        System.out.println(result29.equals(expectedAnswer29) ? "통과\n" : "실패\n");

        // 30. n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환
        vA = Factory.createVector(3, Factory.createScalar("24"));
        vA.setValue(1, Factory.createScalar("10"));
        Matrix expectedAnswer30 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("24")},
            {Factory.createScalar("10")},
            {Factory.createScalar("24")}
        });
        System.out.println("30. n-차원 벡터 객체는 자신으로부터 nx1 행렬을 생성하여 반환");
        System.out.println("원본 벡터: " + vA);
        System.out.println("기댓값:\n" + expectedAnswer30);
        System.out.println("결과:\n" + vA.toVerticalMatrix());
        System.out.println(vA.toVerticalMatrix().equals(expectedAnswer30) ? "통과\n" : "실패\n");

        // 31. n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환
        vA = Factory.createVector(3, Factory.createScalar("2.4"));
        vA.setValue(1, Factory.createScalar("100"));
        Matrix expectedAnswer31 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2.4"), Factory.createScalar("100"), Factory.createScalar("2.4")}
        });
        System.out.println("31. n-차원 벡터 객체는 자신으로부터 1xn 행렬을 생성하여 반환");
        System.out.println("원본 벡터: " + vA);
        System.out.println("기댓값: " + expectedAnswer31);
        System.out.println("결과: " + vA.toHorizontalMatrix());
        System.out.println(vA.toHorizontalMatrix().equals(expectedAnswer31) ? "통과\n" : "실패\n");

        // 32. 행렬은 다른 행렬과 가로로 합쳐질 수 있다.
        mA = Factory.createMatrix(2, 3, Factory.createScalar("10"));
        mB = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("24"), Factory.createScalar("6"), Factory.createScalar("24")},
            {Factory.createScalar("3"), Factory.createScalar("24"), Factory.createScalar("9")}
        });
        Matrix expectedAnswer32 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("10"), Factory.createScalar("10"), Factory.createScalar("10"), Factory.createScalar("24"), Factory.createScalar("6"), Factory.createScalar("24")},
            {Factory.createScalar("10"), Factory.createScalar("10"), Factory.createScalar("10"), Factory.createScalar("3"), Factory.createScalar("24"), Factory.createScalar("9")}
        });
        System.out.println("32. 행렬은 다른 행렬과 가로로 합쳐질 수 있다.");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB);
        System.out.println("기댓값:\n" + expectedAnswer32);
        System.out.println("결과:\n" + Tensors.attachHMatrix(mA, mB));
        System.out.println(Tensors.attachHMatrix(mA, mB).equals(expectedAnswer32) ? "통과\n" : "실패\n");

        // 33. 행렬은 다른 행렬과 세로로 합쳐질 수 있다.
        mA = Factory.createMatrix(2, 2, Factory.createScalar("24"));
        mB = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("0")},
            {Factory.createScalar("0"), Factory.createScalar("3")}
        });
        Matrix expectedAnswer33 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("24"), Factory.createScalar("24")},
            {Factory.createScalar("24"), Factory.createScalar("24")},
            {Factory.createScalar("2"), Factory.createScalar("0")},
            {Factory.createScalar("0"), Factory.createScalar("3")}
        });
        System.out.println("33. 행렬은 다른 행렬과 세로로 합쳐질 수 있다.");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB);
        System.out.println("기댓값:\n" + expectedAnswer33);
        System.out.println("결과:\n" + Tensors.attachVMatrix(mA, mB));
        System.out.println(Tensors.attachVMatrix(mA, mB).equals(expectedAnswer33) ? "통과\n" : "실패\n");

        // 34. 행렬에서 특정 행을 벡터 형태로 추출
        mA = Factory.createMatrix(2, 2, Factory.createScalar("0"));
        mA.setValue(0, 0, Factory.createScalar("2"));
        mA.setValue(0, 1, Factory.createScalar("0"));
        mA.setValue(1, 0, Factory.createScalar("0"));
        mA.setValue(1, 1, Factory.createScalar("3"));
        int rowIdx = 0;
        int colIdx = 1;
        Vector expectedAnswer34 = Factory.createVector(new Scalar[]{
            Factory.createScalar("2"),
            Factory.createScalar("0")
        });
        System.out.println("34. 행렬에서 특정 행을 벡터 형태로 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 행 인덱스: " + rowIdx);
        System.out.println("기댓값: " + expectedAnswer34);
        Vector result34 = mA.getRowVector(rowIdx);
        System.out.println("결과: " + result34);
        System.out.println(result34.equals(expectedAnswer34) ? "통과\n" : "실패\n");

        // 35. 행렬에서 특정 열을 벡터 형태로 추출
        Vector expectedAnswer35 = Factory.createVector(new Scalar[]{
            Factory.createScalar("0"),
            Factory.createScalar("3")
        });
        System.out.println("35. 행렬에서 특정 열을 벡터 형태로 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 열 인덱스: " + colIdx);
        System.out.println("기댓값: " + expectedAnswer35);
        Vector result35 = mA.getColVector(colIdx);
        System.out.println("결과: " + result35);
        System.out.println(result35.equals(expectedAnswer35) ? "통과\n" : "실패\n");

        // 36. 행렬은 특정 범위의 부분 행렬 추출
        mA = Factory.createMatrix(3, 3, Factory.createScalar("2"));
        mA.setValue(0, 0, Factory.createScalar("0"));
        mA.setValue(0, 1, Factory.createScalar("1"));
        mA.setValue(1, 0, Factory.createScalar("2"));
        mA.setValue(1, 1, Factory.createScalar("3"));
        Matrix expectedAnswer36 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("0"),Factory.createScalar("1")},
            {Factory.createScalar("2"),Factory.createScalar("3")}
        });
        System.out.println("36.행렬은 특정 범위의 부분 행렬 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 범위: (0,0)부터 (2,2)까지");
        System.out.println("기댓값:\n" + expectedAnswer36);
        Matrix result36 = mA.extractSubMatrix(0, 2, 0, 2);
        System.out.println("결과:\n" + result36);
        System.out.println(result36.equals(expectedAnswer36) ? "통과\n" : "실패\n");

        // 37. 행렬에서 특정 하나의 행과 열을 제외한 부분 행렬
        Matrix expectedAnswer37 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("3"), Factory.createScalar("2")},
            {Factory.createScalar("2"), Factory.createScalar("2")}
        });
        System.out.println("37. 행렬에서 특정 하나의 행과 열을 제외한 부분 행렬");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("제거할 행과 열: (0,0)");
        System.out.println("기댓값:\n" + expectedAnswer37);
        Matrix result37 = mA.minorSubMatrix(0, 0);
        System.out.println("결과:\n" + result37);
        System.out.println(result37.equals(expectedAnswer37) ? "통과\n" : "실패\n");

        // 38. 행렬은 전치행렬을 구할 수 있다.
        Matrix expectedAnswer38 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("0"), Factory.createScalar("2"),Factory.createScalar("2")},
            {Factory.createScalar("1"), Factory.createScalar("3"),Factory.createScalar("2")},
            {Factory.createScalar("2"), Factory.createScalar("2"),Factory.createScalar("2")}
        });
        System.out.println("38. 행렬은 전치행렬을 구할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값:\n" + expectedAnswer38);
        Matrix result38 = mA.transposeMatrix();
        System.out.println("전치 결과:\n" + result38);
        System.out.println(result38.equals(expectedAnswer38) ? "통과\n" : "실패\n");

        // 39. 행렬은 대각 요소의 합을 구할 수 있다.
        Scalar expectedAnswer39 = Factory.createScalar("5");
        Scalar result39 = mA.trace();
        System.out.println("39. 행렬은 대각 요소의 합을 구할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer39);
        System.out.println("대각합: " + result39);
        System.out.println(result39.equals(expectedAnswer39) ? "통과\n" : "실패\n");

        // 40. 정사각 행렬 판별
        boolean expectedAnswer40 = true;
        System.out.println("40. 정사각 행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer40);
        boolean result40 = mA.isSquare();
        System.out.println("정사각 행렬 여부: " + result40);
        System.out.println(result40 == expectedAnswer40 ? "통과\n" : "실패\n");

        // 41. 상삼각행렬 판별
        boolean expectedAnswer41 = false;
        System.out.println("41. 상삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer41);
        boolean result41 = mA.isUpperTriangular();
        System.out.println("상삼각행렬 여부: " + result41);
        System.out.println(result41 == expectedAnswer41 ? "통과\n" : "실패\n");

        // 42. 하삼각행렬 판별
        boolean expectedAnswer42 = false;
        System.out.println("42. 하삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer42);
        boolean result42 = mA.isLowerTriangular();
        System.out.println("하삼각행렬 여부: " + result42);
        System.out.println(result42 == expectedAnswer42 ? "통과\n" : "실패\n");

        // 43. 단위행렬 판별
        mA = Factory.createIdentityMatrix(3);
        boolean expectedAnswer43 = true;
        System.out.println("43. 단위행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer43);
        boolean result43 = mA.isIdentity();
        System.out.println("단위행렬 여부: " + result43);
        System.out.println(result43 == expectedAnswer43 ? "통과\n" : "실패\n");

        // 44. 영행렬 판별
        boolean expectedAnswer44 = false;
        System.out.println("44. 영행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer44);
        boolean result44 = mA.isZero();
        System.out.println("영행렬 여부: " + result44);
        System.out.println(result44 == expectedAnswer44 ? "통과\n" : "실패\n");

        // 45. 행렬은 특정 두 행의 위치를 맞교환할 수 있다.
        Matrix expectedAnswer45 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("0"), Factory.createScalar("0"), Factory.createScalar("1")},
            {Factory.createScalar("0"), Factory.createScalar("1"), Factory.createScalar("0")},
            {Factory.createScalar("1"), Factory.createScalar("0"), Factory.createScalar("0")}
        });
        System.out.println("45. 행렬은 특정 두 행의 위치를 맞교환할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 행: 0행과 2행");
        System.out.println("기댓값:\n" + expectedAnswer45);
        mA.rowSwap(0, 2);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer45) ? "통과\n" : "실패\n");

        // 46. 행렬은 특정 두 열의 위치를 맞교환할 수 있다.
        Matrix expectedAnswer46 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("0"), Factory.createScalar("0"), Factory.createScalar("1")},
                {Factory.createScalar("1"), Factory.createScalar("0"), Factory.createScalar("0")},
                {Factory.createScalar("0"), Factory.createScalar("1"), Factory.createScalar("0")}
        });
        System.out.println("46. 행렬은 특정 두 열의 위치를 맞교환할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 열: 0열과 1열");
        System.out.println("기댓값:\n" + expectedAnswer46);
        mA.colSwap(0, 1);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer46) ? "통과\n" : "실패\n");

        // 47. 행렬은 특정 행에 상수배(스칼라)를 할 수 있다.
        mA = Factory.createMatrix(3, 3, Factory.createScalar("2.4"));
        Matrix expectedAnswer47 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2.4"), Factory.createScalar("2.4"), Factory.createScalar("2.4")},
            {Factory.createScalar("7.2"), Factory.createScalar("7.2"), Factory.createScalar("7.2")},
            {Factory.createScalar("2.4"), Factory.createScalar("2.4"), Factory.createScalar("2.4")}
        });
        System.out.println("47. 행렬은 특정 행에 상수배(스칼라)를 할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 행: 1행, 스칼라: 3");
        System.out.println("기댓값:\n" + expectedAnswer47);
        mA.rowMultiply(1, Factory.createScalar("3"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer47) ? "통과\n" : "실패\n");

        // 48. 행렬은 특정 열에 상수배(스칼라)를 할 수 있다.
        Matrix expectedAnswer48 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("2.4"), Factory.createScalar("7.2"), Factory.createScalar("2.4")},
                {Factory.createScalar("7.2"), Factory.createScalar("21.6"), Factory.createScalar("7.2")},
                {Factory.createScalar("2.4"), Factory.createScalar("7.2"), Factory.createScalar("2.4")}
        });
        System.out.println("48. 행렬은 특정 열에 상수배(스칼라)를 할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 열: 1열, 스칼라: 3");
        System.out.println("기댓값:\n" + expectedAnswer48);
        mA.colMultiply(1, Factory.createScalar("3"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer48) ? "통과\n" : "실패\n");

        // 49. 행렬은 특정 행에 다른 행의 상수배를 더할 수 있다.
        Matrix expectedAnswer49 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("7.2"), Factory.createScalar("21.6"), Factory.createScalar("7.2")},
                {Factory.createScalar("7.2"), Factory.createScalar("21.6"), Factory.createScalar("7.2")},
                {Factory.createScalar("2.4"), Factory.createScalar("7.2"), Factory.createScalar("2.4")}
        });
        System.out.println("49. 행렬은 특정 행에 다른 행의 상수배를 더할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("대상 행: 0행, 더할 행: 2행, 스칼라: 2");
        System.out.println("기댓값:\n" + expectedAnswer49);
        mA.rowAddOtherRow(0, 2, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer49) ? "통과\n" : "실패\n");

        // 50. 행렬은 특정 열에 다른 열의 상수배를 더할 수 있다.
        Matrix expectedAnswer50 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("21.6"), Factory.createScalar("21.6"), Factory.createScalar("7.2")},
                {Factory.createScalar("21.6"), Factory.createScalar("21.6"), Factory.createScalar("7.2")},
                {Factory.createScalar("7.2"), Factory.createScalar("7.2"), Factory.createScalar("2.4")}
        });
        System.out.println("50. 행렬은 특정 열에 다른 열의 상수배를 더할 수 있다.");
        System.out.println("원본 행렬:\n" + mA);    
        System.out.println("대상 열: 0열, 더할 열: 2열, 스칼라: 2");
        System.out.println("기댓값:\n" + expectedAnswer50);
        mA.colAddOtherCol(0, 2, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer50) ? "통과\n" : "실패\n");

        // 51. RREF 변환
        mA = Factory.createMatrix(2, 2, Factory.createScalar("1"));
        mA.setValue(0, 0, Factory.createScalar("2"));
        mA.setValue(0, 1, Factory.createScalar("4"));
        mA.setValue(1, 1, Factory.createScalar("3"));
        Matrix expectedAnswer51 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("0")},
            {Factory.createScalar("0"), Factory.createScalar("1")}
        });
        System.out.println("51. RREF 변환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값:\n" + expectedAnswer51);
        Matrix result51 = mA.getRREF();
        System.out.println("RREF 변환 결과:\n" + result51);
        System.out.println(result51.equals(expectedAnswer51) ? "통과\n" : "실패\n");

        // 52. RREF 판별
        boolean expectedAnswer52 = false;
        System.out.println("52. RREF 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer52);
        System.out.println("RREF 여부: " + (mA.isRREF() ? "RREF임" : "RREF가 아님"));
        System.out.println(mA.isRREF() == expectedAnswer52 ? "통과\n" : "실패\n");

        // 53. 행렬식 구하기
        Scalar expectedAnswer53 = Factory.createScalar("2");
        Scalar result53 = mA.getDeterminant();
        System.out.println("53. 행렬식 구하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer53);
        System.out.println("행렬식: " + result53);
        System.out.println(result53.equals(expectedAnswer53) ? "통과\n" : "실패\n");

        // 54. 역행렬 구하기
        Matrix expectedAnswer54 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1.5"), Factory.createScalar("-2")},
            {Factory.createScalar("-0.5"), Factory.createScalar("1")}
        });
        System.out.println("54. 역행렬 구하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값:\n" + expectedAnswer54);
        Matrix result54 = mA.getInverseMatrix();
        System.out.println("역행렬:\n" + result54);
        System.out.println(result54.equals(expectedAnswer54) ? "통과\n" : "실패\n");

        System.out.println("끝");
    }
} 