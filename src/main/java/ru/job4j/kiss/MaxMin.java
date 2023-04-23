package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        Predicate<Integer> predicateForMax = val -> val > 0;
        return find(values, comparator, predicateForMax);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        Predicate<Integer> predicateForMin = val -> val < 0;
        return find(values, comparator, predicateForMin);
    }

    private <T> T find(List<T> values, Comparator<T> comparator, Predicate<Integer> predicate) {
        if (checkArguments(values, comparator, predicate)) {
            T extremum = values.get(0);
            for (T value : values) {
                if (predicate.test(comparator.compare(value, extremum))) {
                    extremum = value;
                }
            }
            return extremum;
        }
        return null;
    }

    private <T> boolean checkArguments(List<T> values, Comparator<T> comparator, Predicate<Integer> predicate) {
        return values != null && !values.isEmpty() && comparator != null && predicate != null;
    }
}
