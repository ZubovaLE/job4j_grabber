package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private final Connection connection;
    private static volatile boolean tableExists = false;
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS post(id SERIAL PRIMARY KEY, title TEXT," +
            "link TEXT NOT NULL UNIQUE, description TEXT, created TIMESTAMP);";

    public PsqlStore(Connection connection) {
        this.connection = connection;
    }

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver"));
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
        checkTable(connection);
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO post(title, link, description, created) VALUES (?, ?, ?, ?) RETURNING id;")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getLink());
            statement.setString(3, post.getDescription());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            post.setId(statement.executeQuery().getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        checkTable(connection);
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                fillPosts(posts, resultSet);
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
                    resultSet.getString("title"),
                    resultSet.getString("link"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("created").toLocalDateTime()));
        }
    }

    @Override
    public Post findById(int id) {
        Post result = null;
        checkTable(connection);
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("link"),
                            resultSet.getString("description"),
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

    private synchronized void checkTable(Connection connection) {
        if (!tableExists) {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet resultSet = metaData.getTables(null, null, "post", new String[]{"TABLES"});
                tableExists = resultSet.next();
                if (!tableExists) {
                    createTable(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties cfg = getProperties();
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        try {
            PsqlStore psqlStore = new PsqlStore(cfg);
            List<Post> posts = habrCareerParse.list("https://career.habr.com/vacancies/java_developer?page=1");
            posts.forEach(psqlStore::save);
            System.out.println(psqlStore.findById(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
