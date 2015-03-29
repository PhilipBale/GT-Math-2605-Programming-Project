package matrix;

/**
 * Created by Philip on 3/28/15.
 */
public class MatrixGenerator {

    public static Matrix HilbertsMatrix(int n) {
        Matrix m = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m.setData(i,j, (double) 1 / (i + j + 1));
            }
        }

        return m;
    }
}
