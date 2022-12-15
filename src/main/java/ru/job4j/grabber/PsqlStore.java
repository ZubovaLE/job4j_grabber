package ru.job4j.grabber;

import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private final Connection connection;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        connection = DriverManager.getConnection(cfg.getProperty("url"), cfg.getProperty("username"),
                cfg.getProperty("password"));
    }

    private static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream is = PsqlStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO post(name, text, link, created) VALUES (?, ?, ?, ?) ON CONFLICT (link) DO NOTHING;", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                fillPosts(posts, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private void fillPosts(List<Post> posts, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            posts.add(new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("text"),
                    resultSet.getString("link"),
                    resultSet.getTimestamp("created").toLocalDateTime()));
        }
    }

    @Override
    public Post findById(int id) {
        Post result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("link"),
                            resultSet.getString("text"),
                            resultSet.getTimestamp("created").toLocalDateTime());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        Properties cfg = getProperties();
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HarbCareerDateTimeParser());
//        try {
            PsqlStore psqlStore = new PsqlStore(cfg);
        Post postOne = new Post("name1", "link1", "description1", LocalDateTime.now());
        psqlStore.save(postOne);
        psqlStore.save(new Post("name2", "link2", "description2", LocalDateTime.now()));
        System.out.println(psqlStore.findById(1));
        System.out.println(psqlStore.getAll());
//            List<Post> posts = habrCareerParse.list("https://career.habr.com/vacancies/java_developer?page=1");
//            posts.forEach(psqlStore::save);
//            System.out.println(psqlStore.findById(1));
//            System.out.println(psqlStore.getAll());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
