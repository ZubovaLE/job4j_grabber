package ru.job4j.grabber;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PsqlStoreTest {
    private static Connection connection;
    private static PsqlStore store;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = ClassLoader.getSystemResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        store = new PsqlStore(connection);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    @DisplayName("FindById when add item then must get the same item")
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        Post post = new Post("title", "link", "description", LocalDateTime.now());
        store.save(post);
        assertThat(store.findById(1),is(post));
    }
}