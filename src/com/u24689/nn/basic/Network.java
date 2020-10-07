package com.u24689.nn.basic;

public class Network {
    int layerCount;
    Layer[] layers;

    Network(int newLayerCount, int[] layerSizes) {
        layerCount = newLayerCount;
        layers = new Layer[layerCount + 1];
        for (int i = 1; i <= layerCount; i += 1) {
            layers[i] = new Layer(layerSizes[i - 1]);
        }
    }

    public void guess(double[] inputs) {
        layers[1].setValue(inputs);
        for (int i = 2; i <= layerCount; i += 1){
            layers[i].feedForward(layers[i - 1]);
        }
    }

    public void train(double[][] inputs, double[][] outputs, double learning_rate, int training_time) {
        for (int i = 1; i <= training_time; i += 1) {
            // TODO: train
        }
    }

    public void print() {
        layers[layerCount].print();
    }
}
