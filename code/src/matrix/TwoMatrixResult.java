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
        LU, QR;
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
            case QR:
                return first ? "Q" : "R";
            default:
                return "None";
        }
    }

}
