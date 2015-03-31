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
        if (m != toMultiply.getN()) {
            throw new IllegalArgumentException("Matrix multiplication is impossible");
        }

        double[][] c = new double[m][toMultiply.getN()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < toMultiply.getN(); j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + data[i][k] * toMultiply.data[k][j];
                }
            }
        }


        return new Matrix(c);
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

            if (result.data[destRow][i] < 0.00000000001) {
                result.data[destRow][i] = 0;
            }
        }

        return result;
    }

    public void printMatrix() {
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
