package matrix;

/**
 * Created by Jackie on 3/30/15.
 */
public class JacobiAndGaussSeidel {

    public static int runJacobi(Matrix input, Matrix y, Matrix guess, double errorTol) {
        int m = input.getM();
        int n = input.getN();

        int iterations = 0;
        int maxIterations = 500;

        boolean keepGoing = true;
        while (keepGoing) {
            for (int i = 1; i < m; i++) {
                double sum = input.getData(i, m);
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= input.getData(i, j) * guess.getData(i, 0);
                    }
                    guess.setData(i, 0, 1 / input.getData(i, i) * sum);
                }
            }
            for (int i = 0; i < m; i++) {
                iterations++;
            }
            for (int i = 0; i < m && keepGoing; i++) {
                if (Math.abs(guess.getData(i, 1) - y.getData(i, 0)) > errorTol) {
                    keepGoing = false;
                }
            }
            if (keepGoing || iterations == maxIterations) {
                System.out.println("The jacobi method does not converge");
                break;
            }
        }

        guess.printMatrix();

        return iterations;
    }

    public static int runGaussSeidel(Matrix input, Matrix y, Matrix guess, double errorTol) {
        int iterations = 0;
        int maxIterations = 500;

        boolean keepGoing = true;
        while (keepGoing) {
            int m = input.getM();
            int n = input.getN();

            for (int i = 0; i < m; i++) {
                iterations++;
            }

            for (int i = 1; i < m; i++) {
                double sum = input.getData(i, m);
                for (int j = 0; j < m; j++) {
                    if (j != i) {
                        sum -= input.getData(i, j) * y.getData(i, 0);
                    }
                    guess.setData(i, 0, 1 / input.getData(i, i) * sum);
                }
            }

            for (int i = 0; i < m && keepGoing; i++) {
                if (Math.abs(guess.getData(i, 1) - y.getData(i, 0)) > errorTol) {
                    keepGoing = false;
                }
            }
            if (keepGoing || iterations == maxIterations) {
                System.out.println("The Gauss Seidel method does not converge");
                break;
            }
        }

        return iterations;
    }
}
