package com.u24689.neuralnetwork.cirno;

public interface ActivationFunction {
    double calculate(double x);
    double derivative(double x);
    double calculated_derivative(double x);
    double reverse(double x);
}
