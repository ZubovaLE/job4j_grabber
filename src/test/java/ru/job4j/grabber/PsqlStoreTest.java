package ru.job4j.grabber;

import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PsqlStoreTest {
    private static Connection connection;
    private static PsqlStore store;

    private Post postOne;
    private Post postTwo;
    private Post postThree;

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
        try (PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE post Restart IDENTITY;")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void fillTable() {
        postOne = new Post("title1", "link1", "description1", LocalDateTime.now());
        postTwo = new Post("title2", "link2", "description2", LocalDateTime.now());
        postThree = new Post("title3", "link3", "description3", LocalDateTime.now());
        store.save(postOne);
        store.save(postTwo);
        store.save(postThree);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM post")) {
            statement.execute();
        }
    }

    @Test
    @DisplayName("FindById when id of postTwo then must get the second post")
    public void findByGeneratedIdWhenIdOfPostTwoThenMustGetPostTwo() {
        assertThat(store.findById(postTwo.getId()), is(postTwo));
    }

    @Test
    @DisplayName("FindAll when three posts then get all posts")
    public void findAllWhenThreePosts() {
        List<Post> posts = store.getAll();
        assertThat(posts.size(), is(3));
        assertTrue(posts.contains(postOne));
        assertTrue(posts.contains(postTwo));
        assertTrue(posts.contains(postThree));
    }
}