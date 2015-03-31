package matrix;


import java.text.DecimalFormat;

/**
 * Created by Philip on 3/25/15.
 */
public class Matrix {
    private int m = 0; // rows
    private int n = 0; // cols
    private double[][] data = null;

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        data = new double[m][n];
    }

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(double[][] data) {
        m = data.length;
        n = data[0].length;
        this.data = data;
    }

    public Matrix(double[] data, boolean isMBased) {
        this(CustomMath.makeArray2d(data.clone()));

        if (!isMBased) {
            Matrix newM = this.transpose();
            this.data = newM.data;
            this.m = newM.n;
            this.n = newM.m;
        }

    }

    public Matrix(Matrix toCopy) {
        this.m = toCopy.getM();
        this.n = toCopy.getN();
        double[][] data = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = toCopy.data[i][j];
            }
        }

        this.data = data;

    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void setData(int m, int n, double val) {
        this.data[m][n] = val;
    }

    public double getData(int m, int n) {
        return data[m][n];
    }

    public double[][] getData() {
        return data;
    }

    public static Matrix identity(int n) {
        Matrix I = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            I.data[i][i] = 1;
        }

        return I;
    }

    public Matrix plus(Matrix toAdd) {
        if ((m != toAdd.m) || (n != toAdd.n)) {
            throw new IllegalArgumentException("Matrix sizes don't match");
        }

        Matrix result = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.data[i][j] = data[i][j] + toAdd.data[i][j];
            }
        }

        return result;
    }

    public Matrix subtract(Matrix toSubtract) {
        if ((m != toSubtract.m) || (n != toSubtract.n)) {
            throw new IllegalArgumentException("Matrix sizes don't match");
        }

        Matrix result = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.data[i][j] = data[i][j] - toSubtract.data[i][j];
            }
        }

        return result;
    }

    public Matrix multiply(Matrix toMultiply) {
        if (n != toMultiply.getM()) {
            throw new IllegalArgumentException("Matrix multiplication is impossible");
        }

        if (n == 9 || m == 9) {
            System.out.println("Hey");
        }

        double[][] c = new double[m][toMultiply.getN()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < toMultiply.getN(); j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] += + data[i][k] * toMultiply.data[k][j];
                }
            }
        }

        return new Matrix(c);
    }

    public Matrix multiplyVect(Matrix input) {
        return multiplyVect(input.transpose().data[0]);
    }

    public Matrix multiplyVect(double[] vec) {
        if (n != vec.length) {
            throw new IllegalArgumentException("Matrix multiplication is impossible");
        }
        double[] result = new double[vec.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i] += data[j][i] * vec[j];
            }
        }

        Matrix resultMatrix = new Matrix(result, true);

        return resultMatrix;
    }

    public Matrix scale(double scalar) {
        Matrix result = new Matrix(this);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.data[i][j] *= scalar;
            }
        }

        return result;
    }


    public double error(Matrix comparable, Matrix target) {
        Matrix multi = this.multiply(comparable);
        multi = multi.subtract(target);

        return norm1(multi);
    }

    public double norm1(Matrix a) {
        double largest = 0;
        double smallest = 0;
        double temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                smallest += Math.abs(a.data[i][j]);
                if (smallest > largest) {
                    temp = smallest;
                    smallest = largest;
                    largest = temp;
                }
                smallest = 0;
            }

        }
        return largest;
    }


    public Matrix transpose() {
        Matrix result = new Matrix(n, m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.data[j][i] = data[i][j];
            }
        }

        return result;
    }

    public boolean isEqualTo(Matrix toCompare) {
        if ((m != toCompare.m) || (n != toCompare.n)) {
            throw new IllegalArgumentException("Matrix sizes don't match");
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j] != toCompare.data[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Matrix modifyRow(int srcRow, int destRow, double modifier) {
        Matrix result = new Matrix(this);

        for (int i = 0; i < n; i++) {
            double mod = result.data[srcRow][i] * modifier;
            result.data[destRow][i] += mod;

            /*if (result.data[destRow][i] < 0.00000000001) {
                result.data[destRow][i] = 0;
            }*/
        }

        return result;
    }

    public void swapRow(int first, int second) {
        double[] temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public Matrix solve(Matrix solution) {
        Matrix result = new Matrix(n, 1);
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(solution);

        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i])) {
                    max = j;
                }
            }

            A.swapRow(i, max);
            b.swapRow(i, max);

            // pivot b
            for (int j = i + 1; j < n; j++) {
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];
            }

            // pivot a
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    A.data[j][k] -= A.data[i][k] * A.data[j][i] / A.data[i][i];
                }

                A.data[j][i] = 0.0;
            }
        }

        for (int j = n - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < n; k++) {
                t += A.data[j][k] * result.data[k][0];
            }

            result.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }

        return result;
    }

    public Matrix getAugment() {
        Matrix result = new Matrix(m, 1);
        for (int i = 0; i < m; i++) {
            result.data[i][0] = data[i][n - 1];
        }

        return result;
    }

    public Matrix removeAugment() {
        Matrix result = new Matrix(m, n - 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                result.setData(i, j, data[i][j]);
            }
        }

        return result;
    }

    public synchronized void printMatrix() {
        String line = "{{ ";
        DecimalFormat df = new DecimalFormat("#.#########");
        String formatted;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {

                    line += ", ";
                }
                formatted = df.format(data[i][j]);
                if (formatted.equals("-0")) {
                    formatted = "0";
                }

                line += formatted;
            }
            if (i > 0) {
                System.out.println(",");
            }

            System.out.print(line + " }");
            line = "{ ";
        }
        System.out.print("}\n\n");
    }

}
