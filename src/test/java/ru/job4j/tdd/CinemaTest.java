package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CinemaTest {
    private Account account;
    private Cinema cinema;
    private Calendar date;

    @BeforeEach
    public void initData() {
        account = new AccountCinema();
        cinema = new Cinema3D();
        date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
    }

    @Ignore
    @Test
    @DisplayName("When buy successfully then return ticket")
    void whenBuySuccessfully() {
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Ignore
    @Test
    @DisplayName("Buy when already bought then return null")
    void buyWhenAlreadyBoughtThenNull() {
        Ticket ticketOne = cinema.buy(account, 1, 1, date);
        assertThat(cinema.buy(account, 1, 1, date)).isNull();
    }

    @Ignore
    @Test
    @DisplayName("Buy when incorrect date then return null")
    void buyWhenIncorrectDateThenNull() {
        date.set(1999, Calendar.NOVEMBER, 10, 23, 0);
        assertThat(cinema.buy(account, 1, 1, date)).isNull();
    }

    @Ignore
    @Test
    @DisplayName("Buy when incorrect row then return null")
    void buyWhenIncorrectRowThenNull() {
        assertThat(cinema.buy(account, -1, 1, date)).isNull();
    }

    @Ignore
    @Test
    @DisplayName("Buy when incorrect row then return null")
    void buyWhenIncorrectColumnThenNull() {
        assertThat(cinema.buy(account, 1, -1, date)).isNull();
    }

    @Ignore
    @Test
    @DisplayName("Buy when non-existent account then return null")
    void buyWhenNonExistentAccountThenNull() {
        assertThat(cinema.buy(null, 1, 1, date)).isNull();
    }

    @Ignore
    @Test
    @DisplayName("When find successfully then return list of sessions")
    void whenFind() {
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isEqualTo(List.of(new Session3D()));
    }

    @Ignore
    @Test
    @DisplayName("Find when no sessions then return empty list")
    public void whenNoSessionsThenEmptyList() {
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions).hasSize(0);
    }
}