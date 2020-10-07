package com.u24689.nn.basic;

public class Main {
    double inputs[][] = new double[][] {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
    };
    double outputs[][] = new double[][] {
            {0},
            {1},
            {1},
            {0}
    };

    public static void main(String[] args) {
        Network net = new Network(3, new int[]{0, 2, 2, 1});
        net.guess(new double[]{0, 1, 1});
        net.print();
    }
}
