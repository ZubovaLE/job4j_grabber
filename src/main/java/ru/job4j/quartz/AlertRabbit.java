package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertRabbit.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Optional<Properties> properties = getProperties();
        try (Connection connection = getConnection(Objects.requireNonNull(properties.orElse(null)))) {
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("DB", connection);
                JobDetail job = newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(readInterval(properties.orElse(null)))
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
            } catch (Exception se) {
                LOGGER.error(se.getMessage(), se);
            }
        }
    }

    private static Optional<Properties> getProperties() {
        Properties config = null;
        try (InputStream is = ClassLoader.getSystemResourceAsStream("rabbit.properties")) {
            config = new Properties();
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(config);
    }

    private static int readInterval(Properties properties) throws NumberFormatException {
        return Integer.parseInt(properties.getProperty("rabbit.interval"));
    }

    private static Connection getConnection(Properties properties) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("DB");
            if (tableExists(connection, "rabbit")) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO rabbit(created_data) VALUES(?)")) {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().withNano(0)));
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean tableExists(Connection connection, String tableName) throws SQLException {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return resultSet.next();
        }
    }
}
