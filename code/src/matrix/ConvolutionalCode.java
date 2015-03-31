package matrix;

import java.util.Random;

/**
 * Created by Philip on 3/31/15.
 */
public class ConvolutionalCode {

    int[] stream;

    public ConvolutionalCode(int length) {
        stream = new int[8];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            stream[i] = r.nextInt(2);
        }
        stream = extend(stream);
    }

    public ConvolutionalCode(int[] base) {
        stream = extend(base);
    }

    private int[] extend(int[] base) {
        int[] result = new int[8];

        for (int i = 0; i < 8; i++) {
            if (i >= base.length) {
                result[i] = 0;
            } else {
                result[i] = base[i];
            }
        }

        return result;
    }

    public int[][] getConvolutionalWord() {
        int[][] result = new int[stream.length][2];


        for (int i = 0; i < stream.length; i++) {
            int y0 = getY0(stream, i);
            int y1 = getY1(stream, i);

            result[i][0] = y0;
            result[i][1] = y1;
        }

        return result;
    }

    public String getOutputStream() {
        int[][] stream = getConvolutionalWord();
        String output = "(";

        for (int i = 0; i < stream.length; i++) {
            if (i > 0) {
                output += ",";
            }
            output += stream[i][0] + "" + stream[i][1];
        }

        return output + ")";

    }

    public int getY0(int[] stream, int index) {
        int pos1 = stream[index];
        int pos2 = index > 1 ? stream[index - 2] : 0;
        int pos3 = index > 2 ? stream[index - 3] : 0;

        return (pos1 + pos2 + pos3) % 2;
    }

    public int getY1(int[] stream, int index) {
        int pos1 = stream[index];
        int pos2 = index > 0 ? stream[index - 1] : 0;
        int pos3 = index > 2 ? stream[index - 3] : 0;

        return (pos1 + pos2 + pos3) % 2;
    }

}
