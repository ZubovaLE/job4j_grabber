package ru.job4j.dip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SimpleShopServiceTest {

    private SimpleShopService shopService = new SimpleShopService();

    @Test
    void whenCreateBucketTwice() {
        assertTrue(shopService.createBucket(new User(1, "One")));
        assertFalse(shopService.createBucket(new User(1, "One")));
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