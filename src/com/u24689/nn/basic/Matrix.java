package com.u24689.nn.basic;

/**
 * the class for matrix
 */
public class Matrix {
    int row, column;
    double[][] data;

    Matrix(int newRow, int newColumn) {
        row = newRow;
        column = newColumn;
        data = new double[row + 1][column + 1];
    }

    /**
     * create new matrix with an array, the array must be at least [newRow + 1][newColumn + 1]
     * @param newRow
     * @param newColumn
     * @param newData
     */

    Matrix(int newRow, int newColumn, double[][] newData) {
        row = newRow;
        column = newColumn;
        data = newData;
    }

    Matrix(double[] newData) {
        row = newData.length - 1;
        column = 1;
        data = new double[row + 1][column + 1];
        for(int i = 1; i <= row; i += 1) {
            data[i][1] = newData[i];
        }
    }

    Matrix(Matrix n) {
        row = n.row;
        column = n.column;
        data = n.data;
    }

    public void randomize(double min, double max) {
        if (min > max) {
            double p = min;
            min = max;
            max = p;
        }
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= column; j += 1) {
                data[i][j] = Math.random() * (max - min) + min;
            }
        }
    }

    public void add(double n) {
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= column; j += 1) {
                data[i][j] += n;
            }
        }
    }

    public void add(Matrix n) {
        if (!Matrix.isSameSize(this, n)) {
            return;
        }
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= column; j += 1) {
                data[i][j] += n.data[i][j];
            }
        }
    }

    public void multiply(double n) {
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= column; j += 1) {
                data[i][j] *= n;
            }
        }
    }

    public void multiply(Matrix n) {
        if (!canMultiply(this, n)) {
            return;
        }
        double[][] result = new double[row + 1][column + 1];
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= n.column; j += 1) {
                double sum = 0;
                for (int k = 1; k <= column; k += 1) {
                    sum += data[i][k] * data[k][j];
                }
                result[i][j] = sum;
            }
        }
        data = result;
    }

    public void print() {
        for (int i = 1; i <= row; i += 1) {
            for (int j = 1; j <= column; j += 1) {
                System.out.print(data[i][j]);
                System.out.print('\t');
            }
            System.out.println();
        }
    }

    public static boolean isSameSize(Matrix a, Matrix b) {
        if (a.column == b.column && a.row == b.row) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canMultiply(Matrix a, Matrix b) {
        if (a.column == b.row) {
            return true;
        } else {
            return false;
        }
    }

    public static Matrix add(Matrix a, double b) {
        Matrix result = new Matrix(a);
        result.add(b);
        return result;
    }

    public static Matrix add(Matrix a, Matrix b) {
        Matrix result = new Matrix(a);
        result.add(b);
        return result;
    }

    public static Matrix multiply(Matrix a, double b) {
        Matrix result = new Matrix(a);
        result.multiply(b);
        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.row, b.column);
        result.multiply(b);
        return result;
    }
}
