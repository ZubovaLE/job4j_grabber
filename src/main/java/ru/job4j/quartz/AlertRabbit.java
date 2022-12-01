package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    private static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream is = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(is);
            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    private static int readInterval(Properties properties) throws NumberFormatException {
        return Integer.parseInt(properties.getProperty("rabbit.interval"));
    }

    private static Connection getConnection(Properties properties) throws IOException, ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Properties properties = getProperties();
        try (Connection connection = getConnection(properties)) {
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("DB", connection);
                JobDetail job = newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(readInterval(properties))
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
                try (Statement statement = connection.createStatement()) {
                    ResultSet selection = statement.executeQuery("SELECT * FROM rabbit");
                    var metaData = selection.getMetaData();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        System.out.println(metaData.getColumnName(i) + ", " + metaData.getColumnTypeName(i));
                    }
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            try (Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("DB")) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO rabbit(created_data) VALUES(?)")) {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().withNano(0)));
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
