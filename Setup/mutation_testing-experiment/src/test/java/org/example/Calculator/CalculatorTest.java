package org.example.Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void isPositive() {
        assertTrue(calculator.isPositive(1));

        // check if a negative number returns False
        assertFalse(calculator.isPositive(-1));
    }


    @Test
    void testZeroBoundary() {
        // assertTrue(calculator.isPositive(0));
    }
}