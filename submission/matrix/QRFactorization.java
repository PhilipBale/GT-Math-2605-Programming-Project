package matrix;

/**
 * Created by Philip on 3/30/15.
 */
public class QRFactorization {

    public static TwoMatrixResult getQRGivens(Matrix input) {
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
        result.setError(q.error(asubn, input));


        return result;
    }

    public static TwoMatrixResult getQRHouseHolder(Matrix input) {
        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.QR_HOUSE);


        input = new Matrix(input);
        Matrix begin = new Matrix(input);
        int n = input.getN();
        Matrix asubn = new Matrix(input);
        Matrix identity = Matrix.identity(input.getN());

        Matrix q = new Matrix(identity);
        Matrix r = new Matrix(identity);
        Matrix[] hMatrices = new Matrix[n];


        for (int i = 0; i < n; i++) {
            double[] x = new double[n];
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    x[j] = 0;
                } else {
                    x[j] = input.getData(j, i);
                }
            }

            int y = i + 1;
            double[] xnorme = new double[n];
            xnorme[i] = CustomMath.normalize(x);

            if (i != n - 1) {
                double[] uArray = CustomMath.subtractArrays(x, xnorme);
                double uNorm = CustomMath.normalize(uArray);
                Matrix u = new Matrix(CustomMath.makeArray2d(uArray));
                Matrix uT = u.transpose();
                Matrix uuT = u.multiply(uT);


                double twoDivNormSqr = 2 / (uNorm * uNorm);
                Matrix rightSide = uuT.scale(twoDivNormSqr);
                Matrix hsubn = identity.subtract(rightSide);


                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (j == k && j <= (i - 1)) {
                            hsubn.setData(j, k, 1);
                        }
                    }
                }

                input = hsubn.multiply(input);
                r = new Matrix(input);
                hMatrices[i] = new Matrix(input);

                q = q.multiply(hsubn);

            }
        }

        result.setFirstMatrix(q);
        result.setSecondMatrix(r);
        result.setError(q.error(r, begin));

        return result;
    }
}
