package com.promotion.engine;

import com.promotion.engine.rules.Rule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<SKU, Item> items;
    private Set<Rule> rules;

    public Cart(Set<Rule> rules) {
        this.items = new HashMap<>();
        this.rules = rules;
    }

    public void add(Item item) {
        items.put(item.getSku(), item);
    }

    public BigDecimal total() {
        return rules.stream().map(rule -> rule.apply(items)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
