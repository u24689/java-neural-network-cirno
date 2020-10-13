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
    static int layer_sizes[] = new int[] {2, 4, 1};
    static ActivationFunction activation_functions[] = new ActivationFunction[] {
            Utility.sigmoid(),
            Utility.sigmoid(),
            Utility.sigmoid(),
            Utility.sigmoid()
    };
    static double learning_rate_start = 1, learning_rate_end = 1;

    public static void main(String[] args) {
        Network net = new Network(layer_sizes, activation_functions);

        try {
            net.train(inputs, outputs, learning_rate_start, learning_rate_end, 100000, "random");
        } catch (MatrixException e) {
            e.printStackTrace();
        }

//        net.print_network();
        try {
            for (int i = 0; i < inputs.length; i += 1) {
//                System.out.println("guess:");
                net.guess(inputs[i]).print();
//                System.out.println("answer:");
//                new Matrix(outputs[i]).print();
//                System.out.println();
            }
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }
}
