package ru.job4j.ocp;

import ru.job4j.ocp.shipment.*;
import ru.job4j.ocp.storage.Shop;
import ru.job4j.ocp.storage.Trash;
import ru.job4j.ocp.storage.Warehouse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ControlQuality {
    private final Trash trash = new Trash();
    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();

    private List<Shipment> shipments = List.of(
            new WarehouseShipment(f -> true),
            new ShopShipmentWithoutDiscount(f -> true),
            new DiscountShopShipment(f -> true),
            new TrashShipment(f -> true)
    );
    
//    Predicate<Food> predicate = f -> (Duration.between(f.getExpiryDate(), LocalDate.now()))/(Duration.between(f.getExpiryDate(), f.getCreateDate())) * 100 < 25;
}
