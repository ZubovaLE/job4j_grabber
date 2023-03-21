package ru.job4j.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CinemaTest {

    @Test
    @DisplayName("Test buy")
    void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    @DisplayName("Test buy when is already bought then null")
    void buyWhenAlreadyBoughtThenNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticketOne = cinema.buy(account, 1, 1, date);
        Ticket ticketTwo = cinema.buy(account, 1, 1, date);
        assertThat(ticketOne, is(new Ticket3D()));
        assertNull(ticketTwo);
    }

    @Test
    @DisplayName("Test buy when no tickets then null")
    void buyWhenNoTicketsThenNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertNull(ticket);
    }
    
    @Test
    @DisplayName("Test find")
    void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Test
    @DisplayName("Test find when no sessions")
    public void whenNotSession() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions.size(), is(0));
    }
}