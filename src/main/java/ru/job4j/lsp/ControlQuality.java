package ru.job4j.lsp;

import ru.job4j.lsp.shipment.*;
import ru.job4j.lsp.storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {
    private final ShipmentConditions shipmentConditions;

    public ControlQuality(ShipmentConditions shipmentConditions) {
        this.shipmentConditions = shipmentConditions;
    }

    public void sendFoodToCorrectStorage(Food food) {
        for (Shipment shipment : shipmentConditions.getShipments()) {
            if (shipment.acceptFood(food)) {
                shipment.shipFoodToStorage(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> allProducts = collectAllProductsFromStorages();
        shipmentConditions.getStorages().forEach(Storage::clear);
        allProducts.forEach(this::sendFoodToCorrectStorage);
    }

    private List<Food> collectAllProductsFromStorages() {
        return shipmentConditions.getStorages().stream()
                .flatMap(s -> s.getAllProducts().stream())
                .collect(Collectors.toList());
    }
}