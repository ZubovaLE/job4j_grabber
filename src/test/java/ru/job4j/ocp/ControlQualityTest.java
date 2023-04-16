package ru.job4j.ocp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.ocp.storage.Shop;
import ru.job4j.ocp.storage.Trash;
import ru.job4j.ocp.storage.Warehouse;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    @DisplayName("Test sendFoodToCorrectStorage")
    void sendFoodToCorrectStorage() {
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(shop, warehouse, trash);
        Food foodOne = new Food("one", LocalDate.of(2023, Month.APRIL, 17), LocalDate.of(2023, Month.APRIL, 4), 100.00, 80);
        Food foodTwo = new Food("two", LocalDate.of(2023, Month.APRIL, 13), LocalDate.of(2023, Month.APRIL, 4), 100.00, 50);
        Food foodThree = new Food("three", LocalDate.of(2023, Month.AUGUST, 27), LocalDate.of(2023, Month.APRIL, 1), 100.00, 50);
        controlQuality.sendFoodToCorrectStorage(foodOne);
        controlQuality.sendFoodToCorrectStorage(foodTwo);
        controlQuality.sendFoodToCorrectStorage(foodThree);
        assertThat(shop.showProductsInStorage()).hasSize(1).contains(foodOne);
        assertThat(warehouse.showProductsInStorage()).hasSize(1).contains(foodThree);
        assertThat(trash.showProductsInStorage()).hasSize(1).contains(foodTwo);
    }
}