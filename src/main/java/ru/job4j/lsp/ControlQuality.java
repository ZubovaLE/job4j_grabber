package ru.job4j.lsp;

import ru.job4j.lsp.shipment.*;
import ru.job4j.lsp.storage.*;

import java.time.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {
    private final StorageConditionSetter storageConditionSetter;

    public ControlQuality(StorageConditionSetter storageConditionSetter) {
        this.storageConditionSetter = storageConditionSetter;
    }

    public void sendFoodToCorrectStorage(Food food) {
        for (Shipment shipment : storageConditionSetter.getShipments()) {
            if (shipment.acceptFood(food)) {
                shipment.shipFoodToStorage(food);
                break;
            }
        }
    }

    public void resort() {
        for (Storage storage : storageConditionSetter.getStorages()) {
            for (Food food : storage.showProductsInStorage()) {
                storage.clear();
                sendFoodToCorrectStorage(food);
            }
        }
    }

    private long calculateExpiryDatePercentage(LocalDate today, LocalDate createDate, LocalDate expiryDate) {
        return DAYS.between(createDate, today) * 100 / DAYS.between(createDate, expiryDate);
    }
}