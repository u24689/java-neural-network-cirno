package com.u24689.neuralnetwork.cirno;

import com.u24689.neuralnetwork.cirno.exceptions.MatrixException;

/**
 * the class for matrix
 */
public class Matrix {
    int row, column;
    double[][] data;

    Matrix(int new_row, int new_column) {
        row = new_row;
        column = new_column;
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
    public void add(Matrix n) throws MatrixException{
        if (!Matrix.isSameSize(this, n)) {
            throw new MatrixException(String.format("Can't add matrix with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
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
    public void subtract(Matrix n) throws MatrixException{
        if (!Matrix.isSameSize(this, n)) {
            throw new MatrixException(String.format("Can't subtract matrices with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
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
    public void multiply(Matrix n) throws MatrixException {
        if (!canMultiply(this, n)) {
            throw new MatrixException(String.format("Can't multiply matrices with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
        }
        double[][] result = new double[row][n.column];
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < n.column; j += 1) {
                double sum = 0;
                for (int k = 0; k < column; k += 1) {
//                    System.out.println(String.format("adding [%d][%d] and [%d][%d]\n", i, k, k, j));
                    sum += data[i][k] * n.data[k][j];
                }
                result[i][j] = sum;
            }
        }
        column = n.column;
        data = result;
    }
    public void multiply_element_wise(Matrix n) throws MatrixException {
        if (!isSameSize(this, n)) {
            throw new MatrixException(String.format("Can't element-wise multiply matrices with sizes [%d, %d] and [%d, %d]",
                    row, column, n.row, n.column));
        }
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                data[i][j] *= n.data[i][j];
            }
        }
    }

    /**
     * map an activation function to a matrix
     * @param func
     * @param method: can be calculate, derivative, calculated_derivative and reverse
     */
    public void map(ActivationFunction func, String method) {
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                if (method == "calculate") {
                    data[i][j] = func.calculate(data[i][j]);
                } else if (method == "derivative") {
                    data[i][j] = func.derivative(data[i][j]);
                } else if (method == "calculated_derivative") {
                    data[i][j] = func.calculated_derivative(data[i][j]);
                } else if (method == "reverse") {
                    data[i][j] = func.reverse(data[i][j]);
                }
            }
        }
    }
    public double[] to_1d_array() throws MatrixException {
        if (row != 1 && column != 1) {
            throw new MatrixException(String.format("Can't convert matrix with size [%d, %d] to 1d array", row, column));
        }
        double[] result;
        if (row == 1) {
            result = new double[column];
            for (int i = 0; i < column; i += 1) {
                result[i] = data[0][i];
            }
        } else {
            result = new double[row];
            for (int i = 0; i < row; i += 1) {
                result[i] = data[i][0];
            }
        }
        return result;
    }
    public void transpose() {
        Matrix p = new Matrix(this);
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
                System.out.print(String.format("%.5f\t", data[i][j]));
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
    public static Matrix add(Matrix a, Matrix b) throws MatrixException {
        Matrix result = new Matrix(a);
        result.add(b);
        return result;
    }
    public static Matrix subtract(Matrix a, double b) {
        a.subtract(b);
        return a;
    }
    public static Matrix subtract(Matrix a, Matrix b) throws MatrixException {
        a.subtract(b);
        return a;
    }
    public static Matrix multiply(Matrix a, double b) {
        Matrix result = new Matrix(a);
        result.multiply(b);
        return result;
    }
    public static Matrix multiply(Matrix a, Matrix b) throws MatrixException {
        Matrix result = new Matrix(a);
        result.multiply(b);
        return result;
    }
    public static Matrix multiply_element_wise(Matrix a, Matrix b) throws MatrixException {
        Matrix result = new Matrix(a);
        result.multiply_element_wise(b);
        return result;
    }
    public static Matrix map(Matrix a, ActivationFunction func, String method) {
        Matrix result = new Matrix(a);
        result.map(func, method);
        return result;
    }
    public static double[] to_1d_array(Matrix a) throws MatrixException {
        return a.to_1d_array();
    }
    public static Matrix transpose(Matrix a){
        Matrix result = new Matrix(a);
        result.transpose();
        return result;
    }
}
