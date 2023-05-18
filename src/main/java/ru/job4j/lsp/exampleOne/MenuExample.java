package ru.job4j.lsp.exampleOne;

public class MenuExample {
    // Precondition: 0 < num <= 5
    public void selectMenuItem(int num) {
        if (num > 0 && num < 5) {
            //do something
            System.out.printf("Выбран %d пункт меню", num);
        } else {
            throw new IllegalArgumentException("Input out of range 1-5");
        }
    }
}