package matrix;


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
        this(n,n);
    }

    public Matrix(double[][] data) {
        m = data.length;
        n = data[0].length;
        this.data = data;
    }

    public Matrix(Matrix toCopy) {
        this(toCopy.data);
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

    public static Matrix identity(int n) {
        Matrix I = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            I.data[i][i] = 1;
        }

        return I;
    }

    public Matrix plus(Matrix toAdd) {
        if ((m != toAdd.m)|| (n != toAdd.n)) {
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
        if ((m != toSubtract.m)|| (n != toSubtract.n)) {
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
        if ((m != toCompare.m)|| (n != toCompare.n)) {
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

    public void printMatrix() {
        String line = "{ ";

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    line += ", ";
                }
                line += data[i][j];
            }

            System.out.println(line + " }");
            line = ",{ ";
        }
    }

}
