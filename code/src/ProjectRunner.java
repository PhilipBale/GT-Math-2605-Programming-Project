import matrix.*;

import java.util.Scanner;

/**
 * Created by Philip on 3/31/15.
 */
public class ProjectRunner {

    private enum Target {

        LU_SIMPLE("lu_fact", "LU Decomposition for one matrix", 1.1),
        QR_HOUSE_SIMPLE("qr_fact_househ", "QR Factorization by householder for one matrix", 1.21),
        QR_GIVENS_SIMPLE("qr_fact_givens", "QR Factorization by givens rotation for one matrix", 1.22),
        LU_SOLVE("solve_lu_b", "Solves with b using LU for one matrix", 1.31),
        QR_SOLVE("solve_qr_b", "Solves with b using QR for one matrix", 1.32),
        SOLVE_HILBERT("solve_hilbert", "Solves n hilbert matrices using QR and LU", 1.4),
        CONVOLUTION("convolutional_codes", "Generates random binary input stream, A0 and A1 matrices, and encoded word", 2.1),
        JACOBI("jacobi", "Jacobi iteration for a matrix", 2.21),
        GAUSS_SEIDEL("gauss_seidel", "Gauss seidel for a matrix", 2.22),
        DECODE("decode", "Decoees a given input binary stream", 2.3),
        POWER_METHOD("power_method", "Power method to find largest eigenvalue", 3.1);

        public String name;
        public String desc;
        public double rubricNum;

        Target(String name, String desc, double rubricNum) {
            this.name = name;
            this.desc = desc;
            this.rubricNum = rubricNum;
        }
    }

    public static void main(String[] args) {
        new ProjectRunner().run();
    }

    Scanner sc = new Scanner(System.in);

    public void run() {
        log("Welcome to our MATH 2605");

        boolean running = true;

        while (running) {
            log("Please enter command number, command name, or -1 to exit");
            if (!handleInput()) {
                running = false;
            }
        }

        log("Thank you for using me!");


    }

    public boolean handleInput() {
        String input = sc.next();
        if (input == "-1") {
            return false;
        }
        Target target = getTarget(input);
        if (target == null) {
            log("No such target!");
            return true;
        }
        String path;
        Matrix inputMatrix;
        Matrix solution;
        Matrix resultMatrix;
        Matrix qTb;
        Matrix x;
        Matrix y;
        int n;
        TwoMatrixResult result;
        log("Running Target: " + target.name);
        log("Desc: " + target.desc);

        switch (target) {
            case LU_SIMPLE:
                path = promptForMatrixPath();
                inputMatrix = DotDatMatrixParser.parseMatrix(path);
                result = LUDecomposition.getLUDecomposition(inputMatrix);
                result.printResult();
                break;
            case QR_HOUSE_SIMPLE:
                path = promptForMatrixPath();
                inputMatrix = DotDatMatrixParser.parseMatrix(path);
                result = QRFactorization.getQRHouseHolder(inputMatrix);
                result.printResult();
                break;
            case QR_GIVENS_SIMPLE:
                path = promptForMatrixPath();
                inputMatrix = DotDatMatrixParser.parseMatrix(path);
                result = QRFactorization.getQRGivens(inputMatrix);
                result.printResult();
                break;
            case LU_SOLVE:
                path = promptForAugmentedMatrixPath();
                inputMatrix = DotDatMatrixParser.parseMatrix(path);
                solution = inputMatrix.getAugment();
                inputMatrix = inputMatrix.removeAugment();

                result = LUDecomposition.getLUDecomposition(inputMatrix);
                y = result.getFirstMatrix().solve(solution);
                resultMatrix = result.getSecondMatrix().solve(y);
                log("LU solution: ");
                resultMatrix.printMatrix();
                log("LU error: " + result.getError());

                break;
            case QR_SOLVE:
                path = promptForAugmentedMatrixPath();
                inputMatrix = DotDatMatrixParser.parseMatrix(path);
                solution = inputMatrix.getAugment();
                inputMatrix = inputMatrix.removeAugment();
                result = QRFactorization.getQRGivens(inputMatrix);
                qTb = result.getFirstMatrix().transpose().multiply(solution);
                x = result.getSecondMatrix().solve(qTb);
                log("Givens Solution:");
                x.printMatrix();
                log("Givens error: " + result.getError());

                result = QRFactorization.getQRHouseHolder(inputMatrix);
                qTb = result.getFirstMatrix().transpose().multiply(solution);
                x = result.getSecondMatrix().solve(qTb);
                log("Householder solution:");
                x.printMatrix();
                log("Householder error: " + result.getError());

                solution = inputMatrix.solve(solution);
                solution.printMatrix();

                break;
            case SOLVE_HILBERT:
                log("What is your n?");
                n = sc.nextInt();
                for (int i = 2; i < n; i++) {
                    log("Solving for n=" + i);
                    inputMatrix = MatrixGenerator.HilbertsMatrix(i);
                    double[] b = CustomMath.calculateWuchensB(inputMatrix.getN());
                    solution = new Matrix(CustomMath.makeArray2d(b));

                    result = QRFactorization.getQRGivens(inputMatrix);
                    qTb = result.getFirstMatrix().transpose().multiply(solution);
                    x = result.getSecondMatrix().solve(qTb);
                    log("Givens Solution:");
                    x.printMatrix();
                    log("Givens error: " + result.getError());
                    newLine(2);

                    result = QRFactorization.getQRHouseHolder(inputMatrix);
                    qTb = result.getFirstMatrix().transpose().multiply(solution);
                    x = result.getSecondMatrix().solve(qTb);
                    log("Householder solution:");
                    x.printMatrix();
                    log("Householder error: " + result.getError());
                    newLine(2);

                    result = LUDecomposition.getLUDecomposition(inputMatrix);
                    y = result.getFirstMatrix().solve(solution);
                    resultMatrix = result.getSecondMatrix().solve(y);
                    log("LU solution: ");
                    resultMatrix.printMatrix();
                    log("LU error: " + result.getError());
                    newLine(2);

                }
                break;
            case CONVOLUTION:
                break;
            case JACOBI:
                break;
            case GAUSS_SEIDEL:
                break;
            case DECODE:
                break;
            case POWER_METHOD:
                break;

            default:
                log("No function for that number!");
                break;
        }

        log("Command completed!");
        newLine(3);

        return true;
    }

    public String promptForMatrixPath() {
        sc.reset();
        log("Please enter your matrix path");
        return sc.next();
    }

    public String promptForAugmentedMatrixPath() {
        sc.reset();
        log("Please enter your *augmented* matrix path");
        return sc.next();
    }

    public void log(Object o) {
        System.out.println(o);
    }

    public void newLine() {
        log("");
    }

    public void newLine(int n) {
        for (int i = 0; i < n; i++) {
            log("");
        }
    }

    public Target getTarget(String s) {
        for (Target t: Target.values()) {
            if (t.name.equals(s)) {
                return t;
            }
        }
        return null;
    }
}
