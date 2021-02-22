package com.promotion.engine.rules;

import com.promotion.engine.Item;
import com.promotion.engine.SKU;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
public class RuleCD implements Rule {

    @Override
    public BigDecimal apply(Map<SKU, Item> items) {
        return BigDecimal.valueOf(20);
    }
}
