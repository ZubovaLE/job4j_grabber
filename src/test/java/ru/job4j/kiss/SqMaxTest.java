package ru.job4j.kiss;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class SqMaxTest {

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 4", "-1, -1, 0, 0, 0", "0, 0, 0, 0, 0", "-1, -2, -3, -4, -1"})
    void max(int first, int second, int third, int fourth, int expected) {
        assertThat(SqMax.max(first, second, third, fourth)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 2", "-1, 0, 0", "0, 0, 0", "-1, -2, -1"})
    void max(int left, int right, int expected) {
        assertThat(SqMax.max(left, right)).isEqualTo(expected);
    }
}