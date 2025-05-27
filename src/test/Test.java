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

        // 8. 행렬 생성 (csv 파일) - 파일이 없으므로 생략 또는 주석처리
        // System.out.println("8. 행렬 생성 (csv 파일)");
        // System.out.println(Factory.createMatrix("matrix.csv"));

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
        System.out.println("2차원 배열로부터 m x n 행렬 생성");
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
        System.out.println("지정 인덱스: " + (idx11m+1) + "," + (idx11m+1) + "에 10으로 지정");
        mA.setValue(idx11m, idx11m, Factory.createScalar("10"));
        System.out.println("기댓값: \n" + expectedAnswer11ma);
        System.out.println("결과: \n" + mA);
        System.out.println(mA.equals(expectedAnswer11ma) ? "통과\n" : "실패");

        Scalar expectedAnswer11mb = Factory.createScalar("10");
        System.out.println("조회 인덱스: " + (idx11m+1) + "," + (idx11m+1));
        System.out.println("기댓값: " + expectedAnswer11mb);
        System.out.println("결과: " + mA.getValue(idx11m, idx11m));
        System.out.println(mA.getValue(idx11m, idx11m).equals(expectedAnswer11mb) ? "통과\n" : "실패\n");
/*

        // 12. (only 스칼라) 값을 지정/조회할 수 있다.
        sA = Factory.createScalar("3.14");
        Scalar expectedAnswer12 = Factory.createScalar("4.5"); // 4.5로 setValue 했으니 맞음
        System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
        System.out.println("원본 스칼라: " + sA);
        System.out.println("지정 값: 4.5");
        sA.setValue("4.5");
        System.out.println("기댓값: " + expectedAnswer12);
        System.out.println("조회 결과: " + sA.getValue());
        System.out.println(sA.equals(expectedAnswer12) ? "통과" : "실패");
        System.out.println("");

        // 13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.
        vA = Factory.createVector(4, Factory.createScalar("2"));
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        System.out.println("13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.");
        System.out.println("원본 벡터: " + vA);
        System.out.println("원본 행렬: \n" + mA);
        System.out.println("벡터 크기: " + vA.size());
        System.out.println("행렬 행: " + mA.rowSize() + ", 열: " + mA.colSize());
        System.out.println("기댓값: 벡터 크기=4, 행렬 행=2, 열=3");
        System.out.println("");

        // 14. toString() 객체를 콘솔에 출력할 수 있다.
        sA = Factory.createScalar("3.14");
        vA = Factory.createVector(4, Factory.createScalar("2"));
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        Scalar expectedAnswer14_1 = Factory.createScalar("3.14");
        Vector expectedAnswer14_2 = Factory.createVector(new Scalar[]{
            Factory.createScalar("2"),
            Factory.createScalar("2"),
            Factory.createScalar("2"),
            Factory.createScalar("2")
        });
        Matrix expectedAnswer14_3 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")},
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")}
        });
        System.out.println("14. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("스칼라: " + sA);
        System.out.println("벡터: " + vA);
        System.out.println("행렬: " + mA);
        System.out.println("기댓값: 스칼라=" + expectedAnswer14_1 + ", 벡터=" + expectedAnswer14_2 + ", 행렬=" + expectedAnswer14_3);
        System.out.println("");

        // 15. equals() 객체의 동등성 판단
        sA = Factory.createScalar("3.14");
        Scalar s2 = Factory.createScalar("333");
        Scalar expectedAnswer15 = Factory.createScalar("333"); // 비교 대상 2
        System.out.println("15. equals() 객체의 동등성 판단");
        System.out.println("비교 대상 1: " + sA);
        System.out.println("비교 대상 2: " + s2);
        if (sA == null || s2 == null) {
            //throw new TensorException("비교할 수 없는 null 값이 있습니다.");
        }
        System.out.println("기댓값: " + expectedAnswer15);
        System.out.println("결과: " + (sA.equals(s2) ? "같아용" : "달라용"));
        System.out.println("");

        // 16. Comparable 스칼라 대소 비교
        sA = Factory.createScalar("3.14");
        Scalar s3 = Factory.createScalar("10");
        Scalar expectedAnswer16 = Factory.createScalar("10"); // 비교 대상 2
        System.out.println("16. Comparable 스칼라 대소 비교");
        System.out.println("비교 대상 1: " + sA);
        System.out.println("비교 대상 2: " + s3);
        System.out.println("기댓값: " + expectedAnswer16);
        System.out.println("결과: " + "비교 대상 1"+sA+ "이 비교 대상 2"+s3+ "보다 " + (sA.compareTo(s3) > 0 ? "크다" : "작다"));
        System.out.println("");

        // 17. clone() 객체 복제
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        Vector expectedAnswer17 = Factory.createVector(new Scalar[]{
            Factory.createScalar("2"),
            Factory.createScalar("5"),
            Factory.createScalar("2"),
            Factory.createScalar("2")
        });
        Vector v2 = vA.clone();
        System.out.println("17. clone() 객체 복제");
        System.out.println("복제된 벡터: " + v2);
        System.out.println("기댓값: " + expectedAnswer17);
        System.out.println(v2.equals(expectedAnswer17) ? "통과" : "실패");
        System.out.println("");

        // 18. 스칼라 덧셈
        sA = Factory.createScalar("3.14");
        Scalar sAdd = Factory.createScalar("2.5");
        Scalar expectedAnswer18 = Factory.createScalar("5.64");
        System.out.println("18. 스칼라 덧셈");
        System.out.println("원본: " + sA);
        System.out.println("더할 값: " + sAdd);
        System.out.println("기댓값: " + expectedAnswer18);
        sA.add(sAdd);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer18) ? "통과" : "실패");
        System.out.println("");

        // 19. 스칼라 곱셈
        sA = Factory.createScalar("3.14");
        Scalar sMul = Factory.createScalar("2");
        Scalar expectedAnswer19 = Factory.createScalar("6.28");
        System.out.println("19. 스칼라 곱셈");
        System.out.println("원본: " + sA);
        System.out.println("곱할 값: " + sMul);
        System.out.println("기댓값: " + expectedAnswer19);
        sA.multiply(sMul);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer19) ? "통과" : "실패");
        System.out.println("");

        // 20. 벡터 덧셈
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        Vector vD = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("4"),
            Factory.createScalar("5"),
            Factory.createScalar("6")
        });
        Vector expectedAnswer20 = Factory.createVector(new Scalar[]{
            Factory.createScalar("5"), // 2+3
            Factory.createScalar("9"), // 5+4
            Factory.createScalar("7"), // 2+5
            Factory.createScalar("8")  // 2+6
        });
        System.out.println("20. 벡터 덧셈");
        System.out.println("vA: " + vA);
        System.out.println("vD: " + vD);
        System.out.println("기댓값: " + expectedAnswer20);
        vA.add(vD);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer20) ? "통과" : "실패");
        System.out.println("");

        // 21. 벡터 스칼라 곱셈
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        Scalar mulS = Factory.createScalar("3");
        Vector expectedAnswer21 = Factory.createVector(new Scalar[]{
            Factory.createScalar("6"),  // 2*3
            Factory.createScalar("15"), // 5*3
            Factory.createScalar("6"),  // 2*3
            Factory.createScalar("6")   // 2*3
        });
        System.out.println("21. 벡터 스칼라 곱셈");
        System.out.println(vA + " * " + mulS + " = ");
        System.out.println("기댓값: " + expectedAnswer21);
        vA.multiply(mulS);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer21) ? "통과" : "실패");
        System.out.println("");

        // 22. 행렬 덧셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        Matrix mF = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        Matrix expectedAnswer22 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("9"),  Factory.createScalar("10"), Factory.createScalar("11")}, // 7+2, 7+3, 7+4
            {Factory.createScalar("12"), Factory.createScalar("13"), Factory.createScalar("14")}  // 7+5, 7+6, 7+7
        });
        System.out.println("22. 행렬 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값: " + expectedAnswer22);
        Matrix result22 = Tensors.add(mA, mF);
        System.out.println("결과:\n" + result22);
        System.out.println(result22.equals(expectedAnswer22) ? "통과" : "실패");
        System.out.println("");

        // 23. 행렬 곱셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        Matrix mE = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("2")},
            {Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6")}
        });
        Matrix expectedAnswer23 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("42"), Factory.createScalar("56")}, // (7*1+7*3+7*5, 7*2+7*4+7*6)
            {Factory.createScalar("42"), Factory.createScalar("56")}
        });
        System.out.println("23. 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값: " + expectedAnswer23);
        mA.multiply(mE);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer23) ? "통과" : "실패");
        System.out.println("");

        // 24. static 스칼라 덧셈
        sA = Factory.createScalar("3.14");
        sB = Factory.createScalar("5.0");
        Scalar expectedAnswer24 = Factory.createScalar("8.14");
        Scalar result24 = Tensors.add(sA, sB);
        System.out.println("24. static 스칼라 덧셈");
        System.out.println(sA + " + " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer24);
        System.out.println("결과: " + result24);
        System.out.println(result24.equals(expectedAnswer24) ? "통과" : "실패");
        System.out.println("");

        // 25. static 스칼라 곱셈
        sA = Factory.createScalar("3.14");
        sB = Factory.createScalar("5.0");
        Scalar expectedAnswer25 = Factory.createScalar("15.7");
        Scalar result25 = Tensors.multiply(sA, sB);
        System.out.println("25. static 스칼라 곱셈");
        System.out.println(sA + " * " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer25);
        System.out.println("결과: " + result25);
        System.out.println(result25.equals(expectedAnswer25) ? "통과" : "실패");
        System.out.println("");

        // 26. static 벡터 덧셈
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        vD = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("4"),
            Factory.createScalar("5"),
            Factory.createScalar("6")
        });
        Vector expectedAnswer26 = Factory.createVector(new Scalar[]{
            Factory.createScalar("5"),  // 2+3
            Factory.createScalar("9"), // 5+4
            Factory.createScalar("7"), // 2+5
            Factory.createScalar("8")  // 2+6
        });
        Vector result26 = Tensors.add(vA, vD);
        System.out.println("26. static 벡터 덧셈");
        System.out.println(vA + " + " + vD + " = ");
        System.out.println("기댓값: " + expectedAnswer26);
        System.out.println("결과: " + result26);
        System.out.println(result26.equals(expectedAnswer26) ? "통과" : "실패");
        System.out.println("");

        // 27. static 벡터 곱셈
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        sA = Factory.createScalar("3.14");
        Vector expectedAnswer27 = Factory.createVector(new Scalar[]{
            Factory.createScalar("6.28"),  // 2*3.14
            Factory.createScalar("15.7"), // 5*3.14
            Factory.createScalar("6.28"), // 2*3.14
            Factory.createScalar("6.28")  // 2*3.14
        });
        Vector result27 = Tensors.multiply(vA, sA);
        System.out.println("27. static 벡터 곱셈");
        System.out.println(vA + " * " + sA + " = ");
        System.out.println("기댓값: " + expectedAnswer27);
        System.out.println("결과: " + result27);
        System.out.println(result27.equals(expectedAnswer27) ? "통과" : "실패");
        System.out.println("");

        // 28. static 행렬 덧셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        mF = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        Matrix expectedAnswer28 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("9"), Factory.createScalar("10"), Factory.createScalar("11")},
            {Factory.createScalar("12"), Factory.createScalar("13"), Factory.createScalar("14")}
        });
        Matrix result28 = Tensors.add(mA, mF);
        System.out.println("28. static 행렬 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값: " + expectedAnswer28);
        System.out.println("결과:\n" + result28);
        System.out.println(result28.equals(expectedAnswer28) ? "통과" : "실패");
        System.out.println("");

        // 29. static 행렬 곱셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        mE = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("2")},
            {Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6")}
        });
        Matrix expectedAnswer29 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("63"), Factory.createScalar("84")},
            {Factory.createScalar("63"), Factory.createScalar("84")}
        });
        Matrix result29 = Tensors.multiply(mA, mE);
        System.out.println("29. static 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값: " + expectedAnswer29);
        System.out.println("결과:\n" + result29);
        System.out.println(result29.equals(expectedAnswer29) ? "통과" : "실패");
        System.out.println("");

        // 30. 벡터 toVerticalMatrix
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        Matrix expectedAnswer30 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2")},
            {Factory.createScalar("5")},
            {Factory.createScalar("2")},
            {Factory.createScalar("2")}
        });
        System.out.println("30. toVerticalMatrix 벡터 세로로");
        System.out.println("기댓값: " + expectedAnswer30);
        System.out.println(vA.toVerticalMatrix());
        System.out.println("");

        // 31. 벡터 toHorizentalMatrix
        vA = Factory.createVector(4, Factory.createScalar("2"));
        vA.setValue(1, Factory.createScalar("5"));
        Matrix expectedAnswer31 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("5"), Factory.createScalar("2"), Factory.createScalar("2")}
        });
        System.out.println("31. 벡터 toHorizentalMatrix");
        System.out.println("기댓값: " + expectedAnswer31);
        System.out.println(vA.toHorizontalMatrix());
        System.out.println("");

        // 32. attachHMatrix
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        mB = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        Matrix expectedAnswer32 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        System.out.println("32. attachHMatrix (가로로 붙이기)");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachHMatrix(mA, mB));
        System.out.println("기댓값: " + expectedAnswer32);
        System.out.println("");

        // 33. attachVMatrix
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        mB = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        Matrix expectedAnswer33 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")},
            {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")},
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        System.out.println("33. attachVMatrix (세로로 붙이기)");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachVMatrix(mA, mB));
        System.out.println("기댓값: " + expectedAnswer33);
        System.out.println("");

        // 34. 행 벡터 추출
        mA = Factory.createMatrix(2, 2, Factory.createScalar("0"));
        mA.setValue(0, 0, Factory.createScalar("1"));
        mA.setValue(0, 1, Factory.createScalar("2"));
        mA.setValue(1, 0, Factory.createScalar("3"));
        mA.setValue(1, 1, Factory.createScalar("4"));
        int rowIdx = 0;
        int colIdx = 0;
        Vector expectedAnswer34 = Factory.createVector(new Scalar[]{
            Factory.createScalar("1"), // mA[0][0]
            Factory.createScalar("2")  // mA[0][1]
        });
        System.out.println("34. 행 벡터 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 행 인덱스: " + rowIdx);
        System.out.println("기댓값: " + expectedAnswer34);
        Vector result34 = mA.getRowVector(rowIdx);
        System.out.println("결과: " + result34);
        System.out.println(result34.equals(expectedAnswer34) ? "통과" : "실패");
        System.out.println("");

        // 35. 열 벡터 추출
        Vector expectedAnswer35 = Factory.createVector(new Scalar[]{
            Factory.createScalar("1"), // mA[0][0]
            Factory.createScalar("3")  // mA[1][0]
        });
        System.out.println("35. 열 벡터 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 열 인덱스: " + colIdx);
        System.out.println("기댓값: " + expectedAnswer35);
        Vector result35 = mA.getColVector(colIdx);
        System.out.println("결과: " + result35);
        System.out.println(result35.equals(expectedAnswer35) ? "통과" : "실패");
        System.out.println("");

        // 36. 부분 행렬 추출
        Matrix expectedAnswer36 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1")} // mA[0][0]
        });
        System.out.println("36. 부분 행렬 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 범위: (0,0)부터 (1,1)까지");
        System.out.println("기댓값: " + expectedAnswer36);
        Matrix result36 = mA.extractSubMatrix(0, 1, 0, 1);
        System.out.println("결과:\n" + result36);
        System.out.println(result36.equals(expectedAnswer36) ? "통과" : "실패");
        System.out.println("");

        // 37. minor 행렬 추출
        Matrix expectedAnswer37 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("4")} // mA[1][1] (0,0 제거)
        });
        System.out.println("37. minor 행렬 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("제거할 행과 열: (0,0)");
        System.out.println("기댓값: " + expectedAnswer37);
        Matrix result37 = mA.minorSubMatrix(0, 0);
        System.out.println("결과:\n" + result37);
        System.out.println(result37.equals(expectedAnswer37) ? "통과" : "실패");
        System.out.println("");

        // 38. 전치행렬
        Matrix expectedAnswer38 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("3")}, // mA[0][0], mA[1][0]
            {Factory.createScalar("2"), Factory.createScalar("4")}  // mA[0][1], mA[1][1]
        });
        System.out.println("38. 전치행렬");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer38);
        Matrix result38 = mA.transposeMatrix();
        System.out.println("전치 결과:\n" + result38);
        System.out.println(result38.equals(expectedAnswer38) ? "통과" : "실패");
        System.out.println("");

        // 39. 대각합
        Scalar expectedAnswer39 = Factory.createScalar("5"); // 1+4
        Scalar result39 = mA.trace();
        System.out.println("39. 대각합");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer39);
        System.out.println("대각합: " + result39);
        System.out.println(result39.equals(expectedAnswer39) ? "통과" : "실패");
        System.out.println("");

        // 40. 정사각 행렬 판별
        boolean expectedAnswer40 = true;
        System.out.println("40. 정사각 행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer40);
        boolean result40 = mA.isSquare();
        System.out.println("정사각 행렬 여부: " + result40);
        System.out.println(result40 == expectedAnswer40 ? "통과" : "실패");
        System.out.println("");

        // 41. 상삼각행렬 판별
        boolean expectedAnswer41 = false;
        System.out.println("41. 상삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer41);
        boolean result41 = mA.isUpperTriangular();
        System.out.println("상삼각행렬 여부: " + result41);
        System.out.println(result41 == expectedAnswer41 ? "통과" : "실패");
        System.out.println("");

        // 42. 하삼각행렬 판별
        boolean expectedAnswer42 = false;
        System.out.println("42. 하삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer42);
        boolean result42 = mA.isLowerTriangular();
        System.out.println("하삼각행렬 여부: " + result42);
        System.out.println(result42 == expectedAnswer42 ? "통과" : "실패");
        System.out.println("");

        // 43. 단위행렬 판별
        boolean expectedAnswer43 = false;
        System.out.println("43. 단위행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer43);
        boolean result43 = mA.isIdentity();
        System.out.println("단위행렬 여부: " + result43);
        System.out.println(result43 == expectedAnswer43 ? "통과" : "실패");
        System.out.println("");

        // 44. 영행렬 판별
        boolean expectedAnswer44 = false;
        System.out.println("44. 영행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer44);
        boolean result44 = mA.isZero();
        System.out.println("영행렬 여부: " + result44);
        System.out.println(result44 == expectedAnswer44 ? "통과" : "실패");
        System.out.println("");

        // 45. 행 교환
        Matrix expectedAnswer45 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("3"), Factory.createScalar("4")}, // mA[1]
            {Factory.createScalar("1"), Factory.createScalar("2")}  // mA[0]
        });
        System.out.println("45. 행 교환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 행: 0행과 1행");
        System.out.println("기댓값: " + expectedAnswer45);
        mA.rowSwap(0, 1);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer45) ? "통과" : "실패");
        System.out.println("");

        // 46. 열 교환
        Matrix expectedAnswer46 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("4"), Factory.createScalar("3")}, // mA[0][1], mA[0][0]
            {Factory.createScalar("2"), Factory.createScalar("1")}  // mA[1][1], mA[1][0]
        });
        System.out.println("46. 열 교환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 열: 0열과 1열");
        System.out.println("기댓값: " + expectedAnswer46);
        mA.colSwap(0, 1);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer46) ? "통과" : "실패");
        System.out.println("");

        // 47. 행 스칼라 곱
        Matrix expectedAnswer47 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("8"), Factory.createScalar("6")}, // 4*2, 3*2
            {Factory.createScalar("2"), Factory.createScalar("1")}  // 그대로
        });
        System.out.println("47. 행 스칼라 곱");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 행: 0행, 스칼라: 2");
        System.out.println("계산 과정:");
        System.out.println("0행의 각 원소에 2를 곱합니다.");
        System.out.println("(4,3) -> (8,6)");
        System.out.println("기댓값: " + expectedAnswer47);
        mA.rowMultiply(0, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer47) ? "통과" : "실패");
        System.out.println("");

        // 48. 열 스칼라 곱
        Matrix expectedAnswer48 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("16"), Factory.createScalar("6")}, // 8*2, 6
            {Factory.createScalar("4"), Factory.createScalar("1")}   // 2*2, 1
        });
        System.out.println("48. 열 스칼라 곱");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 열: 0열, 스칼라: 2");
        System.out.println("계산 과정:");
        System.out.println("0열의 각 원소에 2를 곱합니다.");
        System.out.println("(8,2) -> (16,4)");
        System.out.println("기댓값: " + expectedAnswer48);
        mA.colMultiply(0, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer48) ? "통과" : "실패");
        System.out.println("");

        // 49. 행에 다른 행의 상수배 더하기
        Matrix expectedAnswer49 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("24"), Factory.createScalar("8")}, // 16+2*4, 6+2*1
            {Factory.createScalar("4"), Factory.createScalar("1")}   // 그대로
        });
        System.out.println("49. 행에 다른 행의 상수배 더하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("대상 행: 0행, 더할 행: 1행, 스칼라: 2");
        System.out.println("계산 과정:");
        System.out.println("1행의 각 원소에 2를 곱한 후 0행에 더합니다.");
        System.out.println("(16,6) + 2*(4,1) = (24,8)");
        System.out.println("기댓값: " + expectedAnswer49);
        mA.rowAddOtherRow(0, 1, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer49) ? "통과" : "실패");
        System.out.println("");

        // 50. 열에 다른 열의 상수배 더하기
        Matrix expectedAnswer50 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("40"), Factory.createScalar("8")}, // 24+2*8, 8
            {Factory.createScalar("6"), Factory.createScalar("1")}   // 4+2*1, 1
        });
        System.out.println("50. 열에 다른 열의 상수배 더하기");
        System.out.println("원본 행렬:\n" + mA);    
        System.out.println("대상 열: 0열, 더할 열: 1열, 스칼라: 2");
        System.out.println("계산 과정:");
        System.out.println("1열의 각 원소에 2를 곱한 후 0열에 더합니다.");
        System.out.println("(24,4) + 2*(8,1) = (40,6)");
        System.out.println("기댓값: " + expectedAnswer50);
        mA.colAddOtherCol(0, 1, Factory.createScalar("2"));
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer50) ? "통과" : "실패");
        System.out.println("");

        // 51. RREF 변환
        Matrix expectedAnswer51 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("0")}, // 단위행렬
            {Factory.createScalar("0"), Factory.createScalar("1")}
        });
        System.out.println("51. RREF 변환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer51);
        Matrix result51 = mA.getRREF();
        System.out.println("RREF 변환 결과:\n" + result51);
        System.out.println(result51.equals(expectedAnswer51) ? "통과" : "실패");
        System.out.println("");

        // 52. RREF 판별
//        Matrix expectedAnswer52 = null;
//        System.out.println("52. RREF 판별");
//        System.out.println("원본 행렬:\n" + mA);
//        System.out.println("기댓값: " + expectedAnswer52);
//        Matrix result52 = mA.isRREF();
//        System.out.println("RREF 여부: " + (result52 == null ? "RREF가 아님" : "RREF임"));
//        System.out.println(result52 == expectedAnswer52 ? "통과" : "실패");
//        System.out.println("");

        // 53. 본인의 행렬식 구하기
        Scalar expectedAnswer53 = Factory.createScalar("-8"); // 예시 행렬식 값
        Scalar result53 = mA.getDeterminant();
        System.out.println("53. 행렬식 구하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer53);
        System.out.println("행렬식: " + result53);
        System.out.println(result53.equals(expectedAnswer53) ? "통과" : "실패");
        System.out.println("");

        // 54. 역행렬 구하기
        Matrix expectedAnswer54 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("-0.125"), Factory.createScalar("1.0")}, // 실제 역행렬 값
            {Factory.createScalar("0.75"), Factory.createScalar("-5.0")}
        });
        System.out.println("54. 역행렬 구하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("기댓값: " + expectedAnswer54);
        Matrix result54 = mA.getInverseMatrix();
        System.out.println("역행렬:\n" + result54);
        System.out.println(result54.equals(expectedAnswer54) ? "통과" : "실패");
        System.out.println("");
        System.out.println("끝이다 ㅎㅎ");

         */
    }
} 