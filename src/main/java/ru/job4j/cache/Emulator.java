package ru.job4j.cache;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Emulator {
    private final static String SEPARATOR = System.lineSeparator();
    private final static List<String> menuOptions = List.of(
            "Specify directory", "Load file content into cache", "Get file content from cache", "Exit");
    private final static Scanner ANSWERS_SCANNER = new Scanner(System.in);
    private static String menuContent;

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache(new File(System.getProperty("user.dir") + "\\src\\main\\java\\ru\\job4j\\cache\\data").getAbsolutePath());
        Emulator emulator = new Emulator();
        emulator.init(dirFileCache);
    }

    public void init(DirFileCache dirFileCache) {
        int select;
        boolean run = true;
        createMenuContent();
        while (run) {
            select = askOption(menuContent);
            if (select < 0 || select > menuOptions.size()) {
                System.out.println("Wrong input, you can select: 0 .. " + menuOptions.size());
                continue;
            }
            switch (select) {
                case 1:
                    System.out.print("Write directory path: ");
                    dirFileCache = new DirFileCache(ANSWERS_SCANNER.nextLine());
                    break;
                case 2:
                    System.out.print("Write file name: ");
                    String fileName = ANSWERS_SCANNER.nextLine();
                    if (dirFileCache != null) {
                       dirFileCache.load(fileName);
                    }
                    break;
                case 3:
                    System.out.print("Write file name: ");
                    String key = ANSWERS_SCANNER.nextLine();
                    if (dirFileCache != null) {
                        System.out.println(dirFileCache.get(key));
                    }
                    break;
                case 4:
                    run = false;
                    System.out.println("Thank you!");
            }
        }
    }

    private static void createMenuContent() {
        StringBuilder menuContentBuilder = new StringBuilder("Choose an option:").append(SEPARATOR);
        int i = 1;
        for (String option : menuOptions) {
            menuContentBuilder.append(i++).append(" ").append(option).append(SEPARATOR);
        }
        menuContent = menuContentBuilder.toString();
    }

    private int askOption(String menu) {
        boolean invalid = true;
        int answer = -1;
        do {
            System.out.print(menu);
            try {
                answer = Integer.parseInt(ANSWERS_SCANNER.nextLine());
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return answer;
    }


}