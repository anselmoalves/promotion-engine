package com.promotion.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private Cart cart;

    @BeforeEach
    public void init() {
        cart = new Cart();
    }

    @Test
    public void scenarioA() {
        cart.add(new Item(new Product(SKU.A), BigDecimal.ONE));
        cart.add(new Item(new Product(SKU.B), BigDecimal.ONE));
        cart.add(new Item(new Product(SKU.C), BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(100), cart.total());
    }

    @Test
    public void scenarioB() {
        cart.add(new Item(new Product(SKU.A), BigDecimal.valueOf(5)));
        cart.add(new Item(new Product(SKU.B), BigDecimal.valueOf(5)));
        cart.add(new Item(new Product(SKU.C), BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(370), cart.total());
    }
}
