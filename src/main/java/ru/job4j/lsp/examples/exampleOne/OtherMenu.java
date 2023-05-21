package ru.job4j.lsp.examples.exampleOne;

public class OtherMenu extends MenuExample {
    @Override
    // precondition: 0 < num <= 3
    public void selectMenuItem(int num) {
        if (num > 0 && num < 3) {
            //do something
            System.out.printf("Выбран %d пункт меню", num);
        } else {
            throw new IllegalArgumentException("Input out of range 1-3");
        }
    }
}