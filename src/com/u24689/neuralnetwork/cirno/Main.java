package com.u24689.neuralnetwork.cirno;

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

    public static void main(String[] args) {
        Network net = new Network(3, new int[]{2, 2, 1});
        net.calculateError(inputs[0], outputs[0]);
    }
}
