package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(readSettings())
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(5000);
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    private static int readSettings() throws IOException, NumberFormatException {
        Properties config = new Properties();
        int seconds;
        try (InputStream is = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(is);
            seconds = Integer.parseInt(config.getProperty("rabbit.interval"));
        }
        return seconds;
    }

    public static class Rabbit implements Job, AutoCloseable {
        private Connection connection;

        public Rabbit() throws SQLException, IOException, ClassNotFoundException {
            connect();
            System.out.println(hashCode());
        }

        private void connect() throws IOException, ClassNotFoundException, SQLException {
            try (InputStream is = Rabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
                Properties properties = new Properties();
                properties.load(is);
                Class.forName(properties.getProperty("driver"));
                this.connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password")
                );
            }
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            store.add(System.currentTimeMillis());
        }

        @Override
        public void close() throws Exception {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
