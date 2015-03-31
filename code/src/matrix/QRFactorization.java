package matrix;

/**
 * Created by Philip on 3/30/15.
 */
public class QRFactorization {

    public static TwoMatrixResult getQRGivens(Matrix input) {
        System.out.println("Input:");
        input.printMatrix();

        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.QR_GIVENS);


        int n = input.getN();
        Matrix answer = new Matrix(input);
        Matrix identity = Matrix.identity(input.getN());
        Matrix givens = new Matrix(identity);
        Matrix q = new Matrix(identity);

        int iterations = 0;
        double a;
        double b;
        double denom;
        double cosX;
        double sinX;

        for (int i = 0; i < n; i++) {
            for (int j = (n - 1); j > i; j--) {

                a = answer.getData(j - 1, i);
                b = answer.getData(j, i);
                denom = Math.sqrt(a * a + b * b);
                cosX = a / denom;
                sinX = -b / denom;


                givens.setData(j, j, cosX);
                givens.setData(j, j - 1, sinX);
                givens.setData(j - 1, j, -sinX);
                givens.setData(j - 1, j - 1, cosX);

                answer = givens.multiply(answer);
                q = givens.multiply(q);

                givens = new Matrix(identity);
                iterations++;
            }
        }
        System.out.println("Q:");
        q.printMatrix();
        System.out.println("R:");
        answer.printMatrix();

        q = q.transpose();

        Matrix finalMatrix = q.multiply(answer);
        finalMatrix.printMatrix();

        return result;
    }

    public static TwoMatrixResult getQRHouseHolder(Matrix input) {
        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.QR_HOUSE);


        return result;
    }
}
