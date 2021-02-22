package com.promotion.engine;

import lombok.Getter;
import lombok.With;

import java.math.BigDecimal;

@With
@Getter
public class Item {

    private final Product product;
    private final BigDecimal quantity;

    private Item(Product product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static Item of(SKU sku) {
        return new Item(new Product(sku), BigDecimal.ZERO);
    }

    public SKU getSku() {
        return product.getSku();
    }
}
