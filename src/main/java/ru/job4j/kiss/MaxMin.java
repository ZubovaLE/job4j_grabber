package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicateForMax = val -> val > 0;
        return find(value, comparator, predicateForMax);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicateForMin = val -> val < 0;
        return find(value, comparator, predicateForMin);
    }

    private <T> T find(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        if (value.size() == 1) {
            return value.get(0);
        }
        if (value.size() >= 2) {
            T element = value.get(0);
            for (T el : value) {
                if (predicate.test(comparator.compare(el, element))) {
                    element = el;
                }
            }
            return element;
        }
        return null;
    }
}
