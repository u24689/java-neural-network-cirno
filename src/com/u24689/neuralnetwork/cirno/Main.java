package com.u24689.neuralnetwork.cirno;

import com.u24689.neuralnetwork.cirno.exceptions.MatrixException;

public class Main {
    static double inputs[][] = new double[][] {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
    };
    static double outputs[][] = new double[][] {
            {0},
            {1},
            {1},
            {0}
    };
    static int layer_sizes[] = new int[] {2, 2, 1};
    static ActivationFunction afs[] = new ActivationFunction[] {
            Utility.sigmoid(),
            Utility.sigmoid(),
            Utility.sigmoid()
    };
    static double learning_rate = 0.01;

    public static void main(String[] args) {
        Network net = new Network(layer_sizes.length, layer_sizes, afs);

        net.print_network();
        try {
            for (int i = 0; i < inputs.length; i += 1) {
                net.guess(inputs[i]).print();
            }
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        try {
            net.train(inputs, outputs, learning_rate, 10000, "random");
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        net.print_network();
        try {
            for (int i = 0; i < inputs.length; i += 1) {
                net.guess(inputs[i]).print();
            }
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }
}
