package matrix;

/**
 * Created by Philip on 3/28/15.
 */

public class TwoMatrixResult {

    private Matrix first;
    private Matrix second;
    private double error;
    private Type resultType;

    public enum Type {
        LU, QR_HOUSE, QR_GIVENS;
    }

    public TwoMatrixResult() {
        this(null, null, 0, null);
    }

    public TwoMatrixResult(Matrix first, Matrix second, double error, Type type) {
        this.first = first;
        this.second = second;
        this.error = error;
        this.resultType = type;
    }

    public void printResult() {
        System.out.println(getPrefix(resultType, true) + ": ");
        first.printMatrix();
        System.out.println(getPrefix(resultType, false) + ": ");
        second.printMatrix();
        System.out.println("Error: " + error);
    }

    private String getPrefix(Type type, boolean first) {
        switch (type) {
            case LU:
                return first ? "L" : "U";
            case QR_HOUSE:
            case QR_GIVENS:
                return first ? "Q" : "R";
            default:
                return "None";
        }
    }

    public Type getType() {
        return resultType;
    }

    public void setType(Type type) {
        this.resultType = type;
    }

    public Matrix getFirstMatrix() {
        return first;
    }

    public void setFirstMatrix(Matrix input) {
        this.first = input;
    }

    public Matrix getSecondMatrix() {
        return second;
    }

    public void setSecondMatrix(Matrix input) {
        this.second = input;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

}
