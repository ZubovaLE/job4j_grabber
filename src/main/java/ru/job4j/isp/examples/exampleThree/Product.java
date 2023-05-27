package ru.job4j.isp.examples.exampleThree;

import lombok.Getter;

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
@Getter
public abstract class Product {
    private String name;
    private int id;
    private float price;
    private String model;
    private String inputVoltage;
    private String outputVoltage;
}
