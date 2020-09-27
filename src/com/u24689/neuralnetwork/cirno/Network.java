package com.u24689.neuralnetwork.cirno;

import com.u24689.neuralnetwork.cirno.exceptions.MatrixException;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.Random;

public class Network {
    int layer_count;
    Layer[] layers;

    Network(int new_layer_count, int[] layer_sizes, ActivationFunction[] activation_functions) {
        layer_count = new_layer_count;
        layers = new Layer[layer_count];
//        System.out.println("new layer 0");
        layers[0] = new Layer(layer_sizes[0], 1, activation_functions[0]);
        for (int i = 1; i < layer_count; i += 1) {
//            System.out.println(String.format("new layer %d", i));
            layers[i] = new Layer(layer_sizes[i], layer_sizes[i - 1], activation_functions[i]);
        }
    }

    public Matrix guess(double[] inputs) throws MatrixException {
        feed_forward(inputs);
        return layers[layer_count - 1].value;
    }

    public void feed_forward(double[] inputs) throws MatrixException {
        layers[0].setValue(inputs);
        for (int i = 1; i < layer_count; i += 1){
//            System.out.println(String.format("feeding forward: layer %d\n", i));
            layers[i].feed_forward(layers[i - 1]);
        }
    }

//    public void calculate_error(double[] inputs, double[] outputs) throws MatrixException{
//        feed_forward(inputs);
//        layers[layer_count - 1].calculate_error(new Matrix(outputs));
//        for(int i = layer_count - 2; i >= 0; i -= 1) {
//            layers[i].calculate_error(layers[i + 1]);
//        }
//    }

    public void calculate_error() {
        // TODO: calculate error
    }

    public void train(
            double[][] inputs, double[][] outputs, double learning_rate, int training_time, String mode
    ) throws MatrixException {
        for (int i = 0; i < layer_count; i += 1) {
            layers[i].learning_rate = learning_rate;
        }
        for (int i = 1; i <= training_time; i += 1) {
            if (mode == "full") {
                for (int j = 0; j < inputs.length; j += 1) {
//                    calculate_error(inputs[j], outputs[j]);
                    layers[layer_count - 1].backpropagation(layers[layer_count - 2], new Matrix(outputs[j]));
                    for (int k = layer_count - 2; k > 0; k -= 1) {
                        layers[k].backpropagation(layers[k - 1], null);
                    }
                }
            } else if (mode == "random") {
                int p_data = new Random().nextInt(inputs.length);
//                calculate_error(inputs[p_data], outputs[p_data]);
                layers[layer_count - 1].backpropagation(layers[layer_count - 2], new Matrix(outputs[p_data]));
                for (int k = layer_count - 2; k > 0; k -= 1) {
                    layers[k].backpropagation(layers[k - 1], null);
                }
            }
        }
    }

    public void print() {
        layers[layer_count - 1].print(true, true, true, true);
    }

    public void print_network() {
        System.out.println(String.format("There are %d layers:", layer_count));
        System.out.println("--------------------------------");
        for (int i = 0; i < layer_count; i += 1) {
            System.out.println(String.format("Layer %d:", i));
            layers[i].print(false, true, true, false);
            System.out.println("--------------------------------");
        }
        System.out.println();
    }
}
