package ru.job4j.grabber;

import org.apache.commons.lang3.Validate;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class HabrCareerParse implements Parse {
    private static final URIBuilder SOURCE_LINK = new URIBuilder().setScheme("https").setHost("career.habr.com");
    private static final String PAGE_LINK = SOURCE_LINK.setPath("vacancies/java_developer").toString();
    private final DateTimeParser dateTimeParser;
    private String href;
    private int startingId;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
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
        URIBuilder uriBuilder;
        String URIBuilderParameter = "page";
        try {
            uriBuilder = new URIBuilder(link);
            for (int i = 1; i <= 5; i++) {
                connection = Jsoup.connect(uriBuilder.setParameter(URIBuilderParameter, String.valueOf(i)).toString());
                fillPosts(connection, posts);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private void fillPosts(Connection connection, List<Post> posts) {
        try {
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            startingId = posts.size();
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = Objects.requireNonNull(titleElement).child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                Validate.isTrue(Objects.requireNonNull(dateElement).childrenSize() == 1, "vacancy-card__date must have only one child");
                Element dateTime = Objects.requireNonNull(dateElement).child(0);
                String vacancyName = titleElement.text();
                href = linkElement.attr("href");
                if (isNotBlank(href)) {
                    String vacancyLink = SOURCE_LINK.setPath(href).toString();
                    String description = retrieveDescription(vacancyLink);
                    String datetime = dateTime.attr("datetime");
                    LocalDateTime time = dateTimeParser.parse(datetime);
                    posts.add(new Post(++startingId, vacancyName, vacancyLink, description, time));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> posts = habrCareerParse.list(PAGE_LINK);
        posts.forEach(System.out::println);
    }
}
