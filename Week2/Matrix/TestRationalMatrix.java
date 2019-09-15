package Matrix;

public class TestRationalMatrix {
    public static void main(String[] args) {


        Double[][] m1 = new Double[3][3];
        Double[][] m2 = new Double[3][3];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                m1[i][j] = i + 1 + (0.1 * j + 5);
                m2[i][j] = i + 1 + (0.1 * j + 6);
            }
        }
        RationalMatrix rationalMatrix = new RationalMatrix();
        System.out.println("\nm1 + m2 is ");
        RationalMatrix.printResult(m1, m2, rationalMatrix.addMatrix(m1, m2),
                '+');

        System.out.println("\nm1 * m2 is ");
        RationalMatrix.printResult(m1, m2,
                rationalMatrix.multiplyMatrix(m1, m2), '*');
    }
}
