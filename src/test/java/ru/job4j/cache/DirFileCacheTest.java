package ru.job4j.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DirFileCacheTest {
    private static Path tempCache;
    private static Path file;

    @BeforeAll
    public static void init() throws IOException {
        tempCache = Files.createTempDirectory("cache");
        file = Files.createTempFile(tempCache, "file", ".txt");
        try (PrintWriter writer = new PrintWriter(file.toFile())) {
            writer.print("test message");
        }
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(file.getFileName().toString(), "test message"),
                Arguments.of("Non-existent file", null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    @DisplayName("Test load when file exists and when file is not found")
    void load(String cacheName, String expected) {
        DirFileCache dirFileCache = new DirFileCache(tempCache.toFile().getAbsolutePath());
        assertEquals(expected, dirFileCache.load(cacheName));
    }
}