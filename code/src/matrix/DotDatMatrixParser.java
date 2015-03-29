package matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Philip on 3/25/15.
 */
public class DotDatMatrixParser {

    public static Matrix parseMatrix(String path) {
        FileReader file = null;
        try {
            file = new FileReader(new File(path));

            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            int rowLength = 0;
            ArrayList<Double[]> rows = new ArrayList<Double[]>();
            if (line != null) {
                String[] entries = line.trim().split(" ");
                rowLength = entries.length;

                while (line != null && line.length() > 0) {
                    entries = line.trim().split(" ");
                    Double[] row = new Double[rowLength];
                    for (int i = 0; i < rowLength; i++) {
                        if (entries[i].contains("/")) {
                            int a = Integer.parseInt(entries[i].substring(0, entries[i].indexOf("/")));
                            int b = Integer.parseInt(entries[i].substring(entries[i].indexOf("/") + 1, entries[i].length()));
                            row[i] = (double) a / b;
                        } else {
                            row[i] = Double.parseDouble(entries[i]);
                        }
                    }

                    rows.add(row);

                    line = br.readLine();
                }
            }

            double[][] result = new double[rows.size()][rowLength];
            for (int i = 0; i < rows.size(); i++) {
                for (int j = 0; j < rowLength; j++) {
                    result[i][j] = rows.get(i)[j];
                }
            }

            return new Matrix(result);
            //m.printMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        parseMatrix("a.dat");
    }
}
