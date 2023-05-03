package ru.job4j.lsp;

import ru.job4j.lsp.shipment.*;
import ru.job4j.lsp.storage.*;

import java.util.ArrayList;
import java.util.List;

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
            List<Food> old = new ArrayList<>(storage.getAllProducts());
            storage.clear();
            for (Food food : old) {
                sendFoodToCorrectStorage(food);
            }
        }
    }
}