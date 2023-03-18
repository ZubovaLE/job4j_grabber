package ru.job4j.kiss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private static final MaxMin maxMin = new MaxMin();
    private static final List<Integer> LIST = List.of(2, 5, 0, -1, 7);

    @Test
    @DisplayName("Test max")
    void max() {
        Comparator<Integer> comparator = Integer::compare;
        assertThat(maxMin.max(LIST, comparator)).isEqualTo(7);
    }

    @Test
    @DisplayName("Test min")
    void min() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o2, o1) * (-1);
        assertThat(maxMin.min(LIST, comparator)).isEqualTo(-1);
    }
}