import matrix.DotDatMatrixParser;
import matrix.Matrix;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Philip on 3/25/15.
 */
public class BasicTester {

    private static Matrix baseMatrix;

    @BeforeClass
    public static void setUp() {
        baseMatrix = DotDatMatrixParser.parseMatrix("a.dat");
        baseMatrix.printMatrix();
        System.out.println("Starting jUnits");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongSizeMatrix() {
        Matrix b = new Matrix(1,1);
        baseMatrix.isEqualTo(b);
    }

    @Test
    public void matrixEquals() {
        Matrix b = DotDatMatrixParser.parseMatrix("a.dat");
        assert(baseMatrix.isEqualTo(b));
    }

    @Test
    public void matrixTranspose() {
        Matrix b = DotDatMatrixParser.parseMatrix("a_transpose.dat");
        assert(baseMatrix.transpose().isEqualTo(b));
    }
}
