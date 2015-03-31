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
        TwoMatrixResult result = LUDecomposition.getLUDecomposition(hMatrix);
        result.printResult();

        assert(true);
    }

    @Test
    public void givensQR() {
        TwoMatrixResult result = QRFactorization.getQRGivens(hMatrix);
        result.printResult();
        assert(true);
    }

    @Test
    public void householderQR() {
        TwoMatrixResult result = QRFactorization.getQRHouseHolder(hMatrix);
        result.printResult();
        result.getFirstMatrix().multiply(result.getSecondMatrix()).printMatrix();
        assert(true);
    }

    @Test
    public void wuchensB() {
        double wuchenB = CustomMath.calculateWuchensB(4)[0];
        assert(wuchenB == 0.0464158883361278);
    }

    @Test
    public void solveQR() {
        double[] wuchenB = CustomMath.calculateWuchensB(hMatrix.getM());
        Matrix wuchenMatrix = new Matrix(CustomMath.makeArray2d(wuchenB));
        Matrix result = hMatrix.solve(wuchenMatrix);
        System.out.println();
        assert(true);
    }

    @Test
    public void testConvolutional() {
        int[] tester = new int[] {1, 0, 1, 1, 0};
        String result = new ConvolutionalCode(tester).getOutputStream();
        System.out.println("hey");
        assert(true);
    }
}
