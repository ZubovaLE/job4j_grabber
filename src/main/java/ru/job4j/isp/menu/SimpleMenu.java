package ru.job4j.isp.menu;

import java.util.Optional;

public class SimpleMenu implements Menu {
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        return false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.empty();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public MenuItemInfo next() {
        return null;
    }
}
