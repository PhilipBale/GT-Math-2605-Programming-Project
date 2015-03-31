package matrix;

/**
 * Created by Philip on 3/31/15.
 */
public class PowerMethod {

    public static Matrix runPowerMethod(Matrix input, double errorTol, double[] approximation) {
        input = new Matrix(input);
        input.printMatrix();

        Matrix result = new Matrix(input.getN(), 1);
        Matrix approx = new Matrix(approximation, true);

        boolean converges = true;
        double lastEigenVal = 0;
        double curEigenVal = 0;
        double err = 0;

        int iterations = 0;

        for (int i = 0; converges && (err >= errorTol || i == 0); i++) {
            lastEigenVal = approx.getData(0, 0);
            approx = input.multiplyVect(approx);

            if (err >= errorTol || i == 0) {
                approx = approx.scale(1 / lastEigenVal);
            }

            curEigenVal = approx.getData(0, 0);
            err = Math.abs(lastEigenVal - curEigenVal);

            converges = (curEigenVal / lastEigenVal != 1 || i == 0);

            iterations++;

            if (iterations > 1000) {
                throw new RuntimeException("Too many iterations");
            }
        }

        System.out.println("Approximated eigenvalue: " + curEigenVal);
        System.out.println("Iterations: " + iterations);



        return approx.transpose();
    }

}
