package com.u24689.neuralnetwork.cirno;

import com.u24689.neuralnetwork.cirno.exceptions.MatrixException;

import java.util.Random;

public class Network {
    int layer_count;
    Layer[] layers;

    Network(int[] layer_sizes, ActivationFunction[] activation_functions) {
        layer_count = layer_sizes.length;
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
        return layers[layer_count - 1].get_value();
    }

    public void feed_forward(double[] inputs) throws MatrixException {
        layers[0].set_value(inputs);
        for (int i = 1; i < layer_count; i += 1){
//            System.out.println(String.format("feeding forward: layer %d\n", i));
            layers[i].feed_forward(layers[i - 1]);
        }
    }

    public double calculate_error(double inputs[], double outputs[]) throws MatrixException {
        feed_forward(inputs);
        double cost = 0;
        for (int i = 0; i < outputs.length; i += 1) {
            cost += Math.pow((outputs[i] - layers[layer_count - 1].get_value().to_1d_array()[i]), 2);
        }
        return cost;
    }

    public void train(
            double[][] inputs, double[][] outputs, double learning_rate_start, double learning_rate_end,
            int training_time, String mode
    ) throws MatrixException {
//        for (int j = 0; j < layer_count; j += 1) {
//            layers[j].learning_rate = learning_rate;
//        }
        for (int i = 1; i <= training_time; i += 1) {
            for (int j = 0; j < layer_count; j += 1) {
                layers[j].set_learning_rate(
                        learning_rate_start - (learning_rate_start - learning_rate_end) * i / training_time);
            }
            if (mode == "full") {
                for (int j = 0; j < inputs.length; j += 1) {
//                    calculate_error(inputs[j], outputs[j]);
                    train_single(inputs[j], outputs[j]);
                }
            } else if (mode == "random") {
                int p_data = new Random().nextInt(inputs.length);
//                calculate_error(inputs[p_data], outputs[p_data]);
                train_single(inputs[p_data], outputs[p_data]);
            }
//            System.out.println(String.format("%.3f %.3f %.3f %.3f",
//                    calculate_error(inputs[0], outputs[0]), calculate_error(inputs[1], outputs[1]),
//                    calculate_error(inputs[2], outputs[2]), calculate_error(inputs[3], outputs[3])));
        }
    }

    private void train_single(double[] inputs, double[] outputs) throws MatrixException {
        feed_forward(inputs);
        layers[layer_count - 1].backpropagation(layers[layer_count - 2], new Matrix(outputs));
        for (int k = layer_count - 2; k > 0; k -= 1) {
            layers[k].backpropagation(layers[k - 1], null);
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
