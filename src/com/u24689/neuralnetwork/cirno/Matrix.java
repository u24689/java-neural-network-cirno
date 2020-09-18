package com.u24689.neuralnetwork.cirno;

/**
 * the class for matrix
 */
public class Matrix {
    int row, column;
    double[][] data;

    Matrix(int newRow, int newColumn) {
        row = newRow;
        column = newColumn;
        data = new double[row][column];
    }
    Matrix(double[][] newData) {
        row = newData.length;
        column = newData[0].length;
        data = newData;
    }
    Matrix(double[] newData) {
        row = newData.length;
        column = 1;
        data = new double[row][column];
        for(int i = 0; i < row; i += 1) {
            data[i][0] = newData[i];
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
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] = Math.random() * (max - min) + min;
            }
        }
    }
    public void add(double n) {
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] += n;
            }
        }
    }
    public void add(Matrix n) {
        if (!Matrix.isSameSize(this, n)) {
            System.out.println(String.format("Can't add matrix with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
            return;
        }
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] += n.data[i][j];
            }
        }
    }
    public void subtract(double n) {
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] -= n;
            }
        }
    }
    public void subtract(Matrix n) {
        if (!Matrix.isSameSize(this, n)) {
            System.out.println(String.format("Can't subtract matrix with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
            return;
        }
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] -= n.data[i][j];
            }
        }
    }
    public void multiply(double n) {
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] *= n;
            }
        }
    }
    public void multiply(Matrix n) {
        if (!canMultiply(this, n)) {
            System.out.println(String.format("Can't multiply matrix with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
            return;
        }
        double[][] result = new double[row][column];
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < n.column; j += 1) {
                double sum = 0;
                for (int k = 0; k < column; k += 1) {
                    sum += data[i][k] * data[k][j];
                }
                result[i][j] = sum;
            }
        }
        data = result;
    }
    public void transpose() {
        Matrix p = new Matrix(data);
        row = p.column;
        column = p.row;
        data = new double[row][column];
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] = p.data[j][i];
            }
        }
    }
    public void print() {
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
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
    public static Matrix subtract(Matrix a, double b) {
        a.subtract(b);
        return a;
    }
    public static Matrix subtract(Matrix a, Matrix b) {
        a.subtract(b);
        return a;
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
    public static Matrix transpose(Matrix a) {
        a.transpose();
        return a;
    }
}
