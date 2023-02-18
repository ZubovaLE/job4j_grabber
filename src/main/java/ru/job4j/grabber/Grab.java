package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public interface Grab<E> {
    void init(Parse parse, Store<E> store, Scheduler scheduler, String link) throws SchedulerException;
}