package ru.job4j.ocp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorExampleTest {

    @Test
    @DisplayName("When sum 1 and 2 then return 3")
    public void whenSumOneAndTwoThenReturnThree() {
        //given
        int a = 1;
        int b = 2;
        int expected = 3;
        //when
        CalculatorExample.SimpleCalculator calculator = new CalculatorExample.SimpleCalculator();
        //then
        assertEquals(expected, calculator.sum(1, 2));
    }

    @Test
    @DisplayName("When multiply 1 and 2 then return 2")
    public void whenMultiplyOneAndTwoThenReturnTwo() {
        //given
        int a = 1;
        int b = 2;
        int expected = 2;
        //when
        CalculatorExample.SimpleCalculator calculator = new CalculatorExample.SimpleCalculator();
        //then
        assertEquals(expected, calculator.multiply(1, 2));
    }

    @Test
    @DisplayName("When subtract 1 and 2 then return -1")
    public void whenSubtractOneAndTwoThenReturnMinusOne() {
        //given
        int a = 1;
        int b = 2;
        int expected = -1;
        //when
        CalculatorExample.SimpleCalculator calculator = new CalculatorExample.SimpleCalculator();
        //then
        assertEquals(expected, calculator.subtract(1, 2));
    }
}