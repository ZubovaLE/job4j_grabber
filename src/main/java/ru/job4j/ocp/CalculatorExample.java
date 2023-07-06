package ru.job4j.ocp;

import java.util.function.BiFunction;

public class CalculatorExample {
    public static class SimpleCalculator extends AbstractCalculator<Integer> {
        public int sum(int a, int b) {
            return calculate(Integer::sum, a, b);
        }

        public int multiply(int a, int b) {
            return calculate((x, y) -> x * y, a, b);
        }

        public double subtract(int a, int b) {
            return calculate((x, y) -> x - y, a, b);
        }
    }

    private static class AbstractCalculator<T> {
        public T calculate(BiFunction<T, T, T> function, T first, T second) {
            return function.apply(first, second);
        }
    }
}
