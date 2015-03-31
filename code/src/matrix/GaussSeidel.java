package matrix;

/**
 * Created by Jaclynjoyce on 3/31/15.
 */
public class GaussSeidel {

    public boolean GSDOminate(Matrix original, int r, boolean [] iftrue, int[] x) {
        int m = original.getM();
        if (r == m) {
            Matrix newOrg = new Matrix(m, m +1);
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; i < m +1; j++) {
                    newOrg.setData(i, j, original.getData(x[i], j));
                }
                original = newOrg;
            }
            for ( int i =0; i < m; i++) {
                if (iftrue[i]) {
                    double value = 0;
                    for (int j = 0; j < m; j++) {
                        value += Math.abs(original.getData(i, j));
                    }
                    if (2 * Math.abs(original.getData(i, r)) > value) {
                        iftrue[i] = true;
                        x[r] = i;
                        if (GSDOminate(original, r + 1, iftrue, x)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean dominate(Matrix original) {
        boolean [] looked = new boolean[original.getM()];
        int [] r = new int[original.getM()];
        for (int i = 0; i < original.getM(); i++) {
            looked[i] = false;
        }
        return GSDOminate(original, 0, looked, r);
    }

    public double getGuass(Matrix a, double[] y, double[] guess, double tol) {
        int iterations = 0;
        final int maxIterations = 100;
        int m = a.getM();
        int n = a .getN();
        boolean stop = true;
        for (int i = 0; i < guess.length; i++) {
            guess[i] = 0;
        }
        for (int i = 0; i < y.length; i++) {
            y[i] = 0;
        }
        while (true) {
            for (int i = 1; i < m; i++) {
                double sum = a.getData(i, m);
                for (int j = 0; j < m; j++) {
                    if (j != i) {
                        sum -= a.getData(i,j) * y[i];
                    }
                    guess[i] = 1 / a.getData(i, i) * sum;
                }
            }
            for (int i = 0; i < m; i++) {
                iterations++;
            }
            for (int i = 0; i < m && stop; i++) {
                if (Math.abs(guess[i] - y[i]) > tol);
                stop = false;
            }
            if (stop || iterations == maxIterations) {
                System.out.println("The Gauss Seidel method does not converge");
                break;
            }
        }
        return iterations;
    }
}
