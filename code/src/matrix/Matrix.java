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

    public Matrix(double[][] data) {
        m = data.length;
        n = data[0].length;
        this.data = data;
    }

    public Matrix(Matrix toCopy) {
        this(toCopy.data);
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
