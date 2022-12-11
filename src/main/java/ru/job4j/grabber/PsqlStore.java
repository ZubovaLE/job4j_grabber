package ru.job4j.grabber;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection connection;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        connection = DriverManager.getConnection(cfg.getProperty("jdbc.url"), cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password"));
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO post(name, text, link, created) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
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
        return null;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
