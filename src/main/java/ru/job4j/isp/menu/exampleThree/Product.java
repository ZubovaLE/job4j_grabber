package ru.job4j.isp.menu.exampleThree;

/**
 * Имеется интерфейс продукта.
 * Каждый продукт имеет имя, идентификатор и цену.
 * Не каждый продукт характеризуется моделью или входным/выходным напряжением, поэтому у некоторых продуктов придётся
 * реализовывать ненужные методы.
 * Здесь необходимо разделение интерфейсов (например, оставить интерфейс Product с тремя первыми методами,
 * добавить интерфейс Characteristic с методами getModel(), getType() и т.д,
 * добавить интерфейс ElectricalCharacteristics с
 * методами getInputVoltage(), getOutputVoltage(), getResistance()).
 */
public interface Product {
    String getName();

    int getId();

    float getPrice();

    void getModel();

    void getInputVoltage();

    void getOutputVoltage();
}
