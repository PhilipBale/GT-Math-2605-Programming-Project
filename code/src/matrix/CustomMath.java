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

    public static double[][] makeArray2d(double[] input) {
        double[][] result = new double[input.length][1];

        for (int i = 0; i < input.length; i++) {
            result[i][0] = input[i];
        }

        return result;
    }
}
