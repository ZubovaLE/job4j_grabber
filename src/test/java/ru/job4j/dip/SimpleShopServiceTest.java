package ru.job4j.dip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dip.examples.courseExample.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleShopServiceTest {

    private SimpleShopService shopService;
    private ShopStore shopStore = new InMemoryShopStore();
    private OrderStore orderStore;
    private OrderService orderService = new SimpleOrderService();
    private User userOne;
    private User userTwo;

    @BeforeEach
    public void fillTable() {
        shopService = new SimpleShopService(shopStore, orderService);
        userOne = new User(1, "One");
        userTwo = new User(2, "Two");
    }

    @Test
    void whenFindOrderDontFindOrders() {
        //Given
        int idUser = 10;
        String name = "Alex";

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> shopService.payOrder(new User(idUser, name), new Order()));
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