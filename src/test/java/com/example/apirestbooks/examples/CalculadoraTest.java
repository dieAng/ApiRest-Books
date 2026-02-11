package com.example.apirestbooks.examples;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    @BeforeAll
    static void setup() {
        System.out.println("Iniciando pruebas de la Calculadora...");
    }

    @AfterAll
    static void teardown() {
        System.out.println("Pruebas de la Calculadora finalizadas.");
    }

    @BeforeEach
    void init() {
        System.out.println("Preparando un nuevo test...");
    }

    @AfterEach
    void cleanup() {
        System.out.println("Test finalizado.");
    }

    @Test
    @DisplayName("Prueba de suma")
    void testSuma() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.sumar(2, 3));
    }

    @Test
    @DisplayName("Prueba de resta")
    void testResta() {
        Calculadora calc = new Calculadora();
        assertEquals(1, calc.restar(3, 2));
    }

    @Test
    @DisplayName("Prueba de multiplicación")
    void testMultiplicacion() {
        Calculadora calc = new Calculadora();
        assertEquals(6, calc.multiplicar(2, 3));
    }

    @Test
    @DisplayName("Prueba de división")
    void testDivision() {
        Calculadora calc = new Calculadora();
        assertEquals(2, calc.dividir(6, 3));
    }

    @Test
    @DisplayName("Prueba de división por cero")
    void testDivisionPorCero() {
        Calculadora calc = new Calculadora();
        try {
            calc.dividir(10, 0);
            assert false; // Debería lanzar una excepción
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("No se puede dividir por cero");
        }
    }

    @Test
    @DisplayName("Prueba deshabilitada")
    @Disabled("Esta prueba está deshabilitada")
    void testDeshabilitado() {
        // Esta prueba no se ejecutará
        assert true;
    }
}
