package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            return Files.readString(Paths.get(cachingDir + key));
        } catch (IOException e) {
            System.out.printf("File \"%s\" is not found%n", key);
        }
        return null;
    }
}