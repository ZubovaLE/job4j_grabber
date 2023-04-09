package ru.job4j.cache;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Emulator {
    private final static String SEPARATOR = System.lineSeparator();
    private final static List<String> MENU_OPTIONS = List.of(
            "1 Specify directory", "2 Load file content into cache", "3 Get file content from cache", "4 Exit");
    private final static Scanner ANSWERS_SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache(new File(System.getProperty("user.dir") + "\\src\\main\\java\\ru\\job4j\\cache\\data").getAbsolutePath());
        Emulator emulator = new Emulator();
        emulator.init(dirFileCache);
    }

    public void init(AbstractCache<String, String> abstractCache) {
        String menuContent = String.join(SEPARATOR, MENU_OPTIONS) + SEPARATOR;
        int select;
        boolean run = true;
        while (run) {
            select = askOption(menuContent);
            if (select < 0 || select > MENU_OPTIONS.size()) {
                System.out.println("Wrong input, you can select: 0 .. " + MENU_OPTIONS.size());
                continue;
            }
            switch (select) {
                case 1:
                    System.out.print("Write directory path: ");
                    abstractCache = new DirFileCache(ANSWERS_SCANNER.nextLine());
                    break;
                case 2:
                    System.out.print("Write file name: ");
                    String fileName = ANSWERS_SCANNER.nextLine();
                    if (abstractCache != null) {
                        abstractCache.load(fileName);
                    }
                    break;
                case 3:
                    System.out.print("Write file name: ");
                    String key = ANSWERS_SCANNER.nextLine();
                    if (abstractCache != null) {
                        System.out.println(abstractCache.get(key));
                    }
                    break;
                case 4:
                    run = false;
                    System.out.println("Thank you!");
            }
        }
    }

    private int askOption(String menu) {
        boolean invalid = true;
        int answer = -1;
        while (invalid) {
            System.out.print(menu);
            try {
                answer = Integer.parseInt(ANSWERS_SCANNER.nextLine());
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        }
        return answer;
    }
}