package com.example.apirestbooks.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTrueFalseTeoriaTest {

    @Test
    void testAssertTrue() {
        assertTrue(true);
    }

    @Test
    void testAssertFalse() {
        assertFalse(false);
    }
}
