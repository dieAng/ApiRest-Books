package com.example.apirestbooks.examples;

public class Calculadora {
    float sumar(float a, float b) {
        return a + b;
    }

    float restar(float a, float b) {
        return a - b;
    }

    float multiplicar(float a, float b) {
        return a * b;
    }

    float dividir(float a, float b) {
        if (b == 0) throw new IllegalArgumentException("No se puede dividir por cero");
        return a / b;
    }
}
