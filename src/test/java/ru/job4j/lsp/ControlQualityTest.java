package ru.job4j.lsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.job4j.lsp.storage.Shop;
import ru.job4j.lsp.storage.Trash;
import ru.job4j.lsp.storage.Warehouse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    @DisplayName("Test sendFoodToCorrectStorage")
    void sendFoodToCorrectStorage() {
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(shop, warehouse, trash);
        Food foodToWarehouse = new Food("Food One", LocalDate.now().plusDays(100), LocalDate.now().minusDays(1), 100.00, 50);
        Food foodToShop = new Food("Food Two", LocalDate.now().plusDays(50), LocalDate.now().minusDays(50), 100.00, 50);
        Food foodToShopWithDiscount = new Food("Food Three", LocalDate.now().plusDays(20), LocalDate.now().minusDays(80), 100.00, 80);
        Food foodToTrash = new Food("Food Four", LocalDate.now().minusDays(1), LocalDate.now().minusDays(30), 100.00, 50);
        controlQuality.sendFoodToCorrectStorage(foodToWarehouse);
        controlQuality.sendFoodToCorrectStorage(foodToShop);
        controlQuality.sendFoodToCorrectStorage(foodToShopWithDiscount);
        controlQuality.sendFoodToCorrectStorage(foodToTrash);
        assertThat(warehouse.showProductsInStorage()).hasSize(1).contains(foodToWarehouse);
        assertThat(shop.showProductsInStorage()).hasSize(2).contains(foodToShop, foodToShopWithDiscount);
        assertThat(foodToShopWithDiscount.getPrice()).isEqualTo(20);
        assertThat(trash.showProductsInStorage()).hasSize(1).contains(foodToTrash);
    }
}