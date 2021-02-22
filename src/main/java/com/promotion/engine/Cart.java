package com.promotion.engine;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class Cart {

    public void add(Item item) {
    }

    public BigDecimal total() {
        return BigDecimal.valueOf(100);
    }
}
