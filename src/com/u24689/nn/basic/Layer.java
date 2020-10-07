package com.u24689.nn.basic;

public class Layer {
    public Matrix value;
    public Matrix bias;
    public Matrix weight;
    public Matrix error;
    public int layerSize;

    Layer (int newLayerSize) {
        layerSize = newLayerSize;
        value = new Matrix(layerSize, 1);
        bias = new Matrix(layerSize, 1);
        weight = new Matrix(layerSize, layerSize);
        error = new Matrix(layerSize, 1);
        value.randomize(0, 1);
        bias.randomize(0, 1);
        weight.randomize(0, 1);
    }

    public void feedForward(Layer previousLayer) {
        value = Matrix.multiply(weight, previousLayer.value);
        value.add(bias);
    }

    public void calculateError() {
        // TODO: calculate error
    }

    public void backpropagation(Layer nextLayer) {
        // TODO: backpropagation
    }

    public void print() {
        System.out.println("values:");
        value.print();
        System.out.println("bias:");
        bias.print();
        System.out.println("weights:");
        weight.print();
        System.out.println("\n");
    }

    public void setValue(Matrix valueMatrix) {
        value = valueMatrix;
    }

    public void setValue(double[] valueArray) {
        value = new Matrix(valueArray);
    }
}
