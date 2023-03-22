package ru.job4j.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    @DisplayName("Test produce when valid arguments")
    void testProduceWhenValidArguments() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "student", "subject", "you");
        Generator generator = new TemplateGenerator();
        String expected = "I am a student, Who are you?";
        String result = generator.produce(template, args);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test produce when no needed keys then get exception")
    void testProduceWhenNoNeededKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "student");
        Generator generator = new TemplateGenerator();
        assertThrows(NoSuchElementException.class, () -> generator.produce(template, args));
    }

    @Test
    @DisplayName("Test produce when extra keys then get exception")
    void testProduceWhenExtraKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "student", "subject", "you", "age", "23");
        Generator generator = new TemplateGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}