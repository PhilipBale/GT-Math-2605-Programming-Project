package matrix;

/**
 * Created by Philip on 3/30/15.
 */
public class QRFactorization {

    public static TwoMatrixResult getQRGivens(Matrix input) {
        input.printMatrix();

        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.QR_GIVENS);


        int n = input.getN();
        Matrix asubn = new Matrix(input);
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

                a = asubn.getData(j - 1, i);
                b = asubn.getData(j, i);
                denom = Math.sqrt(a * a + b * b);
                cosX = a / denom;
                sinX = -b / denom;


                givens.setData(j, j, cosX);
                givens.setData(j, j - 1, sinX);
                givens.setData(j - 1, j, -sinX);
                givens.setData(j - 1, j - 1, cosX);

                asubn = givens.multiply(asubn);
                q = givens.multiply(q);

                givens = new Matrix(identity);
                iterations++;
            }
        }

        q = q.transpose();

        result.setFirstMatrix(q); // q
        result.setSecondMatrix(asubn); // r

        Matrix finalMatrix = q.multiply(asubn);


        return result;
    }

    public static TwoMatrixResult getQRHouseHolder(Matrix input) {
        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.QR_HOUSE);


        return result;
    }
}
