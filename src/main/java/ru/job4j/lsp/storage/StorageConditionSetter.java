package ru.job4j.lsp.storage;

import ru.job4j.lsp.shipment.*;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class StorageConditionSetter {
    private final List<Shipment> shipments;
    private final List<Storage> storages;

    public StorageConditionSetter(Shop shop, Warehouse warehouse, Trash trash) {
        storages = List.of(shop, warehouse, trash);
        shipments = List.of(
                new TrashShipment(trash, food -> (food.getExpiryDate().isBefore(LocalDate.now()))),
                new WarehouseShipment(warehouse, food -> food.getExpiryDate().isAfter(LocalDate.now()) &&
                        calculateExpiryDatePercentage(LocalDate.now(), food.getCreateDate(), food.getExpiryDate()) < 25),
                new ShopShipmentWithoutDiscount(shop, food ->
                {
                    long duration = calculateExpiryDatePercentage(LocalDate.now(), food.getCreateDate(), food.getExpiryDate());
                    return duration >= 25 && duration <= 75;
                }),
                new DiscountShopShipment(shop, food -> calculateExpiryDatePercentage(LocalDate.now(), food.getCreateDate(), food.getExpiryDate()) > 75));
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    private long calculateExpiryDatePercentage(LocalDate today, LocalDate createDate, LocalDate expiryDate) {
        return DAYS.between(createDate, today) * 100 / DAYS.between(createDate, expiryDate);
    }
}