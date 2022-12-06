package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class HabrCareerParse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        HabrCareerParse habrCareerParse = new HabrCareerParse();
        HarbCareerDateTimeParser harbCareerDateTimeParser = new HarbCareerDateTimeParser();
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Vacancies. Page %d%n", i);
            Connection connection = Jsoup.connect(PAGE_LINK + i);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = Objects.requireNonNull(titleElement).child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                Element dateTime = Objects.requireNonNull(dateElement).child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String description = habrCareerParse.retrieveDescription(link);
                String date = dateTime.attr("datetime");
                LocalDateTime time = harbCareerDateTimeParser.parse(date);
                System.out.printf("%s %s, date:%s%ndescription: %s%n", vacancyName, link, time, description);
            });
        }
    }

    private String retrieveDescription(String link) {
        String description = null;
        try {
            Document document = Jsoup.connect(link).get();
            Element descriptionElement = document.selectFirst(".style-ugc");
            description = descriptionElement.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;

    }
}
