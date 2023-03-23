package ru.job4j.tdd;

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
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
    }

    @Test
    @DisplayName("When correct buy then return ticket")
    void whenBuySuccessfully() {
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    @DisplayName("Test buy when already bought then exception")
    void buyWhenAlreadyBoughtThenException() {
        Ticket ticketOne = cinema.buy(account, 1, 1, date);
        assertThat(ticketOne).isEqualTo(new Ticket3D());
        assertThatIllegalArgumentException().isThrownBy(() ->  cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("Buy when incorrect date then exception")
    void buyWhenIncorrectDateThenException() {
        assertThatIllegalArgumentException().isThrownBy(() -> cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("Buy when incorrect row then exception")
    void buyWhenIncorrectRowThenException() {
        assertThatIllegalArgumentException().isThrownBy(() -> cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("Buy when incorrect row then exception")
    void buyWhenIncorrectColumnThenException() {
        assertThatIllegalArgumentException().isThrownBy(() -> cinema.buy(account, 1, 1, date));
    }

    @Test
    @DisplayName("When correct find then return list")
    void whenFind() {
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isEqualTo(List.of(new Session3D()));
    }

    @Test
    @DisplayName("Test find when no sessions then return empty list")
    public void whenNoSessionsThenEmptyList() {
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions).hasSize(0);
    }
}