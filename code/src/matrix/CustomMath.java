package matrix;

/**
 * Created by Philip on 3/30/15.
 */
public class CustomMath {

    public static double normalize(double[] input) {
        double result = 0;

        for (int i = 0; i < input.length; i++) {
            result += input[i] * input[i];
        }

        result = Math.sqrt(result);

        return result;
    }

    public static double[] normalizedVec(double[] input) {
        double[] result = new double[input.length];
        double norm = normalize(input);
        for (int i = 0; i <  input.length; i++) {
            result[i] = input[i] / norm;
        }

        return result;
    }

    public static double[] subtractArrays(double[] first, double[] second) {
        double[] result = new double[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] - second[i];
        }

        return result;
    }

    public static double[] addArrays(double[] first, double[] second) {
        double[] result = new double[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] + second[i];
        }

        return result;
    }

    public static double[] scaleArray(double[] input, double scalar) {
        double[] result = new double[input.length];

        for (int i = 0; i < input.length; i++) {
            result[i] = input[i] * scalar;
        }

        return result;
    }

    public static double[][] makeArray2d(double[] input) {
        double[][] result = new double[input.length][1];

        for (int i = 0; i < input.length; i++) {
            result[i][0] = input[i];
        }

        return result;
    }

    public static double[] calculateWuchensB(int n) {
        double baseB = Math.pow(0.1, (double) n / 3);
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = baseB;
        }

        return result;
    }

}
