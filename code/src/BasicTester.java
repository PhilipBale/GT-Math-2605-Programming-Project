import matrix.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Philip on 3/25/15.
 */
public class BasicTester {

    private static Matrix aMatrix;
    private static Matrix aTransMatrix;
    private static Matrix bMatrix;
    private static Matrix bTransMatrix;
    private static Matrix hMatrix; //Hilbert

    @BeforeClass
    public static void setUp() {
        aMatrix = DotDatMatrixParser.parseMatrix("a.dat");
        bMatrix = DotDatMatrixParser.parseMatrix("b.dat");
        aTransMatrix = DotDatMatrixParser.parseMatrix("a_transpose.dat");
        bTransMatrix = DotDatMatrixParser.parseMatrix("b_transpose.dat");
        hMatrix = DotDatMatrixParser.parseMatrix("h.dat");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongSizeMatrix() {
        aMatrix.isEqualTo(bMatrix);
    }

    @Test
    public void matrixEquals() {
        assert (aMatrix.isEqualTo(aTransMatrix));
    }

    @Test
    public void matrixTranspose() {
        Matrix trans = bMatrix.transpose();
        assert (trans.isEqualTo(bTransMatrix));
    }

    @Test
    public void hilbertMatrix() {
        Matrix hilb = MatrixGenerator.HilbertsMatrix(4);
        assert (hMatrix.isEqualTo(hilb));
    }

    @Test
    public void hilbertLU() {
        hMatrix.printMatrix();
        TwoMatrixResult result = LUDecomposition.getLUDecomposition(hMatrix);
        result.printResult();

        assert(true);
    }
}
