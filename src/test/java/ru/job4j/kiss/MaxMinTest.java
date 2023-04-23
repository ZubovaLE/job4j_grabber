package ru.job4j.kiss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private static final MaxMin maxMin = new MaxMin();
    private static final Comparator<Integer> INTEGER_COMPARATOR = Integer::compare;

    private static Stream<Arguments> provideParametersForMaxWithInteger() {
        return Stream.of(
                Arguments.of(List.of(2, 5, 0, -1, 7), 7),
                Arguments.of(List.of(-1), -1),
                Arguments.of(List.of(-1, 0, 0, -2), 0),
                Arguments.of(List.of(), null),
                Arguments.of(null, null)
        );
    }

    private static Stream<Arguments> provideParametersForMinWithInteger() {
        return Stream.of(
                Arguments.of(List.of(2, 5, 0, -1, 7), -1),
                Arguments.of(List.of(0), 0),
                Arguments.of(List.of(-1, -2, -2, -1), -2),
                Arguments.of(List.of(), null),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForMaxWithInteger")
    @DisplayName("Test max in different cases")
    void testMaxWithInteger(List<Integer> values, Integer expected) {
        assertThat(maxMin.max(values, INTEGER_COMPARATOR)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForMinWithInteger")
    @DisplayName("Test min in different cases")
    void testMinWithInteger(List<Integer> values, Integer expected) {
        assertThat(maxMin.min(values, INTEGER_COMPARATOR)).isEqualTo(expected);
    }
}