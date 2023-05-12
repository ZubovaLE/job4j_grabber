package ru.job4j.dip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SimpleShopServiceTest {

    private SimpleShopService shopService;
    private ShopStore shopStore = new InMemoryShopStore();
    private OrderStore orderStore;
    private OrderService orderService = new SimpleOrderService(orderStore);
    private User userOne;
    private User userTwo;

    @BeforeEach
    public void fillTable() {
        shopService = new SimpleShopService(shopStore, orderService);
        userOne = new User(1, "One");
        userTwo = new User(2, "Two");
    }

    @Test
    void whenCreateBucketTwice() {
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