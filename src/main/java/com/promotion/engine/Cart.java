package com.promotion.engine;

import com.promotion.engine.rules.Rule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

    private final Map<SKU, Item> items;
    private final Set<Rule> rules;

    public Cart(Set<Rule> rules) {
        this.items = new HashMap<>();
        this.rules = rules;
    }

    public void add(Item item) {
        items.put(item.getSku(), Item.of(item.getSku()).withQuantity(items.getOrDefault(item.getSku(), Item
                .of(item.getSku())).getQuantity().add(item.getQuantity())));
    }

    public BigDecimal total() {
        return rules.stream().map(rule -> rule.apply(items)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
