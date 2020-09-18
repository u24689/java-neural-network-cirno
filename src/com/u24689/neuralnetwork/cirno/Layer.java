package com.u24689.neuralnetwork.cirno;

public class Layer {
    public Matrix value;
    public Matrix bias;
    public Matrix weight;
    public Matrix error;
    public int layerSize;

    Layer (int newLayerSize, int previousLayerSize) {
        layerSize = newLayerSize;
        value = new Matrix(layerSize, 1);
        bias = new Matrix(layerSize, 1);
        weight = new Matrix(layerSize, previousLayerSize);
        error = new Matrix(layerSize, 1);
        value.randomize(0, 1);
        bias.randomize(0, 1);
        weight.randomize(0, 1);
    }

    public void feedForward(Layer previousLayer) {
        value = Matrix.multiply(weight, previousLayer.value);
        try {
            value.add(bias);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void calculateError(Matrix answer) {
        error = Matrix.subtract(answer, value);
    }

    public void calculateError(Layer nextLayer) {
        System.out.println("error equals");
        Matrix.transpose(nextLayer.weight).print();
        System.out.println("multiply");
        nextLayer.error.print();
        System.out.println("which is");
        error = Matrix.multiply(Matrix.transpose(nextLayer.weight), nextLayer.error);
        error.print();
        System.out.println();
    }

    public void backpropagation(Layer nextLayer) {
        // TODO: backpropagation
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
