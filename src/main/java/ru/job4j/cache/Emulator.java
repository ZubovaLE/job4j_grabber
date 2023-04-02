package ru.job4j.cache;

import java.util.List;
import java.util.Scanner;

public class Emulator {
    private final static String SEPARATOR = System.lineSeparator();
    private final static List<String> menuOptions = List.of(
            "Specify directory", "Load file content into cache", "Get file content from cache", "Exit");
    private static String menuContent;
    private final Scanner scanner = new Scanner(System.in);

    private static void createMenuContent() {
        StringBuilder menuContentBuilder = new StringBuilder("Choose an option:");
        int i = 1;
        for (String option : menuOptions) {
            menuContentBuilder.append(i++).append(" ").append(option).append(SEPARATOR);
        }
        menuContent = menuContentBuilder.toString();
    }

    public void init() {
        boolean run = true;
        int select;
        createMenuContent();
        while (run) {
            showMenu();
            select = askOption(menuContent);
            if (select < 0 || select >= menuOptions.size()) {
                System.out.println("Wrong input, you can select: 0 .. " + (menuOptions.size() - 1));
                continue;
            }
            if (select == menuOptions.indexOf("Exit")) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println(menuContent);
    }

    private int askOption(String menu) {
        boolean invalid = true;
        int answer = -1;
        do {
            System.out.println(menu);
            try {
                answer = Integer.parseInt(scanner.nextLine());
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return answer;
    }
}