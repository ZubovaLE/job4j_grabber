package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.util.*;

public class PsqlStore implements Store<Post>, AutoCloseable {
    private final Connection connection;
    private volatile boolean tableExists = false;
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS post(id SERIAL PRIMARY KEY, title TEXT,link TEXT NOT NULL UNIQUE, description TEXT, created TIMESTAMP);";
    private static final String GET_ALL_LINKS = "SELECT link FROM post";
    private static final String PROPERTIES_NAME = "app.properties";

    public PsqlStore(Connection connection) {
        this.connection = connection;
    }

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver"));
            connection = DriverManager.getConnection(cfg.getProperty("url"), cfg.getProperty("username"), cfg.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream is = PsqlStore.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME)) {
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
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }
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
    public List<String> getAllLinks() {
        List<String> addedLinks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_LINKS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addedLinks.add(resultSet.getString("link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addedLinks;
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
            Set<String> linksFromDB = new HashSet<>(psqlStore.getAllLinks());
            if (!posts.isEmpty()) {
                posts.stream()
                        .filter(p -> !linksFromDB.contains(p.getLink()))
                        .forEach(psqlStore::save);
            }
            System.out.println(psqlStore.findById(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
