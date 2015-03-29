package matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by philip on 3/28/15.
 */
public class LUDecomposition {

    public static TwoMatrixResult getLUDecomposition(Matrix input) {
        TwoMatrixResult result = new TwoMatrixResult();
        result.setType(TwoMatrixResult.Type.LU);
        Matrix u = new Matrix(input);
        Matrix l = Matrix.identity(u.getN());


        for (int j = 0; j < u.getN(); j++) {
            for (int i = j + 1; i < u.getM(); i++) {
                double rowHead = u.getData(i, j);
                if (rowHead != 0) {
                    double transform = -rowHead / u.getData(j, j);
                    u = u.modifyRow(j, i, transform);
                    l.setData(i,j, -transform);
                } else {
                    l.setData(i,j,0);
                }
            }
        }

        result.setFirstMatrix(l);
        result.setSecondMatrix(u);

        return result;
    }
}
