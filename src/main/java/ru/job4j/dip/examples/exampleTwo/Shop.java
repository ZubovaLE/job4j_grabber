package ru.job4j.dip.examples.exampleTwo;

/**
 * Имеется класс Shop (Магазин).
 * У Магазина есть метод sellProduct. Тут наблюдается нарушение DIP, так как метод принимает конкретную реализацию, а не
 * абстракцию. Метод должен принимать Customer customer. Тогда в метод можно будет передавать любую реализацию, т.к. нет
 * прямой зависимости.
 */
public class Shop {
    public boolean sellProduct(Corporation corporation, String productName) {
        return corporation.buy(productName);
    }
}
