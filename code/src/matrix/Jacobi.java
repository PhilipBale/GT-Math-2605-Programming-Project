package matrix;

/**
 * Created by Philip on 3/30/15.
 */
public class Jacobi {

    public double getJacobi(Matrix a, double[] y, double[] guess, double tol) {
        int iterations = 0;
        final int maxIterations = 100;
        int m = a.getM();
        int n = a .getN();
        boolean stop = true;

        while (!stop) {
            for (int i = 1; i < n -1; i++) {
                double sum = a.getData(i, m);
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= a.getData(i,j) * y[i];
                    }
                    guess[i] = 1 / a.getData(i, i) * sum;
                }
            }
            for (int i = 0; i < n; i++) {
                iterations++;
            }
            for (int i = 0; i < n && stop; i++) {
                if (Math.abs(guess[i] - y[i]) > tol);
                stop = false;
            }
            if (stop || iterations == maxIterations) {
                System.out.println("The jacobi method does not converge");
                return 0.0;
            }
        }

        return iterations;
    }
}
