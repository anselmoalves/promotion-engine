package com.promotion.engine;

import java.math.BigDecimal;

public class Item {

    private final Product product;
    private final BigDecimal quantity;

    public Item(Product product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
