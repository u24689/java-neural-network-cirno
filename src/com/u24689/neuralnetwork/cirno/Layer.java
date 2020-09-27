package com.u24689.neuralnetwork.cirno;

import com.u24689.neuralnetwork.cirno.exceptions.MatrixException;

public class Layer {
    public Matrix value;
    public Matrix bias;
    public Matrix weight;
    public Matrix error;
    public int layer_size;
    public double learning_rate;
    public ActivationFunction activation_function;

    Layer (int new_layer_size, int previous_layer_size, ActivationFunction new_activation_function) {
        layer_size = new_layer_size;
        value = new Matrix(layer_size, 1);
        bias = new Matrix(layer_size, 1);
        weight = new Matrix(layer_size, previous_layer_size);
        activation_function = new_activation_function;
        error = new Matrix(layer_size, 1);
        value.randomize(0, 1);
        bias.randomize(0, 1);
        weight.randomize(0, 1);
    }

    public void feed_forward(Layer previousLayer) throws MatrixException {
        value = Matrix.multiply(weight, previousLayer.value);
        value.add(bias);
        value.map(Utility.sigmoid());
    }

//    public void calculate_error(Matrix answer) throws MatrixException {
//        error = Matrix.subtract(answer, value);
//    }
//
//    public void calculate_error(Layer next_layer) throws MatrixException {
//        error = Matrix.multiply(Matrix.transpose(next_layer.weight), next_layer.error);
//    }

    public void backpropagation(Layer previous_layer, Matrix answer) throws MatrixException {
        if (answer != null) {
            error = Matrix.subtract(answer, value);
        }
        Matrix gradients = Matrix.map(value, Utility.sigmoided_derivative());
        gradients.multiply_element_wise(error);

        Matrix delta_bias = gradients;
        delta_bias.multiply(learning_rate);
        Matrix delta_weights = Matrix.multiply(gradients, Matrix.transpose(previous_layer.value));
        delta_weights.multiply(learning_rate);

//        System.out.println("delta_weights:");
//        delta_weights.print();
//        System.out.println("delta_bias:");
//        delta_bias.print();
//        System.out.println();
//        System.out.println("weights:");
//        weight.print();

        weight.add(delta_weights);
        bias.add(delta_bias);

        previous_layer.error = Matrix.multiply(Matrix.transpose(weight), gradients);

//        System.out.println("weights:");
//        weight.print();
//        System.out.println();
    }

    public void print(boolean print_value, boolean print_bias, boolean print_weights, boolean print_error) {
        if (print_value) {
            System.out.println("values:");
            value.print();
        }
        if (print_bias) {
            System.out.println("bias:");
            bias.print();
        }
        if (print_weights) {
            System.out.println("weights:");
            weight.print();
        }
        if (print_error) {
            System.out.println("errors:");
            error.print();
        }
        System.out.println();
    }

    public void setValue(Matrix valueMatrix) {
        value = valueMatrix;
    }

    public void setValue(double[] valueArray) {
        value = new Matrix(valueArray);
    }
}
