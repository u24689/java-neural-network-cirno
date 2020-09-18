package com.u24689.neuralnetwork.cirno;

public class Network {
    int layerCount;
    Layer[] layers;

    Network(int newLayerCount, int[] layerSizes) {
        layerCount = newLayerCount;
        layers = new Layer[layerCount];
        layers[0] = new Layer(1, layerSizes[0]);
        for (int i = 1; i < layerCount; i += 1) {
            layers[i] = new Layer(layerSizes[i], layerSizes[i - 1]);
        }
    }

    public void guess(double[] inputs) {
        layers[0].setValue(inputs);
        for (int i = 1; i < layerCount; i += 1){
            layers[i].feedForward(layers[i - 1]);
        }
    }

    public void calculateError(double[] inputs, double[] outputs) {
        guess(inputs);
        layers[layerCount - 1].calculateError(new Matrix(outputs));
        for(int i = layerCount - 2; i >= 0; i -= 1) {
            layers[i].calculateError(layers[i + 1]);
        }
        for(int i = 0; i < layerCount; i += 1) {
            System.out.println(String.format("Layer %d:", i));
            layers[i].print(false, false,false, true);
        }
    }

    public void calculateError() {
        // TODO: calculate error
    }

    public void train(double[][] inputs, double[][] outputs, double learning_rate, int training_time) {
        for (int i = 1; i <= training_time; i += 1) {
            // TODO: train
        }
    }

    public void print() {
        layers[layerCount].print(true, true, true, true);
    }
}
