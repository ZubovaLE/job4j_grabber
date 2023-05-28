package ru.job4j.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        StringBuilder builder = new StringBuilder();
        int depth;
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            depth = menuItemInfo.getNumber().split("\\.").length;
            builder.append(depth >= 2 ? "--".repeat(depth) : "");
            buildMenuLine(builder, menuItemInfo);
        }
        System.out.println(builder);
    }

    private void buildMenuLine(StringBuilder builder, Menu.MenuItemInfo menuItemInfo) {
        builder
                .append(menuItemInfo.getNumber())
                .append(" ")
                .append(menuItemInfo.getName())
                .append(System.lineSeparator());
    }
}