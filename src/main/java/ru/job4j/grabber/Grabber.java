package ru.job4j.grabber;

import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    private final Properties cfg = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(Grabber.class.getName());
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public Store store() throws SQLException {
        return new PsqlStore(cfg);
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    public void cfg() throws IOException {
        try (InputStream in = ClassLoader.getSystemResourceAsStream("app.properties")) {
            cfg.load(in);
        }
    }

    public void web(Store store) {
        try (ServerSocket server = new ServerSocket(Integer.parseInt(cfg.getProperty("port")))) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream()) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (Post post : store.getAll()) {
                        out.write(post.getTitle().getBytes((Charset.forName("Windows-1251"))));
                        out.write(LINE_SEPARATOR.getBytes((Charset.forName("Windows-1251"))));
                        out.write(post.getCreated().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss"))
                                .getBytes((Charset.forName("Windows-1251"))));
                        out.write(LINE_SEPARATOR.getBytes((Charset.forName("Windows-1251"))));
                        out.write(post.getLink().getBytes((Charset.forName("Windows-1251"))));
                        out.write(LINE_SEPARATOR.getBytes((Charset.forName("Windows-1251"))));
                        out.write(post.getDescription().getBytes((Charset.forName("Windows-1251"))));
                        out.write(LINE_SEPARATOR.getBytes((Charset.forName("Windows-1251"))));
                        out.write(LINE_SEPARATOR.getBytes((Charset.forName("Windows-1251"))));
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) {
            LOGGER.info("Started finding new posts");
            JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            List<Post> posts;
            try {
                posts = parse.list(PAGE_LINK).stream()
                        .filter(p -> StringUtils.containsIgnoreCase(p.getTitle(), "java"))
                        .sorted(Comparator.comparing(Post::getCreated).reversed())
                        .collect(Collectors.toList());
                posts.forEach(store::save);
                LOGGER.info("Finished finding new posts");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new HabrCareerParse(new HabrCareerDateTimeParser()), store, scheduler);
        grab.web(store);
    }
}
