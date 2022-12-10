package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private final DateTimeParser dateTimeParser;

    private int id = 0;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HarbCareerDateTimeParser());
        List<Post> posts = habrCareerParse.list(PAGE_LINK);
        posts.forEach(System.out::println);
    }

    private String retrieveDescription(String link) {
        String description = null;
        try {
            Document document = Jsoup.connect(link).get();
            Element descriptionElement = document.selectFirst(".style-ugc");
            description = Objects.requireNonNull(descriptionElement).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;

    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        Connection connection;
        for (int i = 1; i <= 5; i++) {
            connection = Jsoup.connect(link + i);
            fillPosts(connection, posts);
        }
        return posts;
    }

    private void fillPosts(Connection connection, List<Post> posts) {
        try {
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = Objects.requireNonNull(titleElement).child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                Element dateTime = Objects.requireNonNull(dateElement).child(0);
                String vacancyName = titleElement.text();
                String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String description = retrieveDescription(vacancyLink);
                String date = dateTime.attr("datetime");
                LocalDateTime time = dateTimeParser.parse(date);
                posts.add(new Post(id++, vacancyName, vacancyLink, description, time));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
