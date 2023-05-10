package ru.job4j.dip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SimpleShopServiceTest {

    private SimpleShopService shopService;
    private User userOne;
    private User userTwo;

    @BeforeEach
    public void fillTable() {
        shopService = new SimpleShopService();
        userOne = new User(1, "One");
        userTwo = new User(2, "Two");
    }

    @Test
    void whenCreateBucketTwice() {
        assertFalse(shopService.createBucket(userOne));
        assertTrue(shopService.createBucket(userOne));
    }

    @Test
    void createOrder() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void removeOrder() {
    }

    @Test
    void payOrder() {
    }
}