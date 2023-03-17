package ru.job4j.kiss;

public class SqMax {
    public static int max(int left, int right) {
        return Math.max(left, right);
    }

    public static int max(int first, int second, int third, int fourth) {
        return max (
                max(first, second),
                max(third, fourth)
        );
    }
}