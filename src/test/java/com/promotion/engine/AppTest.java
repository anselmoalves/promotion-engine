package com.promotion.engine;

import com.promotion.engine.rules.Rule;
import com.promotion.engine.rules.RuleA;
import com.promotion.engine.rules.RuleB;
import com.promotion.engine.rules.RuleCD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private static final Set<Rule> ACTIVE_RULES = Collections.unmodifiableSet(new HashSet<>(Arrays
            .asList(new RuleA(), new RuleB(), new RuleCD())));
    private Cart cart;

    @BeforeEach
    public void init() {
        cart = new Cart(ACTIVE_RULES);
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
