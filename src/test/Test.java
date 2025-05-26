//package test;
//
//import tensor.*;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class Test {
//    public static void main(String[] args) throws IOException {
//        // 스칼라의 생성
//        System.out.println("01. 값(String)을 지정하여 스칼라를 생성할 수 있다.");
//        Scalar s1 = Factory.createScalar("23.2");
//        System.out.println("값은 23.2로 지정시");
//        System.out.println("출력: " + s1 + "\n");
//
//        System.out.println("02. i 이상 j 미만의 무작위 값을 요소로 하는 스칼라를 생성할 수 있다.");
//        System.out.println("i = -32.2, j = -12로 지정");
//        Scalar s2 = Factory.createScalar( -32.2,-12);
//        System.out.println("출력: " + s2 + "\n");
//
//
//        // 벡터의 생성
//        System.out.println("03. 지정한 하나의 값을 모든 요소의 값으로 하는 n-차원 벡터를 생성할 수 있다.");
//        System.out.println("차원: 5, 값: 23.2로 지정");
//        Vector v1 = Factory.createVector(5, s1);
//        System.out.println("출력: " + v1 + "\n");
//
//        System.out.println("04. i 이상 j 미만의 무작위 값을 요소로 하는 n-차원 벡터를 생성할 수 있다");
//        System.out.println("i: 1.1, j: 6, n: 5로 지정");
//        Vector v2 = Factory.createVector(1.1, 6, 5);
//        System.out.println("출력: " + v2 + "\n");
//
//        System.out.println("05. 1차원 배열로부터 n-차원 벡터를 생성할 수 있다.");
//        System.out.println("2개의 원소를 가진 1차원 배열 사용");
//        Scalar[] arr1 = {s1, s2};
//        Vector v3 = Factory.createVector(arr1);
//        System.out.println("출력: "+ v3 + "\n");
//
//
//        // 행렬의 생성
//        System.out.println("06. 지정한 하나의 값을 모든 요소의 값으로 하는 m x n 행렬을 생성할 수 있다.");
//        System.out.println("m: 3, n: 5, 하나의 값: 23.2로 지정");
//        Matrix m1 = Factory.createMatrix(3,5, s1);
//        System.out.println("출력: \n" + m1);
//
//        System.out.println("07. i 이상 j 미만의 무작위 값을 요소로 하는 m x n 행렬을 생성할 수 있다.");
//        System.out.println("i: 1, j: 5, m: 3, n: 3로 지정");
//        Matrix m2 = Factory.createMatrix(1, 5, 3, 3);
//        System.out.println("출력: \n" + m2);
//
//        System.out.println("08. csv 파일로부터 m x n 행렬을 생성할 수 있다.");
//        String[][] arr = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}};
//        try (FileWriter fout = new FileWriter("c:\\temp\\csvTest.csv")) {
//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < arr[i].length; j++) {
//                    fout.write(String.valueOf(arr[i][j]));
//                    if(!(j == arr[i].length -1)) fout.write(", ");
//                }
//                fout.write("\r\n");
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//        Matrix m6 = Factory.createMatrix("c:\\temp\\csvTest.csv");
//        System.out.println(m6);
//
//
//        System.out.println("09. 2차원 배열로부터 m x n 행렬을 생성할 수 있다.");
//        System.out.println("2 x 1 2차원 배열 사용");
//        Scalar[][] arr2 = {{s1}, {s2}};
//        Matrix m3 = Factory.createMatrix(arr2);
//        System.out.println("출력: \n" + m3);
//
//        System.out.println("10. 단위 행렬을 생성할 수 있다.");
//        System.out.println("5 x 5 단위행렬 생성 시 ");
//        Matrix m4 = Factory.createIdentityMatrix(5);
//        System.out.println("출력: \n" + m4);
//
//
//        // 스칼라, 벡터, 행렬의 기본 기능
//        System.out.println("11v. 벡터의 특정 위치의 요소를 지정/조회할 수 있다.");
//        System.out.println(v1 + "의 3번째 위치의 요소를 99로 지정하고 조회 시");
//        Scalar s3 = Factory.createScalar("99");
//        v1.setValue(2, s3);
//        System.out.println("출력: " + v1);
//        System.out.println("3번째 위치의 요소는 " + v1.getValue(2) + "\n");
//
//        System.out.println("11m. 행렬의 특정 위치의 요소를 지정/조회할 수 있다.");
//        System.out.println(m1 + "의 3행 2열의 위치의 요소를 99로 지정하고 조회 시");
//        m1.setValue(2, 1, s3);
//        System.out.println("출력: \n" + m1);
//        System.out.println("3행 2열의 요소는 " + m1.getValue(2, 1) + "\n");
//
//        System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
//        System.out.println(s2 + "의 값을 24로 지정하고 조회 시");
//        s2.setValue("24");
//        System.out.println("지정한 후 출력: " + s2 + "\n");
//        System.out.println("조회 시 출력: " + s2.getValue() + "\n");
//
//        System.out.println("13v. 벡터 크기의 정보를 조회할 수 있다.");
//        System.out.println("벡터 " + v1 + "의 크기는");
//        System.out.println("출력: " + v1.size() + "\n");
//
//        System.out.println("13m. 행렬 크기의 정보를 조회할 수 있다.");
//        System.out.println(m4);
//        System.out.println("의 행개수와 열개수는");
//        System.out.println("행개수: " + m4.rowSize());
//        System.out.println("열개수: " + m4.colSize() + "\n");
//
//        System.out.println("14s. 스칼라 값 출력");
//        System.out.println(s1 + "\n");
//
//        System.out.println("14v. 1차원 배열 모양으로 벡터 값 출력");
//        System.out.println(v1 + "\n");
//
//        System.out.println("14m. 2차원 배열 모양으로 행렬 값 출력");
//        System.out.println(m1);
//
//        System.out.println("15s. 스칼라 객체의 동등성 판단");
//        System.out.println(s1 + "과 " + s2 + "의 동등성 판단");
//        System.out.println("출력: " + s1.equals(s2) + "\n");
//
//        System.out.println("15v. 벡터 객체의 동등성 판단");
//        System.out.println(v1 + "과 " + v3 + "의 동등성 판단");
//        System.out.println("출력: " + v1.equals(v3) + "\n");
//
//        System.out.println("15m. 행렬 객체의 동등성 판단");
//        System.out.println(m4 + "의 자기자신 동등성 판단");
//        System.out.println("출력: " + m4.equals(m4) + "\n");
//
//        System.out.println("16. 스칼라의 경우 값의 대소 비교를 할 수 있다. (작으면 -1, 같으면 0, 크면 1 반환)");
//        System.out.println(s1 + "과 " + s2 + "의 대소 비교");
//        System.out.println("출력: " + s1.compareTo(s2) + "\n");
//
//        System.out.println("17s. 객체 복제를 할 수 있다.");
//        System.out.println(s3 + "를 복제 시");
//        Scalar s4 = s3.clone();
//        System.out.println("출력: " + s4 + "\n");
//
//        System.out.println("17v. 객체 복제를 할 수 있다.");
//        System.out.println(v3 + "를 복제 시");
//        Vector v4 = v3.clone();
//        System.out.println("출력: " + v4 + "\n");
//
//        System.out.println("17m. 객체 복제를 할 수 있다.");
//        System.out.println(m4 + "를 복제 시");
//        Matrix m7 = m4.clone();
//        System.out.println("출력: \n" + m7 + "\n");
//
//
//        // 스칼라의 연산
//        System.out.println("18. 스칼라는 다른 스칼라와 덧셈이 가능하다.");
//        System.out.println(s1 + "과 " + s2 + "의 합: " + s1.add(s2) + "\n");
//
//        System.out.println("19. 스칼라는 다른 스칼라와 곱셈이 가능하다.");
//        System.out.println(s1 + "과 " + s2 + "의 곱: " + s1.multiply(s2) + "\n");
//
//
//        // 벡터의 연산
//        System.out.println("20. 벡터는 다른 벡터와 덧셈이 가능하다. (길이가 같을 때)");
//        Vector v5 = Factory.createVector(3, s2);
//        Vector v6 = Factory.createVector(3, s4);
//        System.out.println(v5 + "와 " + v6 + "의 합: " + v5.add(v6) + "\n");
//
//        System.out.println("21. 벡터는 다른 스칼라와 곱셈이 가능하다.");
//        System.out.println(v5 + "와 " + s4 + "의 곱: " + v5.multiply(s4) + "\n");
//
//
//        // 행렬의 연산
//        System.out.println("22. 행렬은 다른 행렬과 덧셈이 가능하다.");
//        Matrix m5 = Factory.createMatrix(5, 5, s4);
//        System.out.println(m5);
//        System.out.println(m4);
//        m5.add(m4);
//        System.out.println("두 행렬의 합은: ");
//        System.out.println(m5);
//
//        System.out.println("23. 행렬은 다른 행렬과 곱셈이 가능하다.");
//        System.out.println(m1);
//        System.out.println(m5);
//        m1.multiply(m5);
//        System.out.println("두 행렬의 곱은: ");
//        System.out.println(m1);
//
//
//        // 스칼라의 연산
//        System.out.println("24. 전달받은 두 스칼라의 덧셈이 가능하다.");
//        System.out.println(s1 + " + " + s3 + " = " + Tensors.add(s1, s3));
//
//        System.out.println("25. 전달받은 두 스칼라의 곱셈이 가능하다.");
//        System.out.println(s1 + " x " + s3 + " = " + Tensors.multiply(s1, s3));
//
//
//        // 벡터의 연산
//        System.out.println("26. 전달받은 두 벡터의 연산이 가능하다.");
//        System.out.println(v5 + " + " + v6 + " = " + Tensors.add(v5, v6));
//
//        System.out.println("27. 전달받은 스칼라와 벡터의 곱셈이 가능하다.");
//        System.out.println(v6 + " x " + s3 + " = " + Tensors.multiply(v6, s3));
//
//
//        //행렬의 연산
//        System.out.println("28. 전달받은 두 행렬의 덧셈이 가능하다.");
//        System.out.println(m4);
//        System.out.println(m5);
//        System.out.println("전달받은 두 행렬의 합은: ");
//        System.out.println(Tensors.add(m4, m5));
//
//        System.out.println("29. 전달받은 두 행렬의 곱셈이 가능하다.");
//        System.out.println(m4);
//        System.out.println(m5);
//        System.out.println("전달받은 두 행렬의 곱은");
//        System.out.println(Tensors.multiply(m4, m5));
//
//
//        // 벡터의 고급 기능
//        System.out.println("30. n-차원 벡터 객체는 자신으로부터 n x 1 행렬을 생성하여 반환할 수 있다.");
//        System.out.println(v5 + " 이 벡터를 이용: ");
//        System.out.println(v5.toVerticalMatrix());
//
//        System.out.println("31. n-차원 벡터 객체는 자신으로부터 1 x n 행렬을 생성하여 반환할 수 있다.");
//        System.out.println(v5 + " 이 벡터를 이용: ");
//        System.out.println(v5.toHorizontalMatrix());
//
//
//        // 행렬의 고급 기능
//        System.out.println("32. 행렬은 다른 행렬과 가로로 합쳐질 수 있다. (행 수가 같을 때)");
//        System.out.println(m3);
//        System.out.println(m6);
//        System.out.println("두 개의 행렬을 가로로 합치면: ");
//        System.out.println(Tensors.attachHMatrix(m3,m6));
//
//        System.out.println("33. 행렬은 다른 행렬과 세로로 합쳐질 수 있다. (열 수가 같을 때)");
//        System.out.println(m4);
//        System.out.println(m5);
//        System.out.println("두 개의 행렬을 세로로 합치면: ");
//        System.out.println(Tensors.attachVMatrix(m4,m5));
//
//        System.out.println("34. 행렬은 특정 행을 벡터 형태로 추출해 줄 수 있다.");
//        System.out.println(m5);
//        System.out.println("이 행렬의 3번째 행벡터는: ");
//        System.out.println(m5.getRowVector(2));
//
//        System.out.println("35. 행렬은 특정 열을 벡터 형태로 추출해 줄 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 3번째 열벡터는: ");
//        System.out.println(m2.getColVector(2));
//
//        System.out.println("36. 행렬은 특정 범위의 부분 행렬을 추출해 줄 수 있다.");
//        System.out.println(m7);
//        System.out.println("이 행렬의 2행 ~ 4행, 1열 ~ 3열 추출하면");
//        System.out.println(m7.extractSubMatrix(1, 3, 0, 2));
//
//        System.out.println("37. 특정 하나의 행과 하나의 열을 제외한 부분 행렬");
//        System.out.println(m6);
//        System.out.println("이 행렬에서 1행 2열을 제외한 부분 행렬은:");
//        System.out.println(m6.minorSubMatrix(0, 1));
//
//        System.out.println("38. 행렬은 전치행렬을 구해줄 수 있다.");
//        System.out.println(m6);
//        System.out.println("이 행렬의 전치행렬은: ");
//        System.out.println(m6.transposeMatrix());
//
//        System.out.println("39. 행렬은 대각 요소의 합을 구해줄 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 대각 요소의 합은: " + m2.trace() + "\n");
//
//
//        System.out.println("40. 행렬은 자신이 정사각 행렬인지 여부를 판별해 줄 수 있다.");
//        System.out.println(m4);
//        System.out.print("이 행렬의 정사각 행렬 여부: ");
//        System.out.println(m4.isSquare());
//
//        System.out.println("41. 행렬은 자신이 상삼각 행렬인지 여부를 판별해 줄 수 있다.");
//        Scalar zero = Factory.createScalar("0");
//        Scalar one = Factory.createScalar("1");
//        Scalar[][] arr3 = {{one, one, one}, {zero, one, one}, {zero, zero, one}};
//        Matrix m8 = Factory.createMatrix(arr3);
//        System.out.print(m8);
//        System.out.print("이 행렬의 상삼각 행렬 여부: ");
//        System.out.println(m8.isUpperTriangular());
//
//        System.out.println("42. 행렬은 자신이 하삼각 행렬인지 여부를 판별해 줄 수 있다.");
//        Scalar[][] arr4 = {{one, zero, zero}, {one, one, zero}, {one, one, one}};
//        Matrix m9 = Factory.createMatrix(arr4);
//        System.out.print(m9);
//        System.out.print("이 행렬의 하삼각 행렬 여부: ");
//        System.out.println(m9.isLowerTriangular());
//
//        System.out.println("43. 행렬은 자신이 단위 행렬인지 여부를 판별해 줄 수 있다.");
//        System.out.println(m4);
//        System.out.println("이 행렬의 단위 행렬 여부: " + m4.isIdentity());
//
//        System.out.println("44. 행렬은 자신이 영 행렬인지 여부를 판별해 줄 수 있다.");
//        Matrix m10 = Factory.createMatrix(4, 4, zero);
//        System.out.println(m10);
//        System.out.println("이 행렬의 영 행렬 여부: " + m10.isZero());
//
//        System.out.println("45. 행렬은 특정 두 행의 위치를 맞교환할 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 1행과 2행을 맞교환하면: ");
//        m2.rowSwap(0, 1);
//        System.out.println(m2);
//
//        System.out.println("46. 행렬은 특정 두 열의 위치를 맞교환할 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 1열과 2열을 맞교환하면: ");
//        m2.colSwap(0, 1);
//        System.out.println(m2);
//
//        System.out.println("47. 행렬은 특정 행에 상수배(스칼라)를 할 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 2행에 스칼라 0을 곱하면: ");
//        m2.rowMultiply(1, zero);
//        System.out.println(m2);
//
//        System.out.println("48. 행렬은 특정 열에 상수배(스칼라)를 할 수 있다.");
//        System.out.println(m2);
//        System.out.println("이 행렬의 1열에 스칼라 0을 곱하면: ");
//        m2.colMultiply(0, zero);
//        System.out.println(m2);
//    }
//}
