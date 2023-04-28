package ru.job4j.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        StringBuilder builder = new StringBuilder();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int depth = menuItemInfo.getNumber().split("\\.").length;
            if (depth >= 2) {
                builder.append("--".repeat(depth)).append(menuItemInfo.getNumber()).append(menuItemInfo.getName()).append(System.lineSeparator());
            } else {
                builder.append(menuItemInfo.getNumber()).append(menuItemInfo.getName()).append(System.lineSeparator());
            }
        }
        System.out.println(builder);
    }
}