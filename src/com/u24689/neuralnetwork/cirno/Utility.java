package com.u24689.neuralnetwork.cirno;

public class Utility {
    public static ActivationFunction sigmoid() {
        return x -> (1 / (1 + Math.pow(Math.E, -x)));
    }
    public static ActivationFunction sigmoid_derivative() {
        return x -> (sigmoid().calculate(x) * (1 - sigmoid().calculate(x)));
    }
    public static ActivationFunction sigmoided_derivative() {
        return x -> (x * (1 - x));
    }
}
