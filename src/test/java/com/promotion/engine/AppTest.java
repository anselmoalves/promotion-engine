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
        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.B).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.C).withQuantity(BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(100), cart.total());
    }

    @Test
    public void scenarioB() {
        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.valueOf(5)));
        cart.add(Item.of(SKU.B).withQuantity(BigDecimal.valueOf(5)));
        cart.add(Item.of(SKU.C).withQuantity(BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(370), cart.total());
    }

    @Test
    public void scenarioC() {
        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.valueOf(3)));
        cart.add(Item.of(SKU.B).withQuantity(BigDecimal.valueOf(5)));
        cart.add(Item.of(SKU.C).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.D).withQuantity(BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(280), cart.total());
    }

    @Test
    public void should_accumulate_items_of_sku_previously_added() {
        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.B).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.C).withQuantity(BigDecimal.ONE));
        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(150), cart.total());
    }

    @Test
    public void should_not_allow_multiple_evaluations_of_the_same_rule() {
        Set<Rule> duplicateRules = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new RuleA(), new RuleA())));
        cart = new Cart(duplicateRules);

        cart.add(Item.of(SKU.A).withQuantity(BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(50), cart.total());
    }
}
