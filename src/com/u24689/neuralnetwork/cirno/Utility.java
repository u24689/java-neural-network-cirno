package com.u24689.neuralnetwork.cirno;

public class Utility {
    public static ActivationFunction sigmoid() {
        return new ActivationFunction() {
            @Override
            public double calculate(double x) {
                return (1 / (1 + Math.pow(Math.E, -x)));
            }

            @Override
            public double derivative(double x) {
                return (calculate(x) * (1 - calculate(x)));
            }

            @Override
            public double calculated_derivative(double x) {
                return (x * (1 - x));
            }

            @Override
            public double reverse(double x) {
                return (-Math.log((1 - x) / x));
            }
        };
    }
}
