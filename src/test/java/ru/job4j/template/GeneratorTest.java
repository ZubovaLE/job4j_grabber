package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Disabled
    @Test
    @DisplayName("Test produce when valid arguments")
    void testProduceWhenValidArguments() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.ofEntries(Map.entry("name", "student"), Map.entry("subject", "you"));
        Generator generator = new TemplateGenerator();
        String expected = "I am a student, Who are you?";
        String result = generator.produce(template, args);
        assertEquals(expected, result);
    }

    @Disabled
    @Test
    @DisplayName("Test produce when no needed keys then get exception")
    void testProduceWhenNoNeededKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args =  Map.ofEntries(Map.entry("name", "student"));
        Generator generator = new TemplateGenerator();
        assertThrows(NoSuchElementException.class, () -> generator.produce(template, args));
    }

    @Disabled
    @Test
    @DisplayName("Test produce when extra keys then get exception")
    void testProduceWhenExtraKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args =  Map.ofEntries(Map.entry("name", "student"), Map.entry("subject", "you"),
                Map.entry("age", "23"));
        Generator generator = new TemplateGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }

    @Disabled
    @Test
    @DisplayName("Test produce when empty template")
    void testProduceWhenEmptyTemplate() {
        String template = "I am a";
        Map<String, String> args =  Map.ofEntries();
        Generator generator = new TemplateGenerator();
        assertEquals(template, generator.produce(template, args));
    }

    @Disabled
    @Test
    @DisplayName("Test produce when empty keys then get exception")
    void testProduceWhenEmptyKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args =  Map.ofEntries();
        Generator generator = new TemplateGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}